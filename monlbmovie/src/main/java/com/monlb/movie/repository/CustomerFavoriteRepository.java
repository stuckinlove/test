package com.monlb.movie.repository;

import com.monlb.movie.model.CustomerFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerFavoriteRepository extends JpaRepository<CustomerFavorite, Long> {

    Optional<CustomerFavorite> findByMovieHeader_Id(Long id);
}
