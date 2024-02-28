package ru.sanitas.log.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LogController {
    @GetMapping
    public String getPage(@RequestParam(value = "mode", required = false) String mode,
                          @RequestParam(value = "patient_id", required = false) Long patientId,
                          @RequestParam(value = "debt_id", required = false) Long debtId,
                          @RequestParam(value = "object", required = false) String object,
                          Model model) {
        model.addAttribute("mode", mode);
        model.addAttribute("patientId", patientId);
        model.addAttribute("debtId", debtId);
        model.addAttribute("object", object);
        return "log";
    }
}
