package ru.sanitas.log.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sanitas.log.controller.dto.LogRequestDto;
import ru.sanitas.log.domain.Log;
import ru.sanitas.log.repository.LogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    public List<Log> findAll(LogRequestDto logRequestDto) {
        return logRepository.findAll(
                logRequestDto.getDateFrom().atTime(0, 0, 0),
                logRequestDto.getDateTo().atTime(23, 59, 59),
                logRequestDto.getPalace(), logRequestDto.getBed(), logRequestDto.getObject(),
                logRequestDto.getStatus(), Pageable.ofSize(1000));
    }
}
