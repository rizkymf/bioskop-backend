package org.binaracademy.bioskopbackend.repository;

import org.binaracademy.bioskopbackend.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    @Modifying
    @Query(nativeQuery = true, value = "delete from schedule where movie_id = :movieId")
    void deleteByMovieId(@Param("movieId") String movieId);
}
