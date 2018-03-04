package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.DataBase;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealServiceImpl mealService;

    public MealServlet() {
        mealService = new MealServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("doPost - request.getQueryString() : " + request.getQueryString());
        log.debug("doPost - request.getParameter(\"submit\") : " + request.getParameter("submit"));
        log.debug("doPost - request.getParameter(\"action\") : " + request.getParameter("action"));
        log.debug("doPost - request.getParameter(\"id\") : " + request.getParameter("id"));
        log.debug("doPost - request.getParameter(\"dateTime\") : " + request.getParameter("dateTime"));
        log.debug("doPost - request.getParameter(\"description\") : " + request.getParameter("description"));
        log.debug("doPost - request.getParameter(\"calories\") : " + request.getParameter("calories"));

//        request.getRequestDispatcher("meals.jsp").forward(request, response);

        String action = request.getParameter("submit");

        if (action != null) {
            switch (action) {
                case "Добавить": {
                    mealService.addMeal(new Meal(
                            mealService.getId()
                            ,LocalDateTime.parse(request.getParameter("dateTime"))
                            ,request.getParameter("description")
                            ,Integer.parseInt(request.getParameter("calories"))
                    ));
                    request.setAttribute("mealsWithExceed",getListMealWithExceed());
                    response.sendRedirect("meals");
                }
                break;
                case "Обновить" : {
                    mealService.updateMeal(
                            Integer.parseInt(request.getParameter("id"))
                            ,LocalDateTime.parse(request.getParameter("dateTime"))
                            ,request.getParameter("description")
                            ,Integer.parseInt(request.getParameter("calories"))
                    );
                    request.setAttribute("mealsWithExceed",getListMealWithExceed());
                    response.sendRedirect("meals");
                }
                break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        log.debug("forwarding to meals " + action);

        if (action != null) {
            switch (action) {
                case "edit": {
                    request.setAttribute("meal",mealService.getMealById(Integer.parseInt(request.getParameter("id"))));
                    request.setAttribute("mealsWithExceed",getListMealWithExceed());
                    request.getRequestDispatcher("meals.jsp").forward(request, response);
                }
                break;
                case "remove": {
                    mealService.removeMeal(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("mealsWithExceed", getListMealWithExceed());
                    response.sendRedirect("meals");
                }
                break;
            }
        } else {
            request.setAttribute("mealsWithExceed", getListMealWithExceed());
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        }
    }

    private List<MealWithExceed> getListMealWithExceed() {
        return MealsUtil.getFilteredWithExceeded(DataBase.meals
                , LocalTime.MIN
                , LocalTime.MAX
                , DataBase.calories);
    }
}
