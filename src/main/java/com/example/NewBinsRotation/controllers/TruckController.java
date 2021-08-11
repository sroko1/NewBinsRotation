package com.example.NewBinsRotation.controllers;

import com.example.NewBinsRotation.models.BinFeature;
import com.example.NewBinsRotation.models.Truck;
import com.example.NewBinsRotation.services.InboundService;
import com.example.NewBinsRotation.services.OutboundService;
import com.example.NewBinsRotation.services.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    TruckService truckService;
    @Autowired
    InboundService inboundService;
    @Autowired
    OutboundService outboundService;

    @RequestMapping("/list")
    public String getTrucks(Model model){
        model.addAttribute("trucks", truckService.getAllTruck());
        return "dynamicTruckList";
    }
    @PostMapping("/delete")
    public String deleteTruck(@RequestParam int id, Model model) {
      truckService.deleteTruck(id);
        model.addAttribute("trucks", truckService.getAllTruck());
        return "dynamicTruckList";
    }

    @PostMapping("/edit")
    public String editTruck(@RequestParam int id, Model model) {
        model.addAttribute("truck", truckService.getTruckById(id));
        model.addAttribute("inbounds", inboundService.getAllInbound());
        model.addAttribute("outbounds",outboundService.getAllOutbound());
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



