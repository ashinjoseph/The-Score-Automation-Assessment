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

public class VerifyLeagueStats extends BaseTest {

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
    public void openLeagueStandings(String favourite, String playerTeamLeague) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        GetStartedPage getStartedPage = new GetStartedPage(driver);
        getStartedPage.getAppStarted(favourite);

        HomePage homePage = new HomePage(driver);
        homePage.verifyFavouriteScreenIsDisplayed(favourite);
        homePage.searchPlayerTeamLeague(playerTeamLeague);

        TeamPage teamPage = new TeamPage(driver);
        teamPage.verifyTeamScreenIsDisplayed(playerTeamLeague);
        teamPage.navigateToTeamStatsTab();

        homePage.navigateBackToSearchScreen();
        homePage.navigateBackToHomeScreen(favourite);
    }
}
