package org.example.i18n;

import java.util.Set;

import org.junit.jupiter.api.*;
import org.example.entity.*;

public class LocalizationServiceImplTest {

    LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    @DisplayName("Проверка на ввод RUSSIA")
    void localeRus () {
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    @DisplayName("Проверка на ввод кроме RUSSIA")
    void localeNotRus () {
        Set<Country> countriesNotRus = Set.of(Country.BRAZIL, Country.USA, Country.GERMANY);
        countriesNotRus.forEach(c -> Assertions.assertEquals("Welcome", localizationService.locale(c)));
    }
}