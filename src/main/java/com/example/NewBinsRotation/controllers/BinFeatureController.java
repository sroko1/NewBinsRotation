package com.example.NewBinsRotation.controllers;

import com.example.NewBinsRotation.models.BinFeature;

import com.example.NewBinsRotation.models.Inbound;
import com.example.NewBinsRotation.services.BinFeatureService;
import com.example.NewBinsRotation.services.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/binFeatures")

public class BinFeatureController {

    @Autowired
    BinFeatureService binFeatureService;

    @Autowired
    InboundService inboundService;

    @RequestMapping("/list")
    public String getBins(Model model) {
        model.addAttribute("binsFeatures", binFeatureService.getAllBinFeature());
        return "dynamicBinFeatList";
    }

    @PostMapping("/delete")
    public String deleteBinFeature(@RequestParam Integer id, Model model) {
    binFeatureService.deleteBinFeature(id);
        model.addAttribute("binsFeatures", binFeatureService.getAllBinFeature());
        return "dynamicBinFeatList";
    }

    @PostMapping("/edit")
    public String editBinFeature(@RequestParam Integer id, Model model) {
        model.addAttribute("binFeature", binFeatureService.getBinFeatureById(id));
        model.addAttribute("inbounds", inboundService.getAllInbound());
        return "postBinInb";
    }

    @PostMapping("/save")
    public String saveBinFeature(BinFeature binFeature, @RequestParam Integer id) {
        binFeatureService.getBinFeatureById(id);
        binFeatureService.editBinFeature(binFeature);
        return "redirect:list";
    }

    @GetMapping("/addNew")
    public String addNewBinFeature(Model model) {
        model.addAttribute("binFeature", new BinFeature());
        return "postBinInb";
    }

    @RequestMapping("/paginated")
    public String getBinFeaturePaginated(Model model,
                                         @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(1);
        Page<BinFeature> binFeaturePage = binFeatureService
                .getBinFeaturePaginated(
                        PageRequest.of(currentPage - 1, 10)
                );

        model.addAttribute("binFeaturePage", binFeaturePage);

        int totalPages = binFeaturePage.getTotalPages();
        if (totalPages > 0) {

            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "index";
    }


}