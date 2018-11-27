package com.learnx.demo.service;

import com.learnx.demo.model.Rate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Override
    public Rate create(Rate newRate) {
        return null;
    }

    @Override
    public Rate update(Rate rate) {
        return null;
    }

    @Override
    public List<Rate> listRateByCourseId(long courseId) {
        return null;
    }

    @Override
    public int getAverageRateByCourseId(long courseId) {
        return 0;
    }
}
