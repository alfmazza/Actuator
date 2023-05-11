package com.sa.metricas.service;

import com.sa.metricas.model.MemoryMetric;
import com.sa.metricas.model.MemoryMetric.Measurement;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MetricsService {
    
    
    @Autowired
    private WebClient webClient;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Scheduled(fixedDelay = 5000)
    public void getAndSaveMemoryUsage() {
        
        try {
            MemoryMetric memoryMetric = webClient.get()
        
                .uri("/actuator/metrics/jvm.memory.used")
                .retrieve()
                .bodyToMono(MemoryMetric.class)
                .block();
            Measurement measurement = memoryMetric.getMeasurements().get(0);
        
            double memoryUsage = measurement.getValue();
            String baseUnit = memoryMetric.getBaseUnit();
            String sql = "INSERT INTO metrics.memory_metrics (memory_usage, base_unit, measurement_time) VALUES (?, ?, ?)";
            Object[] args = {memoryUsage, baseUnit, new Timestamp(System.currentTimeMillis())};
            jdbcTemplate.update(sql, args);
        
            Thread.sleep(5000);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
