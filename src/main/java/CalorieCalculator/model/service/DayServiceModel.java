package CalorieCalculator.model.service;

import CalorieCalculator.model.entity.UserEntity;

import java.time.LocalDate;

public class DayServiceModel {
    private String id;
    private String name;
    private LocalDate date;
    private String username;

    public DayServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
