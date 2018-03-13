package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import java.time.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsUtil {
    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак1", 500,1),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед1", 1000,1),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин1", 500,1),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак2", 1000,2),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед2", 500,2),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин2", 510,2),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед3", 1000,3),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин3", 500,3),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак3", 1000,3)
    );

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static List<MealWithExceed> getWithExceeded(Collection<Meal> meals, int caloriesPerDay, Integer userId) {
        return getFilteredWithExceeded(meals, caloriesPerDay, meal -> meal.getUserId() == userId);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(Collection<Meal> meals, int caloriesPerDay, LocalTime startTime, LocalTime endTime) {
        return getFilteredWithExceeded(meals, caloriesPerDay, meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime));
    }

    private static List<MealWithExceed> getFilteredWithExceeded(Collection<Meal> meals, int caloriesPerDay, Predicate<Meal> filter) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .filter(filter)
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(filter)
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .sorted(new Comparator<MealWithExceed>() {
                    @Override
                    public int compare(MealWithExceed o1, MealWithExceed o2) {
                        return (int) (o2.getDateTime().toEpochSecond(ZoneOffset.UTC) - o1.getDateTime().toEpochSecond(ZoneOffset.UTC));
                    }
                })
                .collect(toList());
    }

    private static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {
        return new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }
}