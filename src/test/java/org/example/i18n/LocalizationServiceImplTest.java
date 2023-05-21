package org.example.i18n;

import java.util.Set;

import org.junit.jupiter.api.*;
import org.example.entity.*;

public class LocalizationServiceImplTest {

    LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    @DisplayName("�������� �� ���� RUSSIA")
    void localeRus () {
        Assertions.assertEquals("����� ����������", localizationService.locale(Country.RUSSIA));
    }

    @Test
    @DisplayName("�������� �� ���� ����� RUSSIA")
    void localeNotRus () {
        Set<Country> countriesNotRus = Set.of(Country.BRAZIL, Country.USA, Country.GERMANY);
        countriesNotRus.forEach(c -> Assertions.assertEquals("Welcome", localizationService.locale(c)));
    }
}