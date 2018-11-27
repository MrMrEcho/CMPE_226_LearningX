package com.learnx.demo.service;

import com.learnx.demo.model.Rating;

import java.util.List;

public interface RatingService {

    Rating create(Rating newRate);
    Rating update(Rating rate);
    List<Rating> listRatingsByCourseId(long courseId);
    int getAverageRatingByCourseId(long courseId);
}
