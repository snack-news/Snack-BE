package com.snack.news.controller;

import com.snack.news.domain.Corporation;
import com.snack.news.service.CorporationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corp")
public class CorporationController {
    private final CorporationService corporationService;

    public CorporationController(CorporationService corporation) {
        this.corporationService = corporation;
    }

    @GetMapping
    public List<Corporation> corporations() {
        return corporationService.getCorporation();
    }

    @PutMapping("/{id}")
    public Corporation updateCorporation (@PathVariable("id") Long id, @RequestBody Corporation corporation) {
        return corporationService.updateCorporation(id, corporation);
    }

    @PostMapping
    public Corporation createCorporation (@RequestBody Corporation corporation) {
        return corporationService.createCorporation(corporation);
    }

    @DeleteMapping("/{id}")
    public Corporation deleteCorporation (@PathVariable("id") Long id) {
        return corporationService.deleteCorporation(id);
    }
}
