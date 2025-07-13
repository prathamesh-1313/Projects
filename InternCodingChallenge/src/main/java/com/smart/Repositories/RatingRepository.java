package com.smart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.Models.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>  {

}
