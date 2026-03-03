package com.scheduler.machine;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/machines")
public class MachineController {

    private final MachineService service;

    public MachineController(MachineService service) {
        this.service = service;
    }

    // Create Machine
    @PostMapping
    public Machine create(@Valid @RequestBody Machine machine) {
        return service.createMachine(machine);
    }

    // Get All Machines
    @GetMapping
    public List<Machine> getAll() {
        return service.getAllMachines();
    }

    // Get Machine By ID
    @GetMapping("/{id}")
    public Machine getById(@PathVariable Long id) {
        return service.getMachine(id);
    }

    // Update Machine
    @PutMapping("/{id}/status")
    public Machine updateStatus(@PathVariable Long id,
                                @RequestBody String status) {
        return service.updateStatus(id, status);
    }

    // Delete Machine (optional but useful)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteMachine(id);
    }
}