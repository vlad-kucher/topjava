package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ+2;
    public static final int MEAL2_ID = START_SEQ+3;
    public static final int MEAL3_ID = START_SEQ+4;
    public static final int MEAL4_ID = START_SEQ+5;
    public static final int MEAL5_ID = START_SEQ+6;
    public static final int MEAL6_ID = START_SEQ+7;
    public static final int MEAL7_ID = START_SEQ+8;
    public static final int MEAL8_ID = START_SEQ+9;

    public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2015, Month.MAY, 30, 9, 0);
    public static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2015, Month.MAY, 30, 14, 0);
    public static final LocalDate START_DATE = LocalDate.of(2015, Month.MAY, 30);
    public static final LocalDate END_DATE = LocalDate.of(2015, Month.MAY, 30);

    //User's meals
    public static final Meal MEAL1 = new Meal(MEAL1_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL2_ID, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(MEAL3_ID, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL4_ID, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL5 = new Meal(MEAL5_ID, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL6 = new Meal(MEAL6_ID, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

    //Admin's meals
    public static final Meal MEAL7 = new Meal(MEAL7_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL8 = new Meal(MEAL8_ID, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
                    )
    );

}
