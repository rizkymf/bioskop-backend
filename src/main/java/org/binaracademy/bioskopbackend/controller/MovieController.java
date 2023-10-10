package org.binaracademy.bioskopbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.model.response.MovieResponse;
import org.binaracademy.bioskopbackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@Slf4j
public class MovieController {

    @Autowired
    MovieService movieService;

    @RequestMapping(value = "/api/movies/get-movies", method = RequestMethod.GET, produces = "application/json")
    public List<MovieResponse> getMovies() {
//        List<MovieResponse> movieResponse = Optional.ofNullable(movieService.getMovieCurrentlyShowing(null))
//                .map(movieList -> movieList.stream()
//                            .map(movie -> MovieResponse.builder()
//                                    .id(movie.getId())
//                                    .name(movie.getName())
//                                    .img(movie.getPosterImage())
//                                    .build())
//                            .collect(Collectors.toList()))
//                .orElse(null);
        return Arrays.asList(MovieResponse.builder()
                        .name("Avengers Endgame")
                        .img("https://i.etsystatic.com/13367669/r/il/db21fd/2198543930/il_570xN.2198543930_4qne.jpg")
                        .id("54321")
                .build());
//        return movieService.getAllMovie();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/movies/add", consumes = "application/json")
    public String addNewMovies(@RequestBody Movie movie) {
        movieService.submitMovie(movie);
        return "Add new movies successful!";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/movies/delete/{movieName}")
    public String deleteMovie(@PathVariable("movieName") String movieName) {
        movieService.deleteMovieFromName(movieName);
        return "Delete movie " + movieName + " success!";
    }

//    @RequestMapping(method = RequestMethod.GET, value = "api/movies/detail")
    @GetMapping(value = "api/movies/detail")
    public MovieResponse getMovieDetail(@RequestParam("movieName") String movieName) {
        return movieService.getMovieDetail(movieName);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "api/movies/update/{movieName}")
    public String updateMovie(@RequestParam("newMovieName") String newMovieName,
            @PathVariable("movieName") String oldMovieName,
            @RequestHeader("Accept-Languange") String acceptLanguage,
            @RequestBody Movie movie) {
        log.info("Accept-Language - {}", acceptLanguage);
        movieService.updateMovieName(oldMovieName, newMovieName);
        return "update movie successful!";
    }
}