package com.example.listifyapi.logs;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LoggerService {
    private final File file;
    public InputStreamResource getLogFile() throws IOException{
        return new InputStreamResource(new FileInputStream(file));
    }
}
