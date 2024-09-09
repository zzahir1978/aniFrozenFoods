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
import com.anifrozenfoods.anifrozenfoods.models.IncomeStatement;
import com.anifrozenfoods.anifrozenfoods.models.IncomeStatementDto;
import com.anifrozenfoods.anifrozenfoods.services.IncomeStatementsRepository;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/incomeStatements")
public class IncomeStatementsController {
    @Autowired
    private IncomeStatementsRepository repo;

    @GetMapping({ "", "/" })
    public String showIncomeStatementList(Model model) {
        List<IncomeStatement> incomeStatements = repo.findAll();
        model.addAttribute("incomeStatements", incomeStatements);
        return "incomeStatements/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        IncomeStatementDto incomeStatementDto = new IncomeStatementDto();
        model.addAttribute("incomeStatementDto", incomeStatementDto);
        return "incomeStatements/CreateIncomeStatement";
    }

    @PostMapping("/create")
    public String createIncomeStatement(
            @Valid @ModelAttribute IncomeStatementDto incomeStatementDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "incomeStatements/CreateIncomeStatement";
        }

        IncomeStatement incomeStatement = new IncomeStatement();
        incomeStatement.setYear(incomeStatementDto.getYear());
        incomeStatement.setMonth(incomeStatementDto.getMonth());
        incomeStatement.setBasicSalary(incomeStatementDto.getBasicSalary());
        incomeStatement.setTotalClaim(incomeStatementDto.getTotalClaim());
        incomeStatement.setEpfEmployee(incomeStatementDto.getEpfEmployee());
        incomeStatement.setEpfEmployer(incomeStatementDto.getEpfEmployer());
        incomeStatement.setTotalEpf(incomeStatementDto.getTotalEpf());
        incomeStatement.setSocso(incomeStatementDto.getSocso());
        incomeStatement.setEis(incomeStatementDto.getEis());
        incomeStatement.setSportClubFee(incomeStatementDto.getSportClubFee());
        incomeStatement.setNettSalary(incomeStatementDto.getNettSalary());

        incomeStatement.setCreatedAt(new Date());

        repo.save(incomeStatement);

        return "redirect:/incomeStatements";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id) {

                try {
                    IncomeStatement incomeStatement = repo.findById(id).get();
                    model.addAttribute("incomeStatement", incomeStatement);
        
                    IncomeStatementDto incomeStatementDto = new IncomeStatementDto();
                    incomeStatementDto.setYear(incomeStatement.getYear());
                    incomeStatementDto.setMonth(incomeStatement.getMonth());
                    incomeStatementDto.setBasicSalary(incomeStatement.getBasicSalary());
                    incomeStatementDto.setTotalClaim(incomeStatement.getTotalClaim());
                    incomeStatementDto.setEpfEmployee(incomeStatement.getEpfEmployee());
                    incomeStatementDto.setEpfEmployer(incomeStatement.getEpfEmployer());
                    incomeStatementDto.setTotalEpf(incomeStatement.getTotalEpf());
                    incomeStatementDto.setSocso(incomeStatement.getSocso());
                    incomeStatementDto.setEis(incomeStatement.getEis());
                    incomeStatementDto.setSportClubFee(incomeStatement.getSportClubFee());
                    incomeStatementDto.setNettSalary(incomeStatement.getNettSalary());
        
                    model.addAttribute("incomeStatementDto", incomeStatementDto);

                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                    return "redirect:/incomeStatements";
                }

            return "incomeStatements/EditIncomeStatement";
    }

    @PostMapping("/edit")
    public String updateIncomeStatement(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute IncomeStatementDto incomeStatementDto,
            BindingResult result) {

                try {
                    IncomeStatement incomeStatement = repo.findById(id).get();
                    model.addAttribute("incomeStatement", incomeStatement);
        
                    if (result.hasErrors()) {
                        return "incomeStatements/EditIncomeStatement";
                    }
        
                    incomeStatement.setYear(incomeStatementDto.getYear());
                    incomeStatement.setMonth(incomeStatementDto.getMonth());
                    incomeStatement.setBasicSalary(incomeStatementDto.getBasicSalary());
                    incomeStatement.setTotalClaim(incomeStatementDto.getTotalClaim());
                    incomeStatement.setEpfEmployee(incomeStatementDto.getEpfEmployee());
                    incomeStatement.setEpfEmployer(incomeStatementDto.getEpfEmployer());
                    incomeStatement.setTotalEpf(incomeStatementDto.getTotalEpf());
                    incomeStatement.setSocso(incomeStatementDto.getSocso());
                    incomeStatement.setEis(incomeStatementDto.getEis());
                    incomeStatement.setSportClubFee(incomeStatementDto.getSportClubFee());
                    incomeStatement.setNettSalary(incomeStatementDto.getNettSalary());
        
                    repo.save(incomeStatement);
        
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
        
                return "redirect:/incomeStatements";
            }

    @GetMapping("/delete")
    public String deleteIncomeStatement(
            @RequestParam int id) {

        try {
            IncomeStatement incomeStatement = repo.findById(id).get();

            repo.delete(incomeStatement);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/incomeStatements";
    }
}
