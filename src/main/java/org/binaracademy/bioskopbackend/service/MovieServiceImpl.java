package org.binaracademy.bioskopbackend.service;

import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.model.response.MovieResponse;
import org.binaracademy.bioskopbackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getMovieCurrentlyShowing(Date date) {
        Date requestDate = Optional.ofNullable(date)
                .orElse(new Date());
        return movieRepository.getMoviesOnSchedule(requestDate);
    }

    @Override
    public List<Movie> getMovieCurrentlyShowingWithFilterSeat(Date date, String seat) {
        return null;
    }

    @Override
    public Boolean addNewMovie(Movie movie) {
        return Optional.ofNullable(movie)
                .map(newMovie -> movieRepository.save(movie))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    public MovieResponse getMovieDetail(String selectedMovieName) {
        // TODO : ambil data movie, berdasarkan nama movie nya
        return Optional.ofNullable(movieRepository.findByName(selectedMovieName))
                .map(movie -> MovieResponse.builder()
                        .movieName(movie.getName())
                        .movieCode(movie.getMovieCode())
                        .posterImage(movie.getPosterImage())
                        .synopsis(movie.getSynopsis())
                        .schedules(movie.getSchedules())
                        .build())
                .orElse(null);
    }

}
