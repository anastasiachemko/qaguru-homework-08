package com.gmail.chemko.nast;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParameterizedWebTest {

    @ValueSource(strings = {"Zavtraki 24", "Barcarola"})
    @ParameterizedTest(name = "Тестирование общего алгоритма с тестовыми данными: {0}")
    void searchWithValueSourceTest(String testData) {
        open("https://eda.yandex.by");
        $("#id_1").setValue(testData).pressEnter();
        $(".DesktopSearchPlaceCarousel_title").shouldHave(Condition.text(testData));
    }

    @CsvSource(value = {
            "Zavtraki 24, $ЗавтракиЗдоровая едаБлины",
            "Barcarola, $ЕвропейскаяЗдоровая еда"
    })
    @ParameterizedTest(name = "Тестирование общего алгоритма с тестовыми данными: {0}")
    void searchWithCsvSourceTest(String testData, String expectedResult) {
        open("https://eda.yandex.by");
        $("#id_1").setValue(testData).pressEnter();
        $(".DesktopSearchPlaceCarousel_subTitle").shouldHave(Condition.text(expectedResult));
    }

    static Stream<Arguments> commonSearchDataProvider() {
        return Stream.of(
                Arguments.of("Zavtraki 24", "$ЗавтракиЗдоровая едаБлины"),
                Arguments.of("Barcarola", "$ЕвропейскаяЗдоровая еда"));
    }

    @MethodSource("commonSearchDataProvider")
    @ParameterizedTest(name = "Тестирование общего алгоритма с тестовыми данными: {0}")
    void searchWithMethodSourceTest(String testData, String expectedResult) {
        open("https://eda.yandex.by");
        $("#id_1").setValue(testData).pressEnter();
        $(".DesktopSearchPlaceCarousel_subTitle").shouldHave(Condition.text(expectedResult));
    }
}
