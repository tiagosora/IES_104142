package com.tiagosora.moviesv2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tiagosora.moviesv2.Model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

}
