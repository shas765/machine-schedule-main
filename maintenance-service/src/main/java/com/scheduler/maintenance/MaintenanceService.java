package com.scheduler.maintenance;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepository repository;

    public MaintenanceService(MaintenanceRepository repository) {
        this.repository = repository;
    }

    // Create maintenance
    public Maintenance create(Maintenance maintenance) {
        return repository.save(maintenance);
    }

    // Get all maintenance
    public List<Maintenance> getAll() {
        return repository.findAll();
    }

    // Get by ID
    public Maintenance getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found"));
    }

    // Update maintenance
    public Maintenance update(Long id, Maintenance updated) {
        Maintenance existing = getById(id);

        existing.setMachineId(updated.getMachineId());
        existing.setDescription(updated.getDescription());
        existing.setDate(updated.getDate());
        existing.setNextDueDate(updated.getNextDueDate());

        return repository.save(existing);
    }

    // Delete maintenance
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // ✅ Upcoming due tasks
    public List<Maintenance> getUpcomingTasks() {

        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);

        return repository.findByNextDueDateBetween(today, nextWeek);
    }
}