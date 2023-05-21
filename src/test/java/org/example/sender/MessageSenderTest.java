package org.example.sender;

import org.mockito.*;
import org.junit.jupiter.api.*;

import org.example.entity.*;
import org.example.geo.*;
import org.example.i18n.*;

import java.util.HashMap;
import java.util.Map;

import static org.example.entity.Country.RUSSIA;
import static org.example.entity.Country.USA;
import static org.mockito.Mockito.mock;

public class MessageSenderTest {

    @Mock
    GeoService geoService;
    @Mock
    LocalizationService localizationService;

    String testIPMosFull = "172.123.12.19";
    String testIPMosPart = "172.";
    String testIPNYFull = "96.44.183.149";
    String testIPNYPart = "96.";

    @Test
    @DisplayName("Проверяем полный Московский IP")
    void  MassageSenderTestMosFull () {
        geoService = mock(GeoService.class);
        Mockito.when(geoService.byIp(testIPMosFull))
                .thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15));
        localizationService = mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPMosFull);
        Assertions.assertEquals("Добро пожаловать", sender.send(headers));
    }

    @Test
    @DisplayName("Проверяем частичный Московский IP")
    void  MassageSenderTestMosPart () {
        geoService = mock(GeoService.class);
        Mockito.when(geoService.byIp(testIPMosPart))
                .thenReturn(new Location("Moscow", RUSSIA, null, 0));
        localizationService = mock(LocalizationService.class);
        Mockito.when(localizationService.locale(RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPMosPart);
        Assertions.assertEquals("Добро пожаловать", sender.send(headers));
    }

    @Test
    @DisplayName("Проверяем полный IP New York")
    void  MassageSenderTestNYFull () {
        geoService = mock(GeoService.class);
        Mockito.when(geoService.byIp(testIPNYFull))
                .thenReturn(new Location("New York", USA, " 10th Avenue", 32));
        localizationService = mock(LocalizationService.class);
        Mockito.when(localizationService.locale(USA))
                .thenReturn("Welcome");
        MessageSenderImpl sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPNYFull);
        Assertions.assertEquals("Welcome", sender.send(headers));
    }

    @Test
    @DisplayName("Проверяем частичный IP New York")
    void  MassageSenderTestNYPart () {
        geoService = mock(GeoService.class);
        Mockito.when(geoService.byIp(testIPNYPart))
                .thenReturn(new Location("New York", USA, null,  0));
        localizationService = mock(LocalizationService.class);
        Mockito.when(localizationService.locale(USA))
                .thenReturn("Welcome");
        MessageSenderImpl sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIPNYPart);
        Assertions.assertEquals("Welcome", sender.send(headers));
    }

}
