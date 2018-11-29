package com.learnx.demo.service;

import com.learnx.demo.model.RatingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Override
    public RatingDto create(RatingDto rating) {
        return null;
    }

    @Override
    public RatingDto update(RatingDto newRating) {
        return null;
    }

    @Override
    public List<RatingDto> listRatingsByCourseId(int courseId) {
        return null;
    }

    @Override
    public double getAverageRatingByCourseId(int courseId) {
        return 0;
    }
}
