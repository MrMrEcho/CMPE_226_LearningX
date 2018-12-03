package com.learnx.demo.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmissionWrapper {

    private List<SubmissionDto> submissions;

    public SubmissionWrapper() {
        submissions = new ArrayList<>();
    }

    public void addSubmission(SubmissionDto sub) {
        this.submissions.add(sub);
    }
}
