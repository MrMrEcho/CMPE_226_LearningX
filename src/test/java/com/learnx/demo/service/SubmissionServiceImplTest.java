package com.learnx.demo.service;

import com.learnx.demo.model.SubmissionDto;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubmissionServiceImplTest {

    @Autowired
    private SubmissionService service;

    @Test
    public void create() {
//        SubmissionDto sub1 = new SubmissionDto(1, 3);
//        sub1 = service.create(sub1);
//
//        SubmissionDto sub2 = new SubmissionDto(1, 1);
//        sub2 = service.create(sub2);
//
//        SubmissionDto sub3 = new SubmissionDto(2, 3);
//        sub3 = service.create(sub3);
    }

    @Test
    public void listExamSubmissionByCourseId() {
        SubmissionDto sub1 = new SubmissionDto(1, 3);
        sub1 = service.create(sub1);

        SubmissionDto sub2 = new SubmissionDto(1, 1);
        sub2 = service.create(sub2);

        SubmissionDto sub3 = new SubmissionDto(2, 3);
        sub3 = service.create(sub3);

        List<SubmissionDto> results = service.listExamSubmissionByCourseId(1, false);

        results.forEach(System.out::println);
    }

    @Test
    public void findById() {
        SubmissionDto sub3 = new SubmissionDto(2, 3);
        sub3 = service.create(sub3);

        System.out.println(service.findById(2, 3));
    }

    @Test
    public void grade() {

        SubmissionDto sub3 = new SubmissionDto(2, 3);
        sub3 = service.create(sub3);

        sub3.setAnswer("asdfasdfs");
        sub3.setGrade(33);
        service.update(sub3);

        System.out.println(service.findById(2, 3));

        SubmissionDto sub4 = new SubmissionDto(1, 3);
        sub4 = service.create(sub4);

        sub4.setAnswer("0000exam");
        sub4.setGrade(99);
        sub4.setHasGrade(true);
        service.update(sub4);

        System.out.println(service.findById(1, 3));
    }
}
