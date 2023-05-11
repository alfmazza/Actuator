package com.sa.metricas.repository;

import com.sa.metricas.model.MemoryMetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryMetricsRepository extends JpaRepository<MemoryMetricsEntity, Long> {
}