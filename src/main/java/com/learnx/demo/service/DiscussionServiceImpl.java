package com.learnx.demo.service;

import com.learnx.demo.model.DiscussionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {


    @Override
    public List<DiscussionDto> listDiscussionsByCourseId(int courseId) {
        return null;
    }

    @Override
    public DiscussionDto create(DiscussionDto newDiscussion) {
        return null;
    }
}
