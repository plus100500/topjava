package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.DataBase;
import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static java.util.Objects.nonNull;

public class MealDaoImpl implements MealDao {

    private static final Logger log = getLogger(MealDaoImpl.class);

    @Override
    public void addMeal(Meal meal) {
        DataBase.meals.add(meal);
        log.info("Meal " + meal + " successfully added");
    }

    @Override
    public void updateMeal(int id, LocalDateTime localDateTime, String description, int calories) {
        Meal meal = getMealById(id);
        getListMeals().remove(meal);
        meal = new Meal(id,localDateTime,description,calories);
        getListMeals().add(meal);
        log.info("Meal " + meal + " successfully updated");
    }

    @Override
    public void removeMeal(int id) {
        Meal meal = getMealById(id);
        DataBase.meals.remove(meal);
        log.info("Meal " + meal + " successfully removed");
    }

    @Override
    public Meal getMealById(int id) {
        Meal meal = null;
        for(Meal mealFor : getListMeals()) {
            if (mealFor.getId() == id) meal = mealFor;
        }

        if (nonNull(meal)) {
            log.info("Meal: " + meal + " founded.");
        } else {
            log.info("Meal with id: " + id + " not found.");
        }
        return meal;
    }

    @Override
    public List<Meal> getListMealById(int id) {
        Meal meal = null;
        for(Meal mealFor : getListMeals()) {
            if (mealFor.getId() == id) meal = mealFor;
        }

        if (nonNull(meal)) {
            log.info("Meal: " + meal + " founded.");
        } else {
            log.info("Meal with id: " + id + " not found.");
        }
        List<Meal> meals = new ArrayList<>();
        meals.add(meal);
        return meals;
    }

    @Override
    public List<Meal> getListMeals() {
        return DataBase.meals;
    }

    @Override
    public int getId() {
        return DataBase.meals.size()+1;
    }
}
