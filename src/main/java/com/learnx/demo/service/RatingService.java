package com.learnx.demo.service;

import com.learnx.demo.model.Rate;

import java.util.List;

public interface RatingService {

    Rate create(Rate newRate);
    Rate update(Rate rate);
    List<Rate> listRateByCourseId(long courseId);
    int getAverageRateByCourseId(long courseId);
}
