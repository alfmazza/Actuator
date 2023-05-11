package com.sa.metricas.repository;

import com.sa.metricas.model.DiskMetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiskMetricRepository extends JpaRepository<DiskMetricEntity, Long>{
    
}
