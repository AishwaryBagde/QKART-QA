package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addresString) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Click on the "Add new address" button, enter the addressString in the address //button[contains(text(),'Add new address')]
             * text box and click on the "ADD" button to save the address //div[@class='css-19a1170']
             */
            WebElement addNewAddress = driver.findElement(By.xpath("//button[contains(text(),'Add new address')]"));
            // addNewAddress.getText().equals("Add new address");
            addNewAddress.click();

            WebElement txtBox = driver.findElement(By.xpath("//textarea[@placeholder='Enter your complete address']"));
            txtBox.sendKeys(addresString);

            WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
            addButton.click();

            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred while entering address: " + e.getMessage());
            return false;

        }
    }

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through all the address boxes to find the address box with matching
             * text, addressToSelect and click on it 
             */
        //    WebElement address = driver.findElement(By.xpath("//p[contains(text(),'Addr line 1 addr Line 2 addr line 3')]"));
        //    address.getText().equals(addressToSelect);

        //    WebElement radioButton = driver.findElement(By.xpath("//input[@type='radio']"));
        //    radioButton.click();

        //     System.out.println("Unable to find the given address");
        //     return false;
        // } catch (Exception e) {
        //     System.out.println("Exception Occurred while selecting the given address: " + e.getMessage());
        //     return false;
        // }
        List<WebElement> radioButton =  driver.findElements(By.xpath("//input[@name='address']/../..//p"));
        for(WebElement elem:radioButton){
            if(elem.getText().contains(addressToSelect)){
                // System.out.println("fff");
                elem.click();
                return true;
            }  
        }

            System.out.println("Unable to find the given address");
            return false;
        } catch (Exception e) {
            System.out.println("Exception Occurred while selecting the given address: " + e.getMessage());
            return false;
        }
      

    }

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find the "PLACE ORDER" button and click on it
            WebElement placeOrderButton = driver.findElement(By.xpath("//button[contains(text(),'PLACE ORDER')]"));
            placeOrderButton.click();

            return false;

        } catch (Exception e) {
            System.out.println("Exception while clicking on PLACE ORDER: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage(String errorMsg) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 08: MILESTONE 7
            WebElement popUp = driver.findElement(By.xpath("//div[@class='SnackbarItem-message']"));
           // System.out.println(popUp.getText().equalsIgnoreCase(errorMsg));
            if(popUp.getText().equalsIgnoreCase(errorMsg)){
                return true;
            }
            
            return false;
        } catch (Exception e) {
            System.out.println("Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
