package ru.javawebinar.topjava.web;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.JpaUtil;
import ru.javawebinar.topjava.service.UserService;

public abstract class AbstractUserControllerTest extends AbstractControllerTest {

    @Autowired
    private JpaUtil jpaUtil;

    @Autowired
    protected UserService userService;

    @Before
    public void setUp() {
        userService.evictCache();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
