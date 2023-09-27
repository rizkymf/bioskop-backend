package org.binaracademy.bioskopbackend.service;

import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

//    public MovieServiceImpl(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }

    @Override
    public List<Movie> getMovieCurrentlyShowing(Date date) {
        Date requestDate = Optional.ofNullable(date)
                .orElse(new Date());
        return movieRepository.getMovies().stream()
                .filter(movie -> movie.getSchedule().after(requestDate))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean addNewMovie(Movie movie) {
        return Optional.ofNullable(movie)
                .map(newMovie -> movieRepository.addMovie(movie))
                .orElse(Boolean.FALSE);
    }

    @Override
    public Movie getMovieDetail(int movieIndex) {
        return movieRepository.getMovies().get(movieIndex - 1);
    }

}
