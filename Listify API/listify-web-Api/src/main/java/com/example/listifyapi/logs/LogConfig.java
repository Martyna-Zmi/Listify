package com.example.listifyapi.logs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Configuration
public class LogConfig {
    @Bean
    File createFile(){
        return new File("listify-log.log");
    }
}
