package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;

@Controller
public class JspMealController extends MealRestController{
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    public JspMealController(MealService service) {
        super(service);
    }

    @GetMapping("/meals")
    public String meals(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                return delete(request,response);
            case "create":
                return create(request, response);
            case "update":
                return update(request,response);
            case "all":
            default:
                request.setAttribute("meals", getAll());
                break;
        }
        model.addAttribute("meals",getAll());
        return "meals";
    }

    private String create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/WEB-INF/jsp/mealForm.jsp").forward(request, response);
        return "mealForm";
    }

    @PostMapping("/meals")
    public String setMeal(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (request.getParameter("id").isEmpty()) {
            create(meal);
        } else {
            update(meal, getId(request));
        }
        return "redirect:meals";
    }

    private String update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final Meal meal = get(getId(request));
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/WEB-INF/jsp/mealForm.jsp").forward(request, response);
        return "mealForm";
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = getId(request);
        delete(id);
        response.sendRedirect("meals");
        return "meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
