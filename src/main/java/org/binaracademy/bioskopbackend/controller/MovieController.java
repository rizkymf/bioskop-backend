package org.binaracademy.bioskopbackend.controller;

import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.model.response.MovieResponse;
import org.binaracademy.bioskopbackend.repository.MovieRepository;
import org.binaracademy.bioskopbackend.service.MovieService;
import org.binaracademy.bioskopbackend.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

@Component
public class MovieController {

    @PostConstruct
    public void init() throws ParseException {
        this.mainMenu();
    }

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public MovieService movieService;

    public void mainMenu() throws ParseException {
        System.out.println("Welcome to Bioskop Binar!!\n" +
                "Silahkan pilih menu\n" +
                "1. Lihat film sedang tayang\n" +
                "2. Tambahkan film\n" +
                "0. Keluar");
        System.out.print("=> ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();
        switch(pilihan) {
            case 1:
                this.showFilmSedangTayang();
                break;
            case 2:
                this.addNewMovie();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Pilihan dengan benar coy\n!!!!!!!!!!!");
                this.mainMenu();
        }
    }

    public void showFilmSedangTayang() throws ParseException {
        System.out.println("Berikut adalah film yang sedang tayang saat ini");
        System.out.println("Nama Film \t | \t Sinopsis");
        List<Movie> movies = movieService.getMovieCurrentlyShowing(null);
        movies.forEach(movie -> {
            System.out.println(movie.getName() + "\t | \t" + movie.getSynopsis());
        });
        System.out.print("Pilih film yang ingin dilihat lebih detil => ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();
        String selectedMovie = movies.get(pilihan-1).getName();
        this.showFilmDetail(selectedMovie);
    }

    public void showFilmDetail(String selectedMovieName) throws ParseException {
        MovieResponse movie = movieService.getMovieDetail(selectedMovieName);
        System.out.println("Nama Film : " + movie.getMovieName());
        System.out.println("Poster Image : " + movie.getPosterImage());
        System.out.println("Sinopsis : " + movie.getSynopsis());
        System.out.println("==========================================");
        System.out.println("Studio : " + movie.getSchedules().stream()
                .filter(Objects::nonNull).findFirst()
                .map(schedule -> schedule.getStudio().getStudioName())
                .orElse("Studio tidak ditemukan"));
        System.out.println("Jadwal yang tersedia : ");
        // TODO: bikin fetch to schedule
        System.out.println("No. Mulai \t | \t Selesai");
        movie.getSchedules().forEach(schedule -> {
            System.out.println(movie.getSchedules().indexOf(schedule) + ". " + schedule.getStartTime() + " \t | \t " + schedule.getEndTime());
        });
        System.out.println();
        System.out.println("1. Kembali ke menu utama");
        System.out.println("2. Edit Detail Film");
        System.out.println("3. Hapus Film Ini");
        System.out.println("0. Keluar");
        System.out.print("=> ");
        int pilihan = scanner.nextInt();
        if(pilihan == 1) {
            this.mainMenu();
        }
        System.exit(0);
    }

    public void addNewMovie() throws ParseException {
        System.out.print("Nama Film : ");
        String movieName = scanner.nextLine();
        System.out.print("Poster Image : ");
        String urlImagePoster = scanner.nextLine();
        System.out.print("Jadwal Tayang : ");
        String schedule = scanner.nextLine();
        System.out.print("Seat tersedia : ");
        String seat = scanner.nextLine();
        System.out.print("Sinopsis : ");
        String synopsis = scanner.nextLine();

        Movie newMovie = Movie.builder()
                .name(movieName)
                .posterImage(urlImagePoster)
//                .schedule(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(schedule))
//                .seat(seat)
                .synopsis(synopsis)
                .build();
        movieService.addNewMovie(newMovie);
        System.out.println("\nFilm baru berhasil di tambahkan!");
        this.mainMenu();
    }

    public void updateMovie(String selectedMovieName) {
        // TODO : do update
    }

    public void deleteMovie(String selectedMovieName) {
        // TODO: do delete movie by movie name
    }

}
