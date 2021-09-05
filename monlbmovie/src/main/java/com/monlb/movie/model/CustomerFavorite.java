package com.monlb.movie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_favorite")
public class CustomerFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_favorite_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "movie_header_id")
    private MovieHeader movieHeader;

    public CustomerFavorite(Customer customer, MovieHeader movieHeader) {
        this.customer = customer;
        this.movieHeader = movieHeader;
    }
}
