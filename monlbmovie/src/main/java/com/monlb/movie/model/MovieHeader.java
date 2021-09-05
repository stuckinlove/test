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
@Table(name = "movie_header")
public class MovieHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_header_id")
    private Long id;
    private String title;
    private Boolean popular;

    @OneToOne
    @JoinColumn(name = "movie_detail_id")
    private MovieDetail movieDetail;
}
