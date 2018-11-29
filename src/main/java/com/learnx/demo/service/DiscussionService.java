package com.learnx.demo.service;

import com.learnx.demo.model.DiscussionDto;
import java.util.List;

public interface DiscussionService {

    List<DiscussionDto> listDiscussionsByCourseId(int courseId);

    DiscussionDto create(DiscussionDto newDiscussion);
}
