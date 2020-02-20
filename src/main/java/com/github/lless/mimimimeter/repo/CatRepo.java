package com.github.lless.mimimimeter.repo;

import com.github.lless.mimimimeter.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepo extends JpaRepository<Cat, Long> {
}
