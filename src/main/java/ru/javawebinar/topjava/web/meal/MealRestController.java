package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealWithExceed> getAll(){
        log.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, AuthorizedUser.id());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.save(meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, AuthorizedUser.id());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id {}", meal, id);
        checkIdConsistent(meal, id);
        service.update(meal);
    }

    public List<MealWithExceed> getFiltered(String startD, String endD, String startT, String endT){
        LocalDate startDate, endDate;
        LocalTime startTime, endTime;

        if (startD==null || endD==null || startD.isEmpty() || endD.isEmpty()) {
            startDate=LocalDate.MIN;
            endDate=LocalDate.MAX;
        } else {
            startDate=LocalDate.parse(startD);
            endDate=LocalDate.parse(endD);
        }

        if (startT==null || endT==null || startT.isEmpty() || endT.isEmpty()) {
            startTime=LocalTime.MIN;
            endTime=LocalTime.MAX;
        } else {
            startTime=LocalTime.parse(startT);
            endTime=LocalTime.parse(endT);
        }

        log.info("filter: from {} to {} date, from {} to {} time", startDate, endDate, startTime, endTime);
        return MealsUtil.getFilteredWithExceeded(service.getAll(AuthorizedUser.id(), startDate, endDate), startTime, endTime, AuthorizedUser.getCaloriesPerDay());
    }
}