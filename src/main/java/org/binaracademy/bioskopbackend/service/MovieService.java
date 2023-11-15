package org.binaracademy.bioskopbackend.service;

import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.model.response.InvoiceResponse;
import org.binaracademy.bioskopbackend.model.response.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public interface MovieService {

    List<MovieResponse> getAllMovie();
    List<Movie> getAllMovieOri();
    List<Movie> getMovieCurrentlyShowing(Date date);
    List<Movie> getMovieCurrentlyShowingWithFilterSeat(Date date, String seat);
    void addNewMovie(Movie movie);
    MovieResponse getMovieDetail(String selectedMovieName);
    Page<Movie> getMoviePaged(int page);
    Future<Boolean> submitMovie(Movie movie) throws InterruptedException;
    Boolean updateMovieName(String oldName, String newName);
    Boolean deleteMovieFromName(String name);
    Boolean updateMovie(String oldMovieId, Movie newMovie);
    CompletableFuture<MovieResponse> getMovieDetailAsync(String selectedMovieName);
    InvoiceResponse getMovieDetailFuture(String selectedMovieName, String username) throws ExecutionException, InterruptedException;
}
