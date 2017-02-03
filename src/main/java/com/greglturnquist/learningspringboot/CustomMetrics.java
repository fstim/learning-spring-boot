package com.greglturnquist.learningspringboot;

import org.springframework.boot.actuate.autoconfigure.ExportMetricReader;
import org.springframework.boot.actuate.metrics.repository.InMemoryMetricRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by fstimpfling on 02/02/2017.
 */
@Component
public class CustomMetrics {

    @Bean
    @ExportMetricReader
    InMemoryMetricRepository inMemoryMetricRepository() {
        return new InMemoryMetricRepository();
    }
}
