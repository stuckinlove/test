package com.monlb.movie.service;

import com.monlb.movie.model.Customer;
import com.monlb.movie.model.CustomerFavorite;
import com.monlb.movie.model.MovieHeader;

import java.util.List;
import java.util.Optional;

public interface SearchService {

    /**
     * Customerを取得する
     * @return Customer
     */
    Customer getCustomer();

    /**
     * Favoritesを取得する
     * @return List<CustomerFavorite>
     */
    List<CustomerFavorite> getFavorites();

    /**
     * Idに該当するFavoritesを取得する
     * @param id
     * @return Optional<CustomerFavorite>
     */
    Optional<CustomerFavorite> getFavoriteById(Long id);

    /**
     * Idに該当するMovieを取得する
     * @param id
     * @return Optional<MovieHeader>
     */
    Optional<MovieHeader> getMovieById(Long id);

    /**
     * Favoriteに追加する
     * @param customerFavorite
     * @return CustomerFavorite
     */
    CustomerFavorite saveCustomerFavorite(CustomerFavorite customerFavorite);

    /**
     * (Like検索)該当するMovieデータを取得する
     * @param title
     * @return List<MovieHeader>
     */
    List<MovieHeader> getMoviesByTitleContaining(String title);

    /**
     * (Popular=true)Movieデータを取得する
     * @return List<MovieHeader>
     */
    List<MovieHeader> getMoviesByPopular();

}
