package com.greglturnquist.learningspringboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    @Value("${http.client.chapters.service.url}")
    private String chaptersServiceUrl;


    @GetMapping
    public String greeting(@RequestParam(required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("name", name);
        ResponseEntity<Chapter[]> responseEntity = new RestTemplate().getForEntity(chaptersServiceUrl, Chapter[].class);
        model.addAttribute("chapters", responseEntity.getBody());
        return "chapters";
    }

}
