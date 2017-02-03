package com.greglturnquist.learningspringboot;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.actuate.metrics.repository.InMemoryMetricRepository;
import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChapterController {
    private final ChapterRepository repository;
    private final CounterService counterService;
    private final GaugeService gaugeService;
    private final InMemoryMetricRepository inMemoryMetricRepository;

    public ChapterController(ChapterRepository repository, CounterService counterService, GaugeService gaugeService, InMemoryMetricRepository inMemoryMetricRepository) {
        this.repository = repository;
        this.counterService = counterService;
        this.gaugeService = gaugeService;
        this.inMemoryMetricRepository = inMemoryMetricRepository;
    }

    @GetMapping("/chapters")
    public Iterable<Chapter> listing() {
        counterService.increment("chapters.service");
        Iterable<Chapter> chapters = repository.findAll();
        gaugeService.submit("chapters.last.count", chapters.spliterator().getExactSizeIfKnown());
        inMemoryMetricRepository.increment(new Delta<Number>("chapters.total", chapters.spliterator().getExactSizeIfKnown()));
        chapters.forEach(System.out::println);
        return chapters;
    }

}