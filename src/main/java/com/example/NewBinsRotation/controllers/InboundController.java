package com.example.NewBinsRotation.controllers;


import com.example.NewBinsRotation.models.BinFeature;
import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.services.InboundService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inbounds")
public class InboundController {

    private final InboundService inboundService;

    public InboundController(InboundService inboundService) {
        this.inboundService = inboundService;
    }

    @RequestMapping("/list")
    public String getInbounds(Model model) {
        model.addAttribute("inbounds", inboundService.getAllInbound());
        return "indexInbound";
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
}
