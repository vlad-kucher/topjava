package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Repository.MealRepository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private static final String MEALS = "/meals.jsp";
    private static final String ADD_OR_EDIT = "/addOrEdit.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String forward;

        if ("delete".equalsIgnoreCase(action)) {
            log.debug("delete meal");
            MealRepository.delete(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("meals", MealsUtil.getWithExceeded(MealRepository.getAll(), 2000));
            forward = MEALS;
        } else if ("edit".equalsIgnoreCase(action)) {
            log.debug("edit meal");
            request.setAttribute("meal", MealRepository.get(Integer.parseInt(request.getParameter("id"))));
            forward = ADD_OR_EDIT;
        } else if ("insert".equalsIgnoreCase(action)) {
            log.debug("add meal");
            forward = ADD_OR_EDIT;
        } else {
            log.debug("forward to meals");
            request.setAttribute("meals", MealsUtil.getWithExceeded(MealRepository.getAll(), 2000));
            forward = MEALS;
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        if (id==null || id.isEmpty()) {
            MealRepository.add(LocalDateTime.parse(request.getParameter("dateTime")),
                                request.getParameter("description"),
                                Integer.parseInt(request.getParameter("calories")));
        } else {
            Meal meal = new Meal(Integer.parseInt(id),
                                LocalDateTime.parse(request.getParameter("dateTime")),
                                request.getParameter("description"),
                                Integer.parseInt(request.getParameter("calories")));
            MealRepository.update(meal.getId(), meal);
        }

        log.debug("forward to meals");
        request.setAttribute("meals", MealsUtil.getWithExceeded(MealRepository.getAll(), 2000));
        request.getRequestDispatcher(MEALS).forward(request, response);
    }
}
