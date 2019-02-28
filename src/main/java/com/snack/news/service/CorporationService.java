package com.snack.news.service;

import com.snack.news.domain.Corporation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CorporationService {
    public List<Corporation> getCorporation () {
        Corporation corp = new Corporation();
        corp.setId(123L);
        corp.setImageLink("https//www.naver.com");
        corp.setName("martin");

        List<Corporation> corporationList = new ArrayList<>();
        corporationList.add(corp);

        return corporationList;
    }

    public Corporation updateCorporation (Long id, Corporation corporation) {
        return null;
    }

    public Corporation createCorporation (Corporation corporation) {
        return null;
    }

    public Corporation deleteCorporation (Long id) {
        return null;
    }
}
