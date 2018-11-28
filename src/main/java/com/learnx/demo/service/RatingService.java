package com.learnx.demo.service;

import com.learnx.demo.model.Rating;

import java.util.List;

public interface RatingService {

    Rating create(Rating newRate);
    Rating update(Rating rate);
    List<Rating> listRatingsByCourseId(int courseId);
    int getAverageRatingByCourseId(int courseId);
}
