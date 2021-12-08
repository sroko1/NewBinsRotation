package com.example.NewBinsRotation.controllers;


import com.example.NewBinsRotation.models.Form;
import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.models.Truck;
import com.example.NewBinsRotation.services.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/form")
public class FormController {

    private final FormService formService;
    private final InboundService inboundService;
    private final BinFeatureService binFeatureService;
    private final TruckService truckService;
    private final SupplierService supplierService;

    public FormController(FormService formService, InboundService inboundService,
                          BinFeatureService binFeatureService, TruckService truckService, SupplierService supplierService) {
        this.formService = formService;
        this.binFeatureService = binFeatureService;
        this.inboundService = inboundService;
        this.truckService = truckService;
        this.supplierService = supplierService;
    }

    @RequestMapping("/list")
    public String getAllForm(Model model) {
        model.addAttribute("form", formService.getAllForm());
        return "dynamicForm";
    }

    @RequestMapping("/list/{id}")
    public String getPointedForm(@PathVariable int id, Model model) {
        model.addAttribute("form", formService.getFormById(id));
        return "dynamicForm";
    }

    @PostMapping("/delete")
    public String deleteForm(@RequestParam int id) {
        formService.deleteForm(id);
        return "redirect:paginated";
    }

    @RequestMapping("/paginated")
    public String getFormsPaginated(Model model,
                                   @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        Page<Form> formPage = formService
                .getFormsPaginated(
                        PageRequest.of(currentPage - 1, 10)
                );

        model.addAttribute("formPage", formPage);

        int totalPages = formPage.getTotalPages();
        if (totalPages > 0) {

            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "dynamicForm";
    }

    @PostMapping("/update")
    public String editForm(@RequestParam Integer id, Form form, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "newInForm";
        } else {
            model.addAttribute("form", formService.getFormById(id));
            model.addAttribute("truck",truckService.getTruckById(id));
            model.addAttribute("inbound", inboundService.getInboundById(id));
           // model.addAttribute("form", formService.getAllForm()); /// tutaj

            model.addAttribute("binsFeatures", binFeatureService.getAllBinFeature());
            model.addAttribute("inbounds", inboundService.getAllInbound());

            model.addAttribute("trucks", truckService.getAllTruck());

            model.addAttribute("suppliers", supplierService.getAllSuppliers());
        }
        return "postForm";
    }

    @GetMapping("/build")

    public String addNewForm(Model model ) {
        model.addAttribute("form", new Form());
       // model.addAttribute("truck", new Truck());
       // model.addAttribute("inbound", new Inbound());
       // model.addAttribute("form", formService.getAllForm());
        model.addAttribute("inbounds", inboundService.getAllInbound());
        model.addAttribute("binsFeatures", binFeatureService.getAllBinFeature());
        model.addAttribute("trucks", truckService.getAllTruck());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());

        return "draw";
    }

    @PostMapping("/build")
    public String registerForm(@Valid Form form, BindingResult result, Truck truck, Inbound inbound) {
        if (result.hasErrors()) {
            return "draw";
        } else {
            formService.saveForm(form);
            truckService.save(truck);
            inboundService.save(inbound);

            return "newInForm";
        }

    }

    @PostMapping("/saveForm")
    public String saveForm( @RequestParam Integer id, @RequestParam String regNumber,
                            Form form, Truck truck, Inbound inbound) {
      formService.getFormById(id);
        formService.editForm(form);

        truckService.findByRegNumber(regNumber);
      // truckService.getTruckById(id);
        truckService.editTruck(truck);
        inboundService.getInboundById(id);
        inboundService.editInbound(inbound);

        return "redirect:paginated";
    }
}

