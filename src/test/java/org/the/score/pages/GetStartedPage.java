package org.the.score.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import org.the.score.common.pages.BasePage;

public class GetStartedPage extends BasePage {
    private final AndroidDriver driver;

    public GetStartedPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private final String btn_GetStarted_Id = "com.fivemobile.thescore:id/action_button_text";
    private final String btn_Continue_Id = "com.fivemobile.thescore:id/btn_primary";
    private final String btn_AllowLocation_Id = "com.fivemobile.thescore:id/btn_allow";
    private final String btn_AllowPermission_Id = "com.android.packageinstaller:id/permission_allow_button";
    private final String lbl_FavTeam_Xpath = "//android.widget.TextView[@text='%s']";
    private final String btn_Done_Id = "com.fivemobile.thescore:id/btn_primary";
    private final String btn_MayBeLater_Id = "com.fivemobile.thescore:id/btn_secondary";
    private final String btn_CloseBanner_Id = "com.fivemobile.thescore:id/dismiss_modal";

    @Step("Launch the App and complete pre-requisites")
    public void getAppStarted(String favourite){
        clickAndTransition(MobileBy.id(btn_GetStarted_Id));
        clickAndTransition(MobileBy.id(btn_Continue_Id));
        clickAndTransition(MobileBy.id(btn_AllowLocation_Id));
        clickAndTransition(MobileBy.id(btn_AllowPermission_Id));
        clickAndTransition(MobileBy.xpath(String.format(lbl_FavTeam_Xpath,favourite)));
        clickAndTransition(MobileBy.id(btn_Continue_Id));
        clickAndTransition(MobileBy.id(btn_Done_Id));
        clickAndTransition(MobileBy.id(btn_MayBeLater_Id));
        clickAndTransition(MobileBy.id(btn_CloseBanner_Id));

    }
}
