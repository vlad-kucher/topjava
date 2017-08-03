package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL1_ID, USER_ID);
        MATCHER.assertEquals(MEAL1, meal);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL3_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL2, MEAL1), service.getAll(USER_ID));
    }

    @Test
    public void getBetweenDates() throws Exception {
        Collection<Meal> between = service.getBetweenDates(START_DATE, END_DATE, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1), between);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        Collection<Meal> between = service.getBetweenDateTimes(START_DATE_TIME, END_DATE_TIME, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL2, MEAL1), between);
    }

    @Test
    public void getAll() throws Exception {
        Collection<Meal> all = service.getAll(ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL8, MEAL7), all);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(MEAL7);
        updated.setCalories(700);
        updated.setDescription("UpdatedDescription");
        service.update(updated, ADMIN_ID);
        MATCHER.assertEquals(updated, service.get(MEAL7_ID, ADMIN_ID));
    }

    @Test
    public void save() throws Exception {
        Meal newMeal = new Meal(START_DATE_TIME, "some description", 333);
        Meal created = service.save(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL8, MEAL7, newMeal), service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(MEAL1_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() throws Exception {
        service.get(MEAL2_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundUpdate() throws Exception {
        service.update(MEAL1, ADMIN_ID);
    }
}