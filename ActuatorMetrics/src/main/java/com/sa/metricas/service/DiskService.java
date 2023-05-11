package com.sa.metricas.service;

import com.sa.metricas.model.DiskMetric;
import com.sa.metricas.model.DiskMetric.Measurement;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DiskService {
    
    @Autowired
    private WebClient webClient;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Scheduled(fixedDelay = 5000)
    public void getAndSaveDiskFree() {
        
        try {
            DiskMetric diskMetric = webClient.get()
                    
                    .uri("/actuator/metrics/disk.free")
                    .retrieve()
                    .bodyToMono(DiskMetric.class)
                    .block();
            Measurement measurement = diskMetric.getMeasurements().get(0);
            
            double freeDisk = measurement.getValue();
            String baseUnit = diskMetric.getBaseUnit();
            
            String sql = "INSERT INTO metrics.disk_free (disk_space, base_unit, measurement_time) VALUES (?, ?, ?)";
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date(System.currentTimeMillis());
            String dateTime = formatter.format(now);
            Object[] args = {freeDisk, baseUnit, dateTime};
            jdbcTemplate.update(sql, args);
            
            Thread.sleep(5000);
            
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    
    }
}
