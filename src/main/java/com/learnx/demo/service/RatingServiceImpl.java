package com.learnx.demo.service;

import com.learnx.demo.entity.Rating;
import com.learnx.demo.model.RatingDto;
import com.learnx.demo.repository.RatingRepository;
import com.learnx.demo.repository.RepositoryUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;

    @Autowired
    public RatingServiceImpl(RatingRepository repository) {
        this.repository = repository;
    }

    private static Rating toEntity(RatingDto dto) {
        Rating entity = new Rating();
        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setRate(dto.getRate());

        return entity;
    }

    private static RatingDto toDto(Rating entity) {
        RatingDto dto = new RatingDto();
        dto.setStudentId(entity.getStudentId());
        dto.setCourseId(entity.getCourseId());
        dto.setRate(entity.getRate());

        return dto;
    }

    @Override
    public RatingDto create(RatingDto rating) {

        Rating newEntity = toEntity(rating);
        Rating saveEntity = repository.save(newEntity);

        return toDto(saveEntity);
    }

    @Override
    public RatingDto update(RatingDto newDto) {
        Rating newEntity = repository.update(toEntity(newDto));

        return toDto(newEntity);
    }

    @Override
    public List<RatingDto> listRatingsByCourseId(int courseId) {
        return RepositoryUtil.mapAll(
                repository.findRatingByCourseId(courseId), RatingServiceImpl::toDto);
    }

    @Override
    public int getAverageRatingByCourseId(int courseId) {
        return repository.getAvgRatingByCourseId(courseId);
    }
}
