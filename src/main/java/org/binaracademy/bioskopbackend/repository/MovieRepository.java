package org.binaracademy.bioskopbackend.repository;

import lombok.Data;
import org.binaracademy.bioskopbackend.model.Movie;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@Component
public class MovieRepository {

    private List<Movie> movies = Arrays.asList(
            Movie.builder().name("Amazing Java").schedule(new Date(1695733200000l))
                    .image("https://upload.wikimedia.org/wikipedia/en/e/e0/The_Amazing_Spider-Man_%28film%29_poster.jpg")
                    .seat(Arrays.asList("A1 A2 B1 B2 C1 C2"))
                    .synopsis("Betapa hebatnya Java men")
                    .build(),
            Movie.builder().name("Civil Servant").schedule(new Date(1695733200000l))
                    .image("https://upload.wikimedia.org/wikipedia/en/e/e0/The_Amazing_Spider-Man_%28film%29_poster.jpg")
                    .seat(Arrays.asList("A1 A2 B1 B2 C1 C2"))
                    .synopsis("Wow keren Civil Servant satu ini")
                    .build(),
            Movie.builder().name("Dr. Weird").schedule(new Date(1695733200000l))
                    .image("https://upload.wikimedia.org/wikipedia/en/e/e0/The_Amazing_Spider-Man_%28film%29_poster.jpg")
                    .seat(Arrays.asList("A1 A2 B1 B2 C1 C2"))
                    .synopsis("Aneh juga dokter satu ini")
                    .build()
    );

    public boolean addMovie(Movie movie) {
        movies.add(movie);

        if(movies.contains(movie)) {
            return true;
        }
        return false;
    }

    public void editMovie(Movie movie) {

    }

    public void deleteMovie(Movie movie) {
        movies.remove(movie);
    }

}
