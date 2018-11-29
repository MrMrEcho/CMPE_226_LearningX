package com.learnx.demo.service;

import com.learnx.demo.model.RatingDto;

import java.util.List;

public interface RatingService {

    RatingDto create(RatingDto rating);

    RatingDto update(RatingDto newRating);

    List<RatingDto> listRatingsByCourseId(int courseId);

    double getAverageRatingByCourseId(int courseId);
}
