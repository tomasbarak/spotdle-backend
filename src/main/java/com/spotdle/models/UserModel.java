package com.spotdle.models;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {
    
    @Id
    @Column(unique = true, nullable = false)
    private String id;

    private String name;
    private String email;
    private Integer maxScore;

    //Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    //Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }
}
