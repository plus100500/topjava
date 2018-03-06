package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.DataBase;
import ru.javawebinar.topjava.model.Meal;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static java.util.Objects.nonNull;

public class MealDaoImpl implements MealDao {

    private static final Logger log = getLogger(MealDaoImpl.class);

    @Override
    public void add(Meal meal) {
        DataBase.getMeals().add(meal);
        log.info("Meal " + meal + " successfully added");
    }

    @Override
    public void update(Meal meal) {
        getList().remove(getById(meal.getId()));
        getList().add(meal);
        log.info("Meal " + meal + " successfully updated");
    }

    @Override
    public void remove(int id) {
        Meal meal = getById(id);
        DataBase.getMeals().remove(meal);
        log.info("Meal " + meal + " successfully removed");
    }

    @Override
    public Meal getById(int id) {
        Meal meal = null;
        for(Meal mealFor : getList()) {
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
    public List<Meal> getListById(int id) {
        Meal meal = null;
        for(Meal mealFor : getList()) {
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
    public List<Meal> getList() {
        return DataBase.getMeals();
    }

    @Override
    public int getId() {
        return DataBase.getMeals().size()+1;
    }
}
