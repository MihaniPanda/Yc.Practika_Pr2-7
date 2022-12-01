package com.example.demoModels.Controllers;


import com.example.demoModels.Models.Employee;
import com.example.demoModels.Models.Evaporator;
import com.example.demoModels.Models.Pod;
import com.example.demoModels.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AllController {

    @Autowired
    private PodRepository podRepository;

    @Autowired
    private EvaporatorRepository evaporatorRepository ;

    @GetMapping("/pod")
    public String podMain(Model model)
    {
        Iterable<Pod> pods = podRepository.findAll();
        model.addAttribute("pods",pods);
        return "Pod/Pod";
    }

    //Добавление Pod систем
    @GetMapping("/pod/add")
    public String podAddView(@ModelAttribute( "pods") Pod pod)
    {
        return "Pod/PodCreate";
    }

    @PostMapping("/pod/add")
    @PreAuthorize("hasAnyAuthority('HUMANOID')")
    public String podAdd(@ModelAttribute("pods") @Valid Pod pod,
                              BindingResult bindingResuit)
    {
        if(bindingResuit.hasErrors()){return "Pod/PodCreate";}
        podRepository.save(pod);
        return "redirect:/pod";
    }


    @GetMapping("/evaporator")
    public String evaporatorMain(Model model)
    {
        Iterable<Evaporator> evaporators = evaporatorRepository.findAll();
        model.addAttribute("evaporators",evaporators);
        return "Evaporator/Evaporator";
    }

    //Добавление испарителей
    @GetMapping("/evaporator/add")
    public String evaporatorAddView(@ModelAttribute( "evaporators") Evaporator evaporator)
    {
        return "Evaporator/EvaporatorCreate";
    }

    @PostMapping("/evaporator/add")
    @PreAuthorize("hasAnyAuthority('HUMANOID')")
    public String evaporatorAdd(@ModelAttribute("evaporators") @Valid Evaporator evaporator,
                         BindingResult bindingResuit)
    {
        if(bindingResuit.hasErrors()){return "Evaporator/EvaporatorCreate";}
        evaporatorRepository.save(evaporator);
        return "redirect:/evaporator";
    }


    @GetMapping("/compatibility")
    public String compatibilityMain(Model model)
    {
        Iterable<Pod> pods = podRepository.findAll();
        Iterable<Evaporator> evaporators = evaporatorRepository.findAll();
        model.addAttribute("pods", pods);
        model.addAttribute("evaporators", evaporators);
        return "Compatibility/Compatibility";
    }

    @GetMapping("compatibility/add")
    public String compatibilityAddGet(Model model) {
        Iterable<Pod> pods = podRepository.findAll();
        Iterable<Evaporator> evaporators = evaporatorRepository.findAll();
        model.addAttribute("pods", pods);
        model.addAttribute("evaporators", evaporators);
        return "Compatibility/CompatibilityCreate";
    }
    @PostMapping("compatibility/add")
    @PreAuthorize("hasAnyAuthority('HUMANOID')")
    public String compatibilityAddPost(@RequestParam String name, @RequestParam String type) {
        Pod podss = podRepository.findByName(name);
        Evaporator evaporatorss = evaporatorRepository.findByType(type);
        podss.getEvaporators().add(evaporatorss);
        podRepository.save(podss);
        // computerRepository.save(computer);
        return "redirect:/compatibility";
    }

}
