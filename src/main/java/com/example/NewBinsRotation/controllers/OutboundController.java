package com.example.NewBinsRotation.controllers;

import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.models.Outbound;
import com.example.NewBinsRotation.services.OutboundService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/outbounds")
public class OutboundController {

    private final OutboundService outboundService;

    public OutboundController(OutboundService outboundService) {
        this.outboundService = outboundService;
    }


    @RequestMapping("/list")
    public String getOutbounds(Model model) {
        model.addAttribute("outbounds", outboundService.getAllOutbound());
        return "dynamicOutboundList";
    }
    @RequestMapping("/list/{id}")
    public String getPointedOutbound(@PathVariable int id, Model model){
        model.addAttribute("outbounds", outboundService.getOutboundById(id));
        return"dynamicOutboundList";
    }

    @PostMapping("/delete")
    public String deleteOutbound(@RequestParam int id, Model model){
        outboundService.deleteOutbound(id);
        model.addAttribute("outbounds", outboundService.getAllOutbound());
        return "dynamicOutboundList";
    }

    @PostMapping("/edit")
    public String editOutbound(@RequestParam int id, Model model){
        model.addAttribute("outbound", outboundService.getOutboundById(id));
        return "postOutbound";
    }

    @PostMapping("/save")
    public String saveOutbound(Outbound outbound){
        outboundService.editOutbound(outbound);
        return "redirect:list";
    }

    @GetMapping("/addNew")
    public  String addNewOutbound(Model model){
        model.addAttribute("outbound",new Outbound());
        return "postOutbound";
    }

    @RequestMapping("/paginated")
    public String getOutboundPaginated(Model model,
                                         @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        Page<Outbound> outboundPage = outboundService
                .getAllOutboundPaginated(
                        PageRequest.of(currentPage - 1, 10)
                );

        model.addAttribute("outboundPage", outboundPage);

        int totalPages = outboundPage.getTotalPages();
        if (totalPages > 0) {

            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "indexOutbound";
    }
}
