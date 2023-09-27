package org.binaracademy.bioskopbackend.controller;

import org.binaracademy.bioskopbackend.model.Movie;
import org.binaracademy.bioskopbackend.repository.MovieRepository;
import org.binaracademy.bioskopbackend.service.MovieService;
import org.binaracademy.bioskopbackend.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class MovieController {

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private MovieService movieService;

    public void mainMenu() {
        System.out.println("Welcome to Bioskop Binar!!\n" +
                "Silahkan pilih menu\n" +
                "1. Lihat film sedang tayang\n" +
                "2. Tambahkan film\n" +
                "0. Keluar");
        System.out.print("=> ");
        int pilihan = scanner.nextInt();
        if(pilihan == 1) {
            this.showFilmSedangTayang();
        }
        System.exit(0);
    }

    public void showFilmSedangTayang() {
        System.out.println("Berikut adalah film yang sedang tayang saat ini");
        System.out.println("Nama Film \t | \t Jadwal \t | \t Sinopsis");
        movieService.getMovieCurrentlyShowing(null).forEach(movie -> {
            System.out.println(movie.getName() + " \t | \t " + movie.getSchedule() + "\t | \t" + movie.getSynopsis());
        });
        System.out.println("Pilih film yang ingin dilihat lebih detil => ");
        int pilihan = scanner.nextInt();
        this.showFilmDetail(pilihan);
    }

    public void showFilmDetail(int input) {
        Movie movie = movieService.getMovieDetail(input);
        System.out.println("Nama Film : " + movie.getName());
        System.out.println("Poster Image : " + movie.getImage());
        System.out.println("Jadwal Tayang : " + movie.getSchedule());
        System.out.println("Seat tersedia : " + movie.getSeat().get(0));
        System.out.println("Sinopsis : " + movie.getSynopsis());
        System.out.println();
        System.out.println("1. Kembali ke menu utama");
        System.out.println("0. Keluar");
        System.out.print("=> ");
        int pilihan = scanner.nextInt();
        if(pilihan == 1) {
            this.mainMenu();
        }
        System.exit(0);
    }

}
