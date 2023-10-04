package org.binaracademy.bioskopbackend.service;

import org.aspectj.lang.annotation.After;
import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.model.Schedule;
import org.binaracademy.bioskopbackend.repository.MovieRepository;
import org.binaracademy.bioskopbackend.service.MovieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;

    @AfterEach
    void tearDown() {
        movieRepository.deleteAll();
    }

    @Test
    void testMovieCurrentlyShowing_success() {
        Movie movieOnSchedule = Movie.builder()
                .name("test 1")
                .movieCode("test1")
                .schedules(Arrays.asList(Schedule.builder()
                                .startTime(new Date(new Date().getTime() - 10000L))
                                .endTime(new Date(new Date().getTime() + 10000L))
                        .build()))
                .posterImage("poster 1")
                .synopsis("synopsis")
                .build();
        Movie movieOffSchedule = Movie.builder()
                .name("test 2")
                .movieCode("test2")
                .schedules(Arrays.asList(Schedule.builder()
                        .startTime(new Date(new Date().getTime() + 10000L))
                        .endTime(new Date(new Date().getTime() + 90000L))
                        .build()))
                .build();
//        movieRepository.save(movieOnSchedule);
        movieRepository.saveAll(Arrays.asList(movieOnSchedule, movieOffSchedule));

        List<Movie> movieActual = movieService.getMovieCurrentlyShowing(null);
        Assertions.assertEquals(1, movieActual.size());
        Assertions.assertEquals("test1", movieActual.get(0).getMovieCode());
    }

    @Test
    void testGetAllMovie_success() {
        Movie movie = Movie.builder()
                .name("test 1")
                .movieCode("test1")
                .build();
        movieRepository.save(movie);

        List<Movie> movieActual = movieService.getMovieCurrentlyShowing(null);
        Assertions.assertEquals(1, movieActual.size());
    }
}
