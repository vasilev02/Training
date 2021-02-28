package com.softuni.judge.service.impl;

import com.softuni.judge.model.entity.Comment;
import com.softuni.judge.model.service.HomeworkAddCommentServiceModel;
import com.softuni.judge.repository.CommentRepository;
import com.softuni.judge.security.CurrentUser;
import com.softuni.judge.service.CommentService;
import com.softuni.judge.service.HomeworkService;
import com.softuni.judge.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final HomeworkService homeworkService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, HomeworkService homeworkService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.homeworkService = homeworkService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addComment(HomeworkAddCommentServiceModel serviceModel, String id) {

        Comment comment = this.modelMapper.map(serviceModel, Comment.class);

        comment.setAuthor(this.userService.getUserByUsername(this.currentUser.getUsername()));
        comment.setHomework(this.homeworkService.getHomeworkBiId(id));

        this.commentRepository.saveAndFlush(comment);
    }

    @Override
    public Integer getGradesFor(int grade) {
        Integer count = this.commentRepository.getGradesFor(grade);
        if(count == null){
            return 0;
        }
        return count;
    }
}
