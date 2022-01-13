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

    String randomCity = (city[new Random().nextInt(city.length)]);

    String [] name = {"Пётр Мамонов", "Алёна Ёжикова", "Матрёна Златоусова", "Артём Климов",
            "Семён Русаков", "Анна Аксёнова", "Филипп Берёза", "Борис Головачёв", "Екатерина Дрёмова"};

    String randomName = (name[new Random().nextInt(name.length)]);

    public static String calculationDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateValidName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName().replace("ё", "е");
    }

    public static String generateInvalidName() {
        return randomName;
    }

    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(
                    randomCity,
                    generateValidName("ru"),
                    faker.phoneNumber().phoneNumber());
        }
    }
}



