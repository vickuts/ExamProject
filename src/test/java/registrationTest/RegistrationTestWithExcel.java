package registrationTest;

import baseTest.BaseTest;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static pages.ParentPage.configProperties;

@RunWith(Parameterized.class)
public class RegistrationTestWithExcel extends BaseTest {
    private String title, firstName, lastName, email, password, houseNumberAndStreetName,
            villageOrTownOrCity, province;
    private Double contactTelephone, zipCode;

    public RegistrationTestWithExcel(String title, String firstName, String lastName, String email,
                                     String password, Double contactTelephone, String houseNumberAndStreetName,
                                     String villageOrTownOrCity, String province, Double zipCode) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactTelephone = contactTelephone;
        this.houseNumberAndStreetName = houseNumberAndStreetName;
        this.villageOrTownOrCity = villageOrTownOrCity;
        this.province = province;
        this.zipCode = zipCode;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "TestData.xls");
        return new SpreadsheetData(inputStream, "Users").getData();
    }

    @Test
    public void newCustomerCreationFromExcel(){
        loginPage
                .openLoginPage()
                .clickOnButtonRegisterNow()
                .registrationWithDataFromExcel(title, firstName, lastName, email, password, contactTelephone,
                        houseNumberAndStreetName, villageOrTownOrCity, province, zipCode)
            .checkIsRedirectionOnAccountPage();
    }
}
