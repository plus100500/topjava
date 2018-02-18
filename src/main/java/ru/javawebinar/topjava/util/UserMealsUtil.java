package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> userMealWithExceedList = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        for (UserMealWithExceed userMealWithExceed : userMealWithExceedList)
            System.out.println(userMealWithExceed.toString());
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        // get Map of sum calories per day
        Map<LocalDate, Integer> caloriesMapPerDay = mealList.stream().collect(Collectors.toMap(obj->obj.getDateTime().toLocalDate(), obj->obj.getCalories(),(val1, val2)->(val1+val2)));

        // get filtered List of UserMeals
        List<UserMeal> userMealList = mealList.stream().filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(),startTime,endTime)).collect(Collectors.toList());

        // get List of UserMealWithExceed (i can't create it...)
        List<UserMealWithExceed> userMealWithExceedList = userMealList.stream().
                filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(),startTime,endTime)).
                collect(Collectors.toList((v)->(new UserMealWithExceed(v.getDateTime(),v.getDescription(),v.getCalories(),caloriesMapPerDay.get(v.getDateTime().toLocalDate()) > caloriesPerDay))));

        return userMealWithExceedList;
    }
}
