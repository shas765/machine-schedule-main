package com.scheduler.machine;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MachineService {

    private final MachineRepository repository;

    public MachineService(MachineRepository repository) {
        this.repository = repository;
    }

    public Machine createMachine(Machine machine) {

        if (machine.getStatus() == null) {
            machine.setStatus("ACTIVE");
        }
    
        return repository.save(machine);
    }
    public List<Machine> getAllMachines() {
        return repository.findAll();
    }

    public Machine getMachine(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine not found"));
    }

    public Machine updateMachine(Long id, Machine machine) {
        Machine existing = getMachine(id);

        existing.setName(machine.getName());
        existing.setType(machine.getType());

        return repository.save(existing);
    }

    public Machine updateStatus(Long id, String status) {

        Machine machine = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine not found"));
    
        machine.setStatus(status);
    
        return repository.save(machine);
    }
    
    public void deleteMachine(Long id) {
        repository.deleteById(id);
    }
}