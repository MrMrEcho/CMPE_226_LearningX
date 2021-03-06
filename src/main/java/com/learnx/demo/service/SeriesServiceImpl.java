package com.learnx.demo.service;

import com.learnx.demo.entity.Series;
import com.learnx.demo.model.SeriesDto;
import com.learnx.demo.repository.RepositoryUtil;
import com.learnx.demo.repository.SeriesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository repository;

    @Autowired
    public SeriesServiceImpl(SeriesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SeriesDto> listSeries() {
        return RepositoryUtil.mapAll(repository.findAll(), SeriesServiceImpl::toDto);
    }

    private static SeriesDto toDto(Series entity) {
        SeriesDto dto = new SeriesDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setInstituteId(entity.getInstituteId());

        return dto;
    }

    @Override
    public List<SeriesDto> listSeriesByInstituteId(int instituteId) {
        return RepositoryUtil.mapAll(
                repository.findByInstituteId(instituteId), SeriesServiceImpl::toDto);
    }

    @Override
    public List<SeriesDto> listSeriesByStudentId(int studentId) {
        return RepositoryUtil.mapAll(
                repository.findByStudentId(studentId), SeriesServiceImpl::toDto);
    }

    @Override
    public SeriesDto create(SeriesDto dto) {
        Series newEntity = toEntity(dto);
        Series saveEntity = repository.save(newEntity);

        return toDto(saveEntity);
    }

    private static Series toEntity(SeriesDto dto) {
        Series entity = new Series();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setInstituteId(dto.getInstituteId());

        return entity;
    }

    @Override
    public SeriesDto update(SeriesDto dto) {
        Series newEntity = repository.update(toEntity(dto));

        return toDto(newEntity);
    }

    @Override
    public boolean addCourseBySeriesId(int courseId, int seriesId) {
        return repository.addCourseToSeriesById(courseId, seriesId);
    }

    @Override
    public SeriesDto getSeriesById(int seriesId) {
        Series entity = repository.findById(seriesId);
        if (entity == null) {
            return null;
        }

        return toDto(entity);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
