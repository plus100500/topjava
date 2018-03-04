package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDao {
    void addMeal(Meal meal);
    void updateMeal(int id, LocalDateTime localDateTime, String description, int calories);
    void removeMeal(int id);
    Meal getMealById(int id);
    List<Meal> getListMealById(int id);
    List<Meal> getListMeals();
    int getId();
}
