package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.MealTestData.MEALS;

@ActiveProfiles({"postgres", "datajpa"})
public class DataJpaUserServiceTest extends AbstractUserServiceTest{

    @Test
    public void testGetWithMeals() throws Exception{
        List<Meal> meals = service.getWithMeals(USER_ID).getMeals();
        MealTestData.MATCHER.assertCollectionEquals(MEALS, meals);
    }
}
