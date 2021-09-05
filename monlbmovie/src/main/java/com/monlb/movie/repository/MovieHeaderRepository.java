package com.monlb.movie.repository;

import com.monlb.movie.model.MovieHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieHeaderRepository extends JpaRepository<MovieHeader, Long> {

    // popular
    List<MovieHeader> findByPopular(boolean popular);

    // like
    List<MovieHeader> findByTitleContaining(String title);
}
