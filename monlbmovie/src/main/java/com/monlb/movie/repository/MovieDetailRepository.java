package com.monlb.movie.repository;

import com.monlb.movie.model.MovieDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDetailRepository extends JpaRepository<MovieDetail, Long> {
}
