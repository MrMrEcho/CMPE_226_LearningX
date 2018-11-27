package com.learnx.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Override
    public List<DiscussionService> listDiscussionsByCourseId(long courseId) {
        return null;
    }

    @Override
    public DiscussionService create(DiscussionService newDiscussion) {
        return null;
    }
}
