package com.example.listifyapi.logs;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("listify/logs")
@RequiredArgsConstructor
public class LoggerController {
    private final LoggerService loggerService;
    @GetMapping(value = "download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity getLogs() throws IOException{
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment;filename="
                + loggerService.getFileName())
                .contentLength(loggerService.getFileLength())
                .body(loggerService.getLogFile());
    }
}
