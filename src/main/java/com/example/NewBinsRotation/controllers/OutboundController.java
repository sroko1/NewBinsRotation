package com.example.NewBinsRotation.controllers;

import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.models.Outbound;
import com.example.NewBinsRotation.services.OutboundService;
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
@RequestMapping("/outbounds")
public class OutboundController {

    private final OutboundService outboundService;

    public OutboundController(OutboundService outboundService) {
        this.outboundService = outboundService;
    }


    @RequestMapping("/list")
    public String getOutbounds(Model model) {
        model.addAttribute("outbounds", outboundService.getAllOutbound());
        return "indexOutbound";
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
