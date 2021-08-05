package com.example.NewBinsRotation.controllers;


import com.example.NewBinsRotation.models.BinFeature;
import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.models.Truck;
import com.example.NewBinsRotation.services.BinFeatureService;
import com.example.NewBinsRotation.services.InboundService;
import com.example.NewBinsRotation.services.TruckService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inbounds")
public class InboundController {


   private final TruckService truckService;

    private final BinFeatureService binFeatureService;

    private final InboundService inboundService;

    public InboundController(TruckService truckService, BinFeatureService binFeatureService, InboundService inboundService) {
        this.truckService = truckService;
        this.binFeatureService = binFeatureService;
        this.inboundService = inboundService;
    }

    @RequestMapping("/list")
    public String getInbounds(Model model) {
        model.addAttribute("inbounds", inboundService.getAllInbound());
        return "dynamicInboundList";
    }

    @RequestMapping("/list/{id}")
    public String getPointedInbound(@PathVariable Integer id, Model model){
        model.addAttribute("inbounds", inboundService.getInboundById(id));
        return"dynamicInboundList";
    }

    @PostMapping("/delete")
    public String deleteInbound(@RequestParam Integer id, Model model){
        inboundService.deleteInbound(id);
        model.addAttribute("inbounds", inboundService.getAllInbound());
        return "dynamicInboundList";
    }

    @PostMapping("/edit")
    public String editInbound(@RequestParam Integer id, Model model){
       model.addAttribute("inbound", inboundService.getInboundById(id));
       model.addAttribute("truck", truckService.getTruckById(id));
       model.addAttribute("binsFeatures",binFeatureService.getBinFeatureById(id));
       return "postInbound";
    }

    @PostMapping("/save")
    public String saveInbound(Inbound inbound){
        inboundService.editInbound(inbound);
        return "redirect:list";
    }

    @GetMapping("/addNew")
    public  String addNewInbound(Model model){
        model.addAttribute("inbound",new Inbound());
        return "postInbound";
    }

    @RequestMapping("/form")
    public String addNewData(Model model){
        model.addAttribute("inbound", new Inbound());

        model.addAttribute("inbounds", inboundService.getAllInbound());
        model.addAttribute("trucks", truckService.getAllTruck());
        model.addAttribute("binsFeatures", binFeatureService.getAllBinFeature());
        return "postForm";
    }

    @PostMapping("/saveForm")
    public String saveData( Inbound inbound, BinFeature binFeature, Truck truck ){
       inboundService.editInbound(inbound);
        binFeatureService.editBinFeature(binFeature);
        truckService.editTruck(truck);
        return "redirect:list";
    }



    @RequestMapping("/paginated")
    public String getInboundPaginated(Model model,
                                         @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        Page<Inbound> inboundPage = inboundService
                .getAllInboundPaginated(
                        PageRequest.of(currentPage - 1, 10)
                );

        model.addAttribute("inboundPage", inboundPage);

        int totalPages = inboundPage.getTotalPages();
        if (totalPages > 0) {

            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "indexInbound";
    }

    @RequestMapping ("/paginatedList")
    public String getInboundListPaginated(Model model,
                                          @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        Page<Inbound> inboundPage = inboundService
                .getInboundListPaginated(
                        PageRequest.of(currentPage - 1, 10)
                );

        model.addAttribute("inboundPage", inboundPage);

        int totalPages = inboundPage.getTotalPages();
        if (totalPages > 0) {

            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "inboundList";
    }


}
