package ru.javawebinar.topjava.Repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealRepository {
    private static AtomicInteger count = new AtomicInteger(1);
    private static ConcurrentHashMap<Integer, Meal> meals = new ConcurrentHashMap<>();

    static {
        MealRepository.add(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        MealRepository.add(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        MealRepository.add(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        MealRepository.add(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        MealRepository.add(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        MealRepository.add(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    }

    public static void add(LocalDateTime dateTime, String description, int calories){
        meals.put(count.get(), new Meal(count.getAndIncrement(), dateTime, description, calories));
    }

    public static void update(int id, Meal meal) {
        meals.put(id, meal);
    }

    public static void delete(int id) {
        meals.remove(id);
    }

    public static Collection<Meal> getAll(){
        return meals.values();
    }

    public static Meal get(int id) {
        return meals.get(id);
    }
}
