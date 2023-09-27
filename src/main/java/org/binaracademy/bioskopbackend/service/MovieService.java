package org.binaracademy.bioskopbackend.service;

import org.binaracademy.bioskopbackend.model.Movie;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface MovieService {

    List<Movie> getMovieCurrentlyShowing(Date date);
    Boolean addNewMovie(Movie movie);
    Movie getMovieDetail(int movieIndex);

}
