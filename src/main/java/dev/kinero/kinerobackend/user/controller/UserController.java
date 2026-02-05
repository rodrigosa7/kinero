package dev.kinero.kinerobackend.user.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final MeterRegistry meterRegistry;

    public UserController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/ping")
    Map<String, String> ping() {
        meterRegistry.counter("api.ping.calls").increment();
        return Map.of(
            "status", "ok",
            "timestamp", Instant.now().toString()
        );
    }
}
