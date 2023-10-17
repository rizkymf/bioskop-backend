package org.binaracademy.bioskopbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.model.response.ErrorResponse;
import org.binaracademy.bioskopbackend.model.response.MovieResponse;
import org.binaracademy.bioskopbackend.model.response.Response;
import org.binaracademy.bioskopbackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/movies")
@Slf4j
public class MovieController {

    @Autowired
    MovieService movieService;

//    @RequestMapping(value = "/get-movies", method = RequestMethod.GET, produces = "application/json")
    @GetMapping(produces = "application/json")
    @Operation(summary = "Api to get all movies")
    public List<MovieResponse> getMovies() {
        return movieService.getAllMovie();
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/add", consumes = "application/json")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public String addNewMovies(@RequestBody Movie movie) {
        movieService.submitMovie(movie);
        return "Add new movies successful!";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{movieName}")
    public String deleteMovie(@PathVariable("movieName") String movieName) {
        movieService.deleteMovieFromName(movieName);
        return "Delete movie " + movieName + " success!";
    }

//    @RequestMapping(method = RequestMethod.GET, value = "api/movies/detail")
    @GetMapping(value = "/detail")
    @Operation(summary = "Getting detail of one movies by movie name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie found"),
            @ApiResponse(responseCode = "404", description = "Inputted movie name not found")
    })
    public ResponseEntity getMovieDetail(@Parameter(description = "movie name to find")
    @Schema(example = "Spiderman 3")
    @RequestParam("movieName") String movieName) {
        MovieResponse response = movieService.getMovieDetail(movieName);
        log.debug("Movie detail with name {} fetched with detail {}", movieName, response);
        if(Objects.nonNull(response)) {
            return new ResponseEntity<>(Response.builder()
                    .data(movieService.getMovieDetail(movieName))
                    .isSuccess(Boolean.TRUE)
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.builder()
                .error(ErrorResponse.builder()
                        .errorMessage("Movie with name " + movieName + " not found")
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .build())
                .data(null)
                .isSuccess(Boolean.FALSE)
                .build(), HttpStatus.NOT_FOUND);
//        return movieService.getMovieDetail(movieName);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{movieName}")
    public String updateMovie(@RequestParam("newMovieName") String newMovieName,
            @PathVariable("movieName") String oldMovieName,
            @RequestHeader("Accept-Languange") String acceptLanguage,
            @RequestBody Movie movie) {
        log.info("Accept-Language - {}", acceptLanguage);
        movieService.updateMovieName(oldMovieName, newMovieName);
        return "update movie successful!";
    }

    private List<?> testWildCard() {
        return Arrays.asList("String", 40);
    }
}