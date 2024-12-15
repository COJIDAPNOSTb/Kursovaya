package org.example.spring;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String trainId;
    private String trainStart;
    private String trainEnd;
    private String trainDuration;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainStart() {
        return trainStart;
    }

    public void setTrainStart(String trainStart) {
        this.trainStart = trainStart;
    }

    public String getTrainEnd() {
        return trainEnd;
    }

    public void setTrainEnd(String trainEnd) {
        this.trainEnd = trainEnd;
    }

    public String getTrainDuration() {
        return trainDuration;
    }

    public void setTrainDuration(String trainDuration) {
        this.trainDuration = trainDuration;
    }
}
