package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public class MealServiceImpl implements MealService {

    private MealDao mealDao;

    public MealServiceImpl() {
        this.mealDao = new MealDaoImpl();
    }

    @Override
    public void addMeal(Meal meal) {
        mealDao.addMeal(meal);
    }

    @Override
    public void updateMeal(int id, LocalDateTime localDateTime, String description, int calories) {
        mealDao.updateMeal(id,localDateTime,description,calories);
    }

    @Override
    public void removeMeal(int id) {
        mealDao.removeMeal(id);
    }

    @Override
    public Meal getMealById(int id) {
        return mealDao.getMealById(id);
    }

    @Override
    public List<Meal> getListMealById(int id) {
        return mealDao.getListMealById(id);
    }

    @Override
    public List<Meal> getListMeals() {
        return mealDao.getListMeals();
    }

    @Override
    public int getId() {
        return mealDao.getId();
    }
}
