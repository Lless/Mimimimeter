package com.github.lless.mimimimeter.service;

import com.github.lless.mimimimeter.domain.Cat;
import com.github.lless.mimimimeter.repo.CatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatService {
    @Autowired
    private CatRepo catRepo;

    public Cat getCat() {
        return catRepo.getOne(1L);
    }
}
