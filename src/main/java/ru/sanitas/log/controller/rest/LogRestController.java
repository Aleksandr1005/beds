package ru.sanitas.log.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sanitas.log.controller.dto.LogRequestDto;
import ru.sanitas.log.domain.Log;
import ru.sanitas.log.service.LogService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LogRestController {
    private final LogService logService;

    @PostMapping
    public List<Log> findAll(@RequestBody LogRequestDto logRequestDto) {
        return logService.findAll(logRequestDto);
    }

    @GetMapping("notes")
    public List<Log> findNotes(@RequestParam(value = "patient_id", required = false) Long patientId,
                               @RequestParam(value = "debt_id", required = false) Long debtId) {
        return logService.findNotes(patientId, debtId);
    }

    @GetMapping("emails")
    public List<Log> findEmails(@RequestParam(value = "object", required = false) String object) {
        return logService.findEmails(object);
    }
}
