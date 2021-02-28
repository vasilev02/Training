package com.softuni.judge.service;

import com.softuni.judge.model.service.HomeworkAddCommentServiceModel;

public interface CommentService {
    void addComment(HomeworkAddCommentServiceModel serviceModel, String id);

    Integer getGradesFor(int grade);
}
