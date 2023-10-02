package org.binaracademy.bioskopbackend.service;

import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.model.response.MovieResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface MovieService {

    List<Movie> getMovieCurrentlyShowing(Date date);
    List<Movie> getMovieCurrentlyShowingWithFilterSeat(Date date, String seat);
    Boolean addNewMovie(Movie movie);
    MovieResponse getMovieDetail(String selectedMovieName);

}
