package com.monlb.movie.service;

import com.monlb.movie.model.Customer;
import com.monlb.movie.model.CustomerFavorite;
import com.monlb.movie.model.MovieHeader;
import com.monlb.movie.repository.CustomerFavoriteRepository;
import com.monlb.movie.repository.CustomerRepository;
import com.monlb.movie.repository.MovieHeaderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    private CustomerRepository customerRepository;
    private CustomerFavoriteRepository customerFavoriteRepository;
    private MovieHeaderRepository movieHeaderRepository;

    @Autowired
    public SearchServiceImpl(CustomerRepository customerRepository, CustomerFavoriteRepository customerFavoriteRepository, MovieHeaderRepository movieHeaderRepository) {
        this.customerRepository = customerRepository;
        this.customerFavoriteRepository = customerFavoriteRepository;
        this.movieHeaderRepository = movieHeaderRepository;
    }

    @Override
    public Customer getCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.get(0);
    }

    @Override
    public List<CustomerFavorite> getFavorites() {
        List<CustomerFavorite> favorites = customerFavoriteRepository.findAll();
        return favorites;
    }

    @Override
    public Optional<CustomerFavorite> getFavoriteById(Long id) {
        Optional<CustomerFavorite> cf = customerFavoriteRepository.findByMovieHeader_Id(id);
        return cf;
    }

    @Override
    public Optional<MovieHeader> getMovieById(Long id) {
        Optional<MovieHeader> movieHeader = movieHeaderRepository.findById(id);
        return movieHeader;
    }

    @Override
    public CustomerFavorite saveCustomerFavorite(CustomerFavorite customerFavorite) {
        CustomerFavorite save = customerFavoriteRepository.save(customerFavorite);
        return save;
    }

    @Override
    public List<MovieHeader> getMoviesByTitleContaining(String title) {
        return movieHeaderRepository.findByTitleContaining(title);
    }

    @Override
    public List<MovieHeader> getMoviesByPopular() {
        return movieHeaderRepository.findByPopular(true);
    }
}
