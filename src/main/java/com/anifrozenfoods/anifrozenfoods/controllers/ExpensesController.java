package com.anifrozenfoods.anifrozenfoods.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.anifrozenfoods.anifrozenfoods.models.Expense;
import com.anifrozenfoods.anifrozenfoods.models.ExpenseDto;
import com.anifrozenfoods.anifrozenfoods.services.ExpensesRepository;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/expenses")
public class ExpensesController {
    @Autowired
    private ExpensesRepository repo;

    @GetMapping({ "", "/" })
    public String showSaleList(Model model) {
        List<Expense> expenses = repo.findAll();
        model.addAttribute("expenses", expenses);
        return "expenses/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        ExpenseDto expenseDto = new ExpenseDto();
        model.addAttribute("expenseDto", expenseDto);
        return "expenses/CreateExpense";
    }

    @PostMapping("/create")
    public String createExpense(
            @Valid @ModelAttribute ExpenseDto expenseDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "expenses/CreateExpense";
        }

        Expense expense = new Expense();
        expense.setDate(expenseDto.getDate());
        expense.setDescription(expenseDto.getDescription());
        expense.setItem(expenseDto.getItem());
        expense.setQty(expenseDto.getQty());
        expense.setUnitRate(expenseDto.getUnitRate());
        expense.setSubTotal(expenseDto.getSubTotal());
        
        expense.setCreatedAt(new Date());

        repo.save(expense);

        return "redirect:/expenses";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id) {

                try {
                    Expense expense = repo.findById(id).get();
                    model.addAttribute("expense", expense);
        
                    ExpenseDto expenseDto = new ExpenseDto();
                    expenseDto.setDate(expense.getDate());
                    expenseDto.setDescription(expense.getDescription());
                    expenseDto.setItem(expense.getItem());
                    expenseDto.setQty(expense.getQty());
                    expenseDto.setUnitRate(expense.getUnitRate());
                    expenseDto.setSubTotal(expense.getSubTotal());
        
                    model.addAttribute("expenseDto", expenseDto);

                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                    return "redirect:/expenses";
                }

            return "expenses/EditExpense";
    }

    @PostMapping("/edit")
    public String updateExpense(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ExpenseDto expenseDto,
            BindingResult result) {

                try {
                    Expense expense = repo.findById(id).get();
                    model.addAttribute("expense", expense);
        
                    if (result.hasErrors()) {
                        return "expenses/EditExpense";
                    }
        
                    expense.setDate(expenseDto.getDate());
                    expense.setDescription(expenseDto.getDescription());
                    expense.setItem(expenseDto.getItem());
                    expense.setQty(expenseDto.getQty());
                    expense.setUnitRate(expenseDto.getUnitRate());
                    expense.setSubTotal(expenseDto.getSubTotal());
 
                    repo.save(expense);
        
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
        
                return "redirect:/expenses";
            }

    @GetMapping("/delete")
    public String deleteSale(
            @RequestParam int id) {

        try {
            Expense expense = repo.findById(id).get();

            repo.delete(expense);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/expenses";
    }
}
