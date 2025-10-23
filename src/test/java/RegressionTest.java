import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class RegressionTest {
    @Test
    public void regressionTest() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://dodopizza.ru");

            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Москва")).first().click();
            page.locator("a").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Пиццы$"))).click();
            page.locator("#guzhy").getByTestId("menu__meta-product_11ee788f97bb5f983f3aede9318dff30").getByTestId("product__button").click();
            page.getByTestId("menu__pizza_size_35 см").click();
            page.getByText("Тонкое").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Креветки Креветки 250 ₽")).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Сыры чеддер и пармезан Сыры чеддер и пармезан 99 ₽")).click();
            page.getByTestId("button_add_to_cart").click();
            page.getByTestId("how_to_get_order_pickup_action").click();
            page.getByText("ул. Авиационная, 66Открыто до 23:").click();
            page.getByTestId("carryout-pizzeria-select-button").click();
            page.getByTestId("menu__meta-product_11eef9e2819b2585873161587eaf0af0").getByTestId("product__button").click();
            page.getByTestId("menu__pizza_size_25 см").click();
            page.getByTestId("button_add_to_cart").click();
            page.getByTestId("navigation__cart").click();

            page.waitForTimeout(3000);

            Locator shrimpPizza = page.locator("article").filter(new Locator.FilterOptions().setHasText("Креветки со сладким чили35"));
            shrimpPizza.getByTestId("amount-add").click();
            page.waitForTimeout(1000);
            shrimpPizza.getByTestId("amount-add").click();
            page.waitForTimeout(1000);
            shrimpPizza.getByRole(AriaRole.BUTTON).nth(1).click();
            page.waitForTimeout(1000);

            page.locator("article").filter(new Locator.FilterOptions().setHasText("Бефстроганов25")).getByRole(AriaRole.BUTTON).first().click();
            page.waitForTimeout(1000);

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Соусы")).click();
            page.waitForTimeout(1000);
            page.locator("article:nth-child(4) > div:nth-child(3) > .sc-qlzmyl-0").click();
            page.waitForTimeout(1000);
            page.locator("article").filter(new Locator.FilterOptions().setHasText("Барбекю49 ₽")).getByTestId("product__button").click();
            page.waitForTimeout(1000);
            page.locator(".card-close").click();
            page.waitForTimeout(1000);
            page.locator("article").filter(new Locator.FilterOptions().setHasText("Сырный1 шт49 ₽Изменить")).getByTestId("amount-add").click();
            page.waitForTimeout(1000);
            page.locator("article").filter(new Locator.FilterOptions().setHasText("Барбекю1 шт49 ₽Изменить")).getByRole(AriaRole.BUTTON).nth(1).click();
            page.waitForTimeout(1000);

            page.waitForTimeout(7000);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
