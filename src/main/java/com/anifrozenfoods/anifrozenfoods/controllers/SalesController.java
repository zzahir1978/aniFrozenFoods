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
import com.anifrozenfoods.anifrozenfoods.models.Sale;
import com.anifrozenfoods.anifrozenfoods.models.SaleDto;
import com.anifrozenfoods.anifrozenfoods.services.SalesRepository;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    private SalesRepository repo;

    @GetMapping({ "", "/" })
    public String showSaleList(Model model) {
        List<Sale> sales = repo.findAll();
        model.addAttribute("sales", sales);
        return "sales/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        SaleDto saleDto = new SaleDto();
        model.addAttribute("saleDto", saleDto);
        return "sales/CreateSale";
    }

    @PostMapping("/create")
    public String createSale(
            @Valid @ModelAttribute SaleDto saleDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return "sales/CreateSale";
        }

        Sale sale = new Sale();
        sale.setDate(saleDto.getDate());
        sale.setDescription(saleDto.getDescription());
        sale.setItem(saleDto.getItem());
        sale.setQty(saleDto.getQty());
        sale.setUnitRate(saleDto.getUnitRate());
        sale.setSubTotal(saleDto.getSubTotal());
        
        sale.setCreatedAt(new Date());

        repo.save(sale);

        return "redirect:/sales";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id) {

                try {
                    Sale sale = repo.findById(id).get();
                    model.addAttribute("sale", sale);
        
                    SaleDto saleDto = new SaleDto();
                    saleDto.setDate(sale.getDate());
                    saleDto.setDescription(sale.getDescription());
                    saleDto.setItem(sale.getItem());
                    saleDto.setQty(sale.getQty());
                    saleDto.setUnitRate(sale.getUnitRate());
                    saleDto.setSubTotal(sale.getSubTotal());
        
                    model.addAttribute("saleDto", saleDto);

                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                    return "redirect:/sales";
                }

            return "sales/EditSale";
    }

    @PostMapping("/edit")
    public String updateSale(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute SaleDto saleDto,
            BindingResult result) {

                try {
                    Sale sale = repo.findById(id).get();
                    model.addAttribute("sale", sale);
        
                    if (result.hasErrors()) {
                        return "sales/EditSale";
                    }
        
                    sale.setDate(saleDto.getDate());
                    sale.setDescription(saleDto.getDescription());
                    sale.setItem(saleDto.getItem());
                    sale.setQty(saleDto.getQty());
                    sale.setUnitRate(saleDto.getUnitRate());
                    sale.setSubTotal(saleDto.getSubTotal());
 
                    repo.save(sale);
        
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
        
                return "redirect:/sales";
            }

    @GetMapping("/delete")
    public String deleteSale(
            @RequestParam int id) {

        try {
            Sale sale = repo.findById(id).get();

            repo.delete(sale);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/sales";
    }
}
