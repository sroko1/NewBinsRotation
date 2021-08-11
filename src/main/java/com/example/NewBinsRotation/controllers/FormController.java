package com.example.NewBinsRotation.controllers;


import com.example.NewBinsRotation.models.Form;
import com.example.NewBinsRotation.models.Inbound;
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
    private final OutboundService outboundService;
    private final BinFeatureService binFeatureService;
    private final TruckService truckService;

    public FormController(FormService formService, InboundService inboundService, OutboundService outboundService,
                          BinFeatureService binFeatureService, TruckService truckService) {
        this.formService = formService;
        this.inboundService = inboundService;
        this.outboundService = outboundService;
        this.binFeatureService = binFeatureService;
        this.truckService = truckService;
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
    public String getFormPaginated(Model model,
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
    public String editForm(@RequestParam int id, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new";
        } else {
            model.addAttribute("form", formService.getFormById(id));

            model.addAttribute("inbounds", inboundService.getAllInbound());

            model.addAttribute("outbounds", outboundService.getAllOutbound());

            model.addAttribute("trucks", truckService.getAllTruck());

            model.addAttribute("binFeatures", binFeatureService.getAllBinFeature());
        }
        return "postForm";
    }

    @GetMapping("/build")
    public String addNewForm(Model model ) {
        model.addAttribute("form", new Form());
        model.addAttribute("inbounds", inboundService.getAllInbound());
        model.addAttribute("outbounds", outboundService.getAllOutbound());
        model.addAttribute("binsFeatures", binFeatureService.getAllBinFeature());
        model.addAttribute("trucks", truckService.getAllTruck());
        return "postForm";
    }

    @PostMapping("/build")
    public String registerForm(@Valid Form form, BindingResult result) {
        if (result.hasErrors()) {
            return "draw";
        } else {
            formService.saveForm(form);
            return "redirect:draw";
        }

    }

    @PostMapping("/saveForm")
    public String saveData( Form form) {
        //formService.getFormById(id);
        formService.updateForm(form);


        return "redirect:paginated";
    }
}

