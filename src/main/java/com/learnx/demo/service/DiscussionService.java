package com.learnx.demo.service;

import com.learnx.demo.model.Discussion;

import java.util.List;

public interface DiscussionService {

    List<Discussion> listDiscussionsByCourseId(long courseId);

    Discussion create(Discussion newDiscussion);
}
