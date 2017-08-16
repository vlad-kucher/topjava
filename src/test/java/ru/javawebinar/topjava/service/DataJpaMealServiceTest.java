package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles({"postgres", "datajpa"})
public class DataJpaMealServiceTest extends AbstractMealServiceTest{

    @Test
    public void testGetWithUser() throws Exception{
        User user = service.getWithUser(MEAL1_ID, USER_ID).getUser();
        UserTestData.MATCHER.assertEquals(USER, user);
    }
}
