package com.example.NewBinsRotation.controllers;

import com.example.NewBinsRotation.models.PortalUser;
import com.example.NewBinsRotation.services.PortalUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class PortalUserController {

    private final PortalUserService portalUserService;

    public PortalUserController(PortalUserService portalUserService) {
        this.portalUserService = portalUserService;
    }

    @GetMapping("/list")
    public String usersList(Model model,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size")Optional<Integer> size
    ) {

        int currentPage = page.orElse(1);
        int currentSize = size.orElse(5);

        Page<PortalUser> portalUserPage = portalUserService.findAllPaginated(
                PageRequest.of(currentPage-1, currentSize)
        );

        model.addAttribute("size", currentSize);
        model.addAttribute("portalUserPage", portalUserPage);

        int totalPages = portalUserPage.getTotalPages();
        if (totalPages>0) {
            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "user";
    }

    //np. localhost:8080/user/list/lastName?startsWith=N
    @GetMapping("/list/lastName")
    public String usersListByLastName(Model model,
                                      @RequestParam("page")Optional<Integer> page,
                                      @RequestParam("size")Optional<Integer> size,
                                      @RequestParam("startsWith") String startsWith
    ) {

        int currentPage = page.orElse(1);
        int currentSize = size.orElse(5);

        Page<PortalUser> portalUserPage = portalUserService.findByLastNameBeginsWith(
                startsWith,
                PageRequest.of(currentPage-1, currentSize)
        );

        model.addAttribute("size", currentSize);
        model.addAttribute("portalUserPage", portalUserPage);

        int totalPages = portalUserPage.getTotalPages();
        if (totalPages>0) {
            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "user";
    }

    @GetMapping("/register")
    public String userRegistration(Model model) {
        model.addAttribute("portalUser", new PortalUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid PortalUser portalUser,
                               BindingResult result) {
        if(portalUserService.findByLogin(portalUser.getLogin()) != null) {
            result.rejectValue("login", "Duplicate.portalUser.login");
        }
        if (result.hasErrors()) {
            return "register";
        } else {
            portalUserService.save(portalUser);
            return "redirect:/login";
        }
    }
}
