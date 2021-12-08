package com.example.NewBinsRotation.controllers;

import com.example.NewBinsRotation.models.BinFeature;
import com.example.NewBinsRotation.models.Truck;
import com.example.NewBinsRotation.services.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trucks")
public class TruckController {


    private final TruckService truckService;


    private final FormService formService;
    private final OutFormService outFormService;


    public TruckController(TruckService truckService, SupplierService supplierService, FormService formService, OutFormService outFormService) {
        this.truckService = truckService;

        this.formService = formService;
        this.outFormService = outFormService;
    }

    @RequestMapping("/list")
    public String getTrucks(Model model) {
        model.addAttribute("trucks", truckService.getAllTruck());
        return "dynamicTruckList";


    }

    @PostMapping("/delete")
    public String deleteTruck(@RequestParam int id, Model model) {
        truckService.deleteTruck(id);
        model.addAttribute("trucks", truckService.getAllTruck());
        return "dynamicTruckList";
    }

    @RequestMapping("/paginated")
    public String getTrucksPaginated(Model model,
                                     @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        Page<Truck> truckPage = truckService
                .getTrucksPaginated(
                        PageRequest.of(currentPage - 1, 10)
                );

        model.addAttribute("truckPage", truckPage);

        int totalPages = truckPage.getTotalPages();
        if (totalPages > 0) {

            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "indexTruck";
    }

    @PostMapping("/edit")
    public String editTruck(@RequestParam int id, Model model) {
        model.addAttribute("truck", truckService.getTruckById(id));
        model.addAttribute("form", formService.getAllForm());
        model.addAttribute("out_form", outFormService.getAllOutForm());
        model.addAttribute("trucks", truckService.getAllTruck());
        return "postTruck";
    }

    @PostMapping("/save")
    public String saveTruck(Truck truck, @RequestParam int id) {
        truckService.getTruckById(id);
        truckService.editTruck(truck);
        return "redirect:list";
    }

    @GetMapping("/addNew")
    public String addNewTruck(Model model) {
        model.addAttribute("truck", new Truck());
        return "postTruck";
    }

}



