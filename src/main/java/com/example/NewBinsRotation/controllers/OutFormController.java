package com.example.NewBinsRotation.controllers;


import com.example.NewBinsRotation.models.OutForm;
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
@RequestMapping("/outForm")
public class OutFormController {

    private final OutFormService outFormService;
    private final OutboundService outboundService;
    private final BinFeatureService binFeatureService;
    private final TruckService truckService;
    private final SupplierService supplierService;

    public OutFormController(OutFormService outFormService, OutboundService outboundService,
                             BinFeatureService binFeatureService, TruckService truckService, SupplierService supplierService) {
        this.outFormService = outFormService;
    this.outboundService = outboundService;
        this.binFeatureService = binFeatureService;
        this.truckService = truckService;
        this.supplierService = supplierService;
    }

    @RequestMapping("/list")
    public String getAllOutForm(Model model) {
        model.addAttribute("outForm", outFormService.getAllOutForm());
        return "dynamicOutForm";
    }

    @RequestMapping("/list/{id}")
    public String getPointedOutForm(@PathVariable int id, Model model) {
        model.addAttribute("outForm", outFormService.getOutFormById(id));
        return "dynamicOutForm";
    }

    @PostMapping("/delete")
    public String deleteOutForm(@RequestParam int id) {
        outFormService.deleteOutForm(id);
        return "redirect:paginated";
    }

    @RequestMapping("/paginated")
    public String getOutFormPaginated(Model model,
                                   @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        Page<OutForm> outFormPage = outFormService
                .getOutFormsPaginated(
                        PageRequest.of(currentPage - 1, 10)
                );

        model.addAttribute("outFormPage", outFormPage);

        int totalPages = outFormPage.getTotalPages();
        if (totalPages > 0) {

            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "dynamicOutForm";
    }
//////////////////////////////////
    @PostMapping("/update")
    public String editOutForm(@RequestParam int id, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newOutForm";
        } else {
            model.addAttribute("outForm", outFormService.getOutFormById(id));

           // model.addAttribute("inbounds", inboundService.getAllInbound());

             model.addAttribute("outbounds", outboundService.getAllOutbound());

            model.addAttribute("trucks", truckService.getAllTruck());

            model.addAttribute("binFeatures", binFeatureService.getAllBinFeature());

            model.addAttribute("suppliers", supplierService.getAllSuppliers());
        }
        return "postOutForm";
    }

    @GetMapping("/build")
    public String addNewOutForm(Model model ) {
        model.addAttribute("outForm", new OutForm());
       // model.addAttribute("inbounds", inboundService.getAllInbound());
          model.addAttribute("outbounds", outboundService.getAllOutbound());
        model.addAttribute("binsFeatures", binFeatureService.getAllBinFeature());
        model.addAttribute("trucks", truckService.getAllTruck());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "postOutForm";
    }

    @PostMapping("/build")
    public String registerOutForm(@Valid OutForm outForm, BindingResult result) {
        if (result.hasErrors()) {
            return "outDraw";
        } else {
            outFormService.saveOutForm(outForm);
            return "redirect:outDraw";
        }

    }

    @PostMapping("/saveOutForm")
    public String saveOutData( OutForm outForm) {
        //outFormService.getFormById(id);
        outFormService.updateOutForm(outForm);


        return "redirect:paginated";
    }
}