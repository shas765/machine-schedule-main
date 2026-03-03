package com.scheduler.maintenance;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final MaintenanceRepository repository;
    private final MachineClient machineClient;

    public MaintenanceController(MaintenanceRepository repository,
                                 MachineClient machineClient) {
        this.repository = repository;
        this.machineClient = machineClient;
    }

    // ✅ Create maintenance task
    @PostMapping
    public Maintenance create(@RequestBody Maintenance maintenance) {

        // Check if machine exists
        machineClient.getMachine(maintenance.getMachineId());

        // OPTIONAL (recommended): Change machine status
        machineClient.updateStatus(
                maintenance.getMachineId(),
                "UNDER_MAINTENANCE"
        );

        return repository.save(maintenance);
    }

    // ✅ Get all maintenance tasks
    @GetMapping
    public List<Maintenance> getAll() {
        return repository.findAll();
    }

    // ✅ Get maintenance by ID
    @GetMapping("/{id}")
    public Maintenance getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found"));
    }

    // ✅ Update maintenance record properly
    @PutMapping("/{id}")
    public Maintenance update(@PathVariable Long id,
                              @RequestBody Maintenance updated) {

        Maintenance existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found"));

        existing.setMachineId(updated.getMachineId());
        existing.setDescription(updated.getDescription());
        existing.setDate(updated.getDate());
        existing.setNextDueDate(updated.getNextDueDate());

        return repository.save(existing);
    }

    // ✅ NEW — Query upcoming due tasks
    @GetMapping("/upcoming")
    public List<Maintenance> getUpcomingTasks() {

        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);

        return repository.findByNextDueDateBetween(today, nextWeek);
    }
}