package com.scheduler.maintenance;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "machine-service")
public interface MachineClient {

    @GetMapping("/machines/{id}")
    Object getMachine(@PathVariable("id") Long id);

    @PutMapping("/machines/{id}/status")
    void updateStatus(@PathVariable("id") Long id,
                      @RequestBody String status);
}