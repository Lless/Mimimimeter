package com.github.lless.mimimimeter.repo;

import com.github.lless.mimimimeter.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatRepo extends JpaRepository<Cat, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM cat where id not in :ids order by rand() limit 2")
    List<Cat> getRandomCats(@Param("ids") Iterable<Long> exceptCatsWithId);

    @Query(nativeQuery = true, value = "SELECT * FROM cat order by rand() limit 2")
    List<Cat> getRandomCats();
}
