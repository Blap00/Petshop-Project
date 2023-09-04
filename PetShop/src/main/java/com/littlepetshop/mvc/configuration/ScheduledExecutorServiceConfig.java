package com.littlepetshop.mvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class ScheduledExecutorServiceConfig {
    
    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        // Crea y devuelve una instancia de ScheduledExecutorService
        return Executors.newScheduledThreadPool(1); // Puedes ajustar el tamaño del pool según tus necesidades
    }
}
