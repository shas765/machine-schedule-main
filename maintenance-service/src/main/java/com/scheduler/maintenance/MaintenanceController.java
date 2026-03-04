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

    
    @PostMapping
    public Maintenance create(@RequestBody Maintenance maintenance) {

        
        machineClient.getMachine(maintenance.getMachineId());

        
        machineClient.updateStatus(
                maintenance.getMachineId(),
                "UNDER_MAINTENANCE"
        );

        return repository.save(maintenance);
    }

    
    @GetMapping
    public List<Maintenance> getAll() {
        return repository.findAll();
    }

    
    @GetMapping("/{id}")
    public Maintenance getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found"));
    }

    
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


    @GetMapping("/upcoming")
    public List<Maintenance> getUpcomingTasks() {

        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);

        return repository.findByNextDueDateBetween(today, nextWeek);
    }
}