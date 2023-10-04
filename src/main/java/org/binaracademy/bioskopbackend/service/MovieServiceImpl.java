package org.binaracademy.bioskopbackend.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.model.response.MovieResponse;
import org.binaracademy.bioskopbackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getMovieCurrentlyShowing(Date date) {
        Date requestDate = Optional.ofNullable(date)
                .orElse(new Date());
        List<Movie> movies = movieRepository.getMoviesOnSchedule(requestDate);
        return movies;
    }

    @Override
    public List<Movie> getMovieCurrentlyShowingWithFilterSeat(Date date, String seat) {
        return null;
    }

    @Override
    public Boolean addNewMovie(Movie movie) {
        log.info("Trying to save new movie");
        return Optional.ofNullable(movie)
                .map(newMovie -> movieRepository.save(movie))
                .map(result -> {
                    boolean isSuccess = Objects.nonNull(result);
                    if(isSuccess) {
                        log.info("Saving movie successful with movie name : {} and generated Id of : {}", movie.getName(),
                                movie.getId());
                    }
                    return isSuccess;
                })
                .orElseGet(() -> {
                    log.error("Saving movie unsuccessful for movie name : {}", movie.getName());
                    return Boolean.FALSE;
                });
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

    @Override
    public Page<Movie> getMoviePaged(int page) {
        return movieRepository.findAllWithPaging(PageRequest.of(page, 2));
    }

}
