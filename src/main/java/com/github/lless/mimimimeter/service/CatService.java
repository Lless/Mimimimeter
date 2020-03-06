package com.github.lless.mimimimeter.service;

import com.github.lless.mimimimeter.domain.Cat;
import com.github.lless.mimimimeter.repo.CatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {
    @Autowired
    private CatRepo catRepo;

    public List<Cat> getCats(List<Long> exceptCats) {
        return catRepo.findAll();
    }

    public List<Cat> getCatsFromIds(List<Long> ids) { return catRepo.findAllById(ids); }
}
