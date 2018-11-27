package com.learnx.demo.service;

import java.util.List;

public interface DiscussionService {

    List<DiscussionService> listDiscussionsByCourseId(long courseId);

    DiscussionService create(DiscussionService newDiscussion);
}
