package com.softuni.judge.model.service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class HomeworkAddCommentServiceModel {

    private String homeworkId;
    private Integer score;
    private String textContent;

    public HomeworkAddCommentServiceModel() {
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    @Min(value = 2, message = "Score must be at least 2")
    @Max(value = 6, message = "Score must be up to 6")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Size(min = 3, message = "Description must be at least 3 characters")
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

}
