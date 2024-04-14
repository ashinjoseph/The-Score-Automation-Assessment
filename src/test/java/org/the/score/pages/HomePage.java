package org.the.score.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.the.score.common.pages.BasePage;

public class HomePage extends BasePage {
    private final AndroidDriver driver;

    public HomePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    private final String lbl_FavTeam_Xpath = "//android.widget.TextView[@text='%s']";
    private final String lbl_SearchPanel_Id = "com.fivemobile.thescore:id/search_bar_text_view";
    private final String lbl_SearchResult_Xpath = "//android.widget.TextView[@text='%s']";
    private final String txt_Search_Id = "com.fivemobile.thescore:id/search_src_text";

    @Step("Verify that Home Screen (Favourite) is displayed")
    public boolean isHomeScreenFavouriteDisplayed(String favourite) {
        String favouriteTeam = favourite.substring(0, 3).toUpperCase();
        WebElement lbl_FavTeam = driver.findElement(MobileBy.xpath(String.format(lbl_FavTeam_Xpath, favouriteTeam)));
        return isElementWithTextDisplayed(lbl_FavTeam, favouriteTeam);
    }

    @Step("Inputs a search string in search bar and opens the result")
    public void searchPlayerTeamNews(String playerTeamNews) {
        driver.findElement(MobileBy.id(lbl_SearchPanel_Id)).click();
        driver.findElement(MobileBy.id(txt_Search_Id)).sendKeys(playerTeamNews);
        WebElement lbl_SearchResult = driver.findElement(MobileBy.xpath(String.format(lbl_SearchResult_Xpath, playerTeamNews)));
        if(isElementWithTextDisplayed(lbl_SearchResult, playerTeamNews)){
            lbl_SearchResult.click();
        }
    }

    @Step("Verify that Search Screen is displayed")
    public boolean isSearchScreenDisplayed() {
        return driver.findElement(MobileBy.id(txt_Search_Id)).isDisplayed();
    }
}