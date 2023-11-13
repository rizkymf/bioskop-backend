package org.binaracademy.bioskopbackend.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.model.response.MovieResponse;
import org.binaracademy.bioskopbackend.repository.MovieRepository;
import org.binaracademy.bioskopbackend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<MovieResponse> getAllMovie() {
        log.info("Starting to get All movie");
        return movieRepository.findAll().stream()
                .map(movie -> MovieResponse.builder()
                        .id(movie.getId())
                        .name(movie.getName())
                        .img(movie.getPosterImage())
                        .build())
                .collect(Collectors.toList());
    }

//    public CompletableFuture getAllMovieAsync() {
//
//    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> getMovieCurrentlyShowing(Date date) {
        Date requestDate = Optional.ofNullable(date)
                .orElse(new Date());
        List<Movie> movies = movieRepository.getMoviesOnSchedule(requestDate);
        return movies;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> getMovieCurrentlyShowingWithFilterSeat(Date date, String seat) {
        return null;
    }

    @Async
    @Transactional
    @Override
    public void addNewMovie(Movie movie) {
        log.info("Trying to save new movie {}", movie.getName());
        Optional.ofNullable(movie)
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

    @Transactional(readOnly = true)
    @Override
    public MovieResponse getMovieDetail(String selectedMovieName) {
        // TODO : ambil data movie, berdasarkan nama movie nya
        log.info("Getting movie detail info of {}", selectedMovieName);
        return Optional.ofNullable(movieRepository.findByName(selectedMovieName))
                .map(movie -> MovieResponse.builder()
                        .id(movie.getId())
                        .name(movie.getName())
                        .img(movie.getPosterImage())
                        .build())
                .orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Movie> getMoviePaged(int page) {
        return movieRepository.findAllWithPaging(PageRequest.of(page, 2));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> getAllMovieOri() {
        return movieRepository.findMovieWithSchedule();
    }

    @Async
    @Override
    public void submitMovie(Movie movie) throws InterruptedException {
        movieRepository.save(movie);
        //        try {
//            movieRepository.submitNewMovie(movie.getId(), movie.getName(), movie.getPosterImage(), movie.getSynopsis());
//            return true;
//        } catch(Exception e) {
//            return false;
//        }
    }

    @Override
    public Boolean updateMovieName(String oldName, String newName) {
        try{
            movieRepository.editMovieName(oldName, newName);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteMovieFromName(String name) {
        try {
            movieRepository.deleteMovieFromName(name);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional(rollbackFor = {NullPointerException.class})
    @Override
    public Boolean updateMovie(String oldMovieId, Movie newMovie) {
        log.info("Updating movie of id : {}", oldMovieId);
        // Get oldMovie to update
        Movie oldMovie = movieRepository.findById(oldMovieId).orElse(null);
        if(!Optional.ofNullable(oldMovie).isPresent()) {
            return Boolean.FALSE;
        }

        // Delete old Schedule
        scheduleRepository.deleteByMovieId(oldMovieId);

//        try {
//            int a = 10;
//            if(a == 10) {
//                int b = a/0;
//            }
//        } catch(Exception e) {
//            return Boolean.FALSE;
//        }

        newMovie.setId(oldMovieId);
        // update movie
        movieRepository.save(newMovie);
        // insert schedule

        return Boolean.TRUE;
    }

    @Override
    public CompletableFuture<MovieResponse> getMovieDetailAsync(String selectedMovieName) {
        return null;
    }

    @Override
    public Future<MovieResponse> getMovieDetailFuture(String selectedMovieName) {
        return null;
    }

    @Scheduled(cron = "* 28 * * * *")
    public void scheduleExample() {
        log.info("message ini scheduling coy");
    }

}
