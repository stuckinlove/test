package com.monlb.movie.controller;

import com.monlb.movie.model.Customer;
import com.monlb.movie.model.CustomerFavorite;
import com.monlb.movie.model.MovieHeader;
import com.monlb.movie.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;


    /**
     * return that the user has previously marked as favorite.
     */
    @GetMapping("/favorites")
    public ResponseEntity favorites() {
        List<CustomerFavorite> favorites = searchService.getFavorites();
        return ResponseEntity.ok(favorites);
    }

    /**
     * add a favorite movie
     */
    @PostMapping("/favorite/{id}")
    public ResponseEntity favorite(@PathVariable Long id) {
        // テスト用のカスタマー(任意の1人)
        Customer customer = searchService.getCustomer();

        // 該当の映画がfavoritesに存在しているか
        Optional<CustomerFavorite> favoriteById = searchService.getFavoriteById(id);

        // 存在する映画コードチェック
        Optional<MovieHeader> movieById = searchService.getMovieById(id);

        // 既に追加されている　もしくは　存在しない映画コード
        if (favoriteById.isPresent() || !movieById.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        // save
        CustomerFavorite saved = searchService.saveCustomerFavorite(new CustomerFavorite(customer, movieById.get()));

        // save結果を返す
        return ResponseEntity.ok(saved);
    }

    /**
     * return popular movies or what the user searched for (should take apiKey)
     */
    @GetMapping("/movies")
    public ResponseEntity movies(@RequestParam(required = false) String search) {
        List<MovieHeader> result = null;

        if (search == null || search.isEmpty()) {
            // パラメータ未指定の場合、popular=true検索
            result = searchService.getMoviesByPopular();
        } else {
            // Like検索
            result = searchService.getMoviesByTitleContaining(search);
        }

        // Nullの場合、500を返す
        if (result == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        // 検索結果がない場合もそのまま返す
        return ResponseEntity.ok(result);
    }

    /**
     * return that specific movie in detail (should take apiKey)
     */
    @GetMapping("/movies/{id}")
    public ResponseEntity movie(@PathVariable Long id) {
        Optional<MovieHeader> movieById = searchService.getMovieById(id);
        if (movieById.isPresent()) {
            return ResponseEntity.ok(movieById.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

}
