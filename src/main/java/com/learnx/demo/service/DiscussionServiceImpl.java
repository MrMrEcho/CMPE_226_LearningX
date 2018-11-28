package com.learnx.demo.service;

import com.learnx.demo.model.Discussion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Override
    public List<Discussion> listDiscussionsByCourseId(long courseId) {
        return null;
    }

    @Override
    public Discussion create(Discussion newDiscussion) {
        return null;
    }
}
