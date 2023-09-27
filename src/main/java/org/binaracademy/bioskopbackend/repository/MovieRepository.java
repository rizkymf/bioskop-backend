package org.binaracademy.bioskopbackend.repository;

import lombok.Data;
import org.binaracademy.bioskopbackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    Movie findByName(String name); // select * from movie m where m.name = :name

    @Query(nativeQuery = true, value = "select * from movie m where m.schedule < :schedule ")
    List<Movie> getMoviesOnSchedule(@Param("schedule") Date schedule);

    @Query(value = "select m from Movie m where m.schedule < :schedule and m.seat like %:seat% ")
    List<Movie> getMoviesOnScheduleAndSeat(@Param("schedule") Date schedule, @Param("seat") String seat);

}
