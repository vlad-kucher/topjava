package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres", "jpa"})
public class JpaUserServiceTest extends AbstractUserServiceTest{
}
