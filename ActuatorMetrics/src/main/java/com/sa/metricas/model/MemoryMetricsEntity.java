package com.sa.metricas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "memory_metrics")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemoryMetricsEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "measurement_time")
    private LocalDateTime measurementTime;
    
    @Column(name = "memory_usage")
    private Double memoryUsage;
    
    @Column(name = "base_unit")
    private String baseUnit;
}
