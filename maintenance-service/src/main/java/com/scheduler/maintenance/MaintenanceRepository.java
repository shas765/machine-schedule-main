package com.scheduler.maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface MaintenanceRepository 
        extends JpaRepository<Maintenance, Long> {

    // ✅ For upcoming due tasks
    List<Maintenance> findByNextDueDateBetween(
            LocalDate startDate,
            LocalDate endDate);
}