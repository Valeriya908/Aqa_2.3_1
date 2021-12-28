package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@UtilityClass
public class DataGenerator {

    String [] city = {"Москва", "Майкоп", "Горно-Алтайск", "Элиста", "Йошкар-Ола", "Красноярск", "Ставрополь",
            "Владимир", "Иркутск", "Курган", "Санкт-Петербург", "Новосибирск", "Южно-Сахалинск", "Тюмень",
            "Биробиджан", "Нарьян-Мар", "Орёл", "Севастополь", "Салехард", "Анадырь"};

    String random = (city[new Random().nextInt(city.length)]);

   public static String firstCalculationDate() {
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String secondCalculationDate() {
        return LocalDate.now().plusDays(6).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(
                    random,
                    firstCalculationDate(),
                    secondCalculationDate(),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());
        }
    }
}



