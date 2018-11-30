package com.learnx.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SubmissionWrapper {
    private List<SubmissionDto> submissions;

    public void addSubmission(SubmissionDto sub){
        this.submissions.add(sub);
    }

    public SubmissionWrapper(){
        submissions=new ArrayList<>();
    }
}
