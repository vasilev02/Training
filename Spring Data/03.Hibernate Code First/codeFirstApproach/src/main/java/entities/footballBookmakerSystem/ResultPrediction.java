package entities.footballBookmakerSystem;

import entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BaseEntity {

    private PredictionEnum prediction;

    public ResultPrediction() {
    }

    @Column(name = "prediction",nullable = false)
    public PredictionEnum getPrediction() {
        return prediction;
    }

    public void setPrediction(PredictionEnum prediction) {
        this.prediction = prediction;
    }
}
