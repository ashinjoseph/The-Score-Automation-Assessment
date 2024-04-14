package org.the.score.test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.the.score.common.test.BaseTest;
import org.the.score.pages.GetStartedPage;
import org.the.score.pages.HomePage;
import org.the.score.pages.TeamPage;
import io.qameta.allure.*;

import java.util.concurrent.TimeUnit;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class VerifyTeamStats extends BaseTest {

    @DataProvider(name = "teamDetails")
    public Object[][] teamDetails() {
        return new Object[][]{
                {"Canada", "Toronto Maple Leafs"}
              //  {"Toronto Raptors", "Toronto Blue Jays"}
        };
    }

    @Test(alwaysRun = true, dataProvider = "teamDetails")
    @Description("This test attempts to verify that team stats can be viewed")
    @Severity(CRITICAL)
    public void searchAndOpenTeamStats(String favourite, String playerTeamNews) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        GetStartedPage getStartedPage = new GetStartedPage(driver);
        getStartedPage.getAppStarted(favourite);

        HomePage homePage = new HomePage(driver);
        sAssert.assertTrue(homePage.isHomeScreenFavouriteDisplayed(favourite), "Getting started was unsuccessful and Home Screen (Favourite) is not displayed");
        homePage.searchPlayerTeamNews(playerTeamNews);

        TeamPage teamPage = new TeamPage(driver);
        sAssert.assertTrue(teamPage.isTeamScreenDisplayed(playerTeamNews), "Team name search and selection was unsuccessful and Team screen is not displayed");
        teamPage.navigateToTeamStatsTab();
        sAssert.assertTrue(teamPage.isTeamStatsTabOpen(), "Team stats tab is not open");

        homePage.navigateBack();
        sAssert.assertTrue(homePage.isSearchScreenDisplayed(), "Back Navigation was unsuccessful and Search Screen is not displayed");
        homePage.navigateBack();
        sAssert.assertTrue(homePage.isHomeScreenFavouriteDisplayed(favourite), "Back Navigation was unsuccessful and Home Screen (Favourite) is not displayed");
    }
}
