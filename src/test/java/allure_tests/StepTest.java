package allure_tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepTest {


    private static String Repository = "allure-examples/allure-examples";
    private static Integer Issue = 38;

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий" + Repository, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(Repository);
            $(".header-search-input").submit();
        });

        step("Кликаем по ссылке репозитория" + Repository, () ->{
            $(linkText(Repository)).click();
        });

        step("Открываем Issue", () ->{
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с номером", () ->{
            $(withText("#" + Issue)).should(Condition.exist);
        }) ;
    }

    @Test
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(Repository);
        steps.clickOnRepositoryLink(Repository);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(Issue);
    }

}
