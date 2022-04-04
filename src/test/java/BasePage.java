import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage extends BaseTest {

    Logger logger = LogManager.getLogger(BasePage.class);


    @Step("<int> saniye kadar bekle")
    public void waitForSecond(int s) throws InterruptedException {
        Thread.sleep(1000 * s);
    }

    @Step("<id> elementini bul ve tıkla")
    public void findByElementEndClick(String id) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        if (element.isDisplayed()) {
            element.click();
        } else {
            com.thoughtworks.gauge.Logger.info("Kontrol edilen element görüntülenemedi.");

        }
    }

    @Step("id <id> li elementi bul ve <text> alanını kontrol et")
    public void textIdAreaControl(String id, String text) {
        Assert.assertTrue("başarısız işlem", appiumDriver.findElement(By.id(id)).getText().contains(text));
        com.thoughtworks.gauge.Logger.info("Element görüntülendi.");
    }

    @Step("Sayfayı aşağı doğru kaydır")
    public void swipeUp() {
        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        // init screen variables
        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefon Ekran Boyutu " + dims);
        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        System.out.println("Başlangıç noktası " + pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 2, dims.height / 4);
        System.out.println("Bitiş noktası " + pointOptionEnd);
        try {
            new TouchAction(appiumDriver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
    }
        @Step("xpath <xpath> li elementi bul ve <text> alanını kontrol et")
        public void textXpathAreaControl (String xpath, String text)
        {
            Assert.assertTrue("başarısız işlem", appiumDriver.findElement(By.xpath(xpath)).getText().contains(text));
            com.thoughtworks.gauge.Logger.info("Element görüntülendi.");
        }


        @Step("Xpath <xpath> li elementi bul ve tıkla")
        public void clickByXpath (String xpath)
        {
            appiumDriver.findElement(By.xpath(xpath)).click();
        }


        @Step("Rastgele ürün seçilir.")
        public void RandomChoose ()
        {
            Random random = new Random();
            List<MobileElement> randomProduct = appiumDriver.findElements(By.xpath("//*[@resource-id='com.ozdilek.ozdilekteyim:id/imageView']"));
            MobileElement element = randomProduct.get(random.nextInt(randomProduct.size()));
            element.click();
        }


        @Step("Id <id> li elementi bul ve tıkla")
        public void clickById (String id)
        {
            appiumDriver.findElement(By.id(id)).click();
        }

        @Step("Id <id> li elementi bul ve <text> değerini gir")
        public void sendKeyById (String id, String text)
        {
            appiumDriver.findElement(By.id(id)).sendKeys(text);
            com.thoughtworks.gauge.Logger.info(text + " " + "ID'li kısıma yazıldı.");
        }

        @Step("Id <id> li elementi bul")
        public void findElementArea (String id)
        {
            appiumDriver.findElement(By.id(id));
            com.thoughtworks.gauge.Logger.info("ID'li kısım bulunudu");
        }
    }
