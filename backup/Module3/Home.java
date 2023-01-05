package QKART_SANITY_LOGIN.Module1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // SLEEP_STMT_10: Wait for Logout to complete
            // Wait for Logout to Complete
            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }
    /*
        * Returns Boolean if searching for the given product name occurs without any
        * errors
        */
    public Boolean searchForProduct(String product) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Clear the contents of the search box and Enter the product name in the search //input[@name = 'search'][1]
            // box
          
            WebElement searchBox = driver.findElement(By.xpath("//div[contains(@class,'search-desktop')]//input"));
            searchBox.clear();
            searchBox.sendKeys(product);
            if(product.equals("yonex")){
                product = product.toUpperCase();
            }
           

        //    WebDriverWait wait = new WebDriverWait(driver, 10);
        //     WebElement noProductsFound = driver.findElement(By.xpath("//h4[text()=' No products found ']"));
        //     //WebElement allProduct = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']"));
        //     if(!(noProductsFound.isDisplayed())){
        //         wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
        //        // (By.xpath("//div[@class='MuiCardContent-root css-1qw96cp']/p[contains(text(),'"+product+"')]")));
        //     }
    
         
		
            // WebDriverWait wait = new WebDriverWait(driver,5);
            //  		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[contains(text(),'"+product+"')]"))));
            
             WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                             (By.xpath("//div[contains(@class,'css-sycj1h')]//p[contains(text(),'"+product+"')]"))),
                       ExpectedConditions.visibilityOfAllElementsLocatedBy(
                             (By.xpath("//div[contains(@class,'loading MuiBox-root css-0')]//h4[contains(text(),'No products found')]")))));
           
            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>() {
        };
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1 //div[@class='MuiCardContent-root css-1qw96cp']
            // Find all webelements corresponding to the card content section of each of //img[contains(@class,'MuiCardMedia-root')]/..//p[1]
            // search //p[contains(@class,'css-yg30e')]
            //
            Thread.sleep(2000);
            searchResults = driver.findElements(By.xpath("//p[contains(@class,'css-yg30e')]"));
            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Check the presence of "No products found" text in the web page. Assign status 
            // = true if the element is *displayed* else set status = false  //div[@class='loading MuiBox-root css-0']
            //WebElement noResultsFound = driver.findElement(By.xpath("//h4[contains(text(),' No products found ')]"));

            WebElement noresult = driver.findElement(By.xpath("//h4[contains(text(),' No products found ')]"));
            noresult.isDisplayed();     

            // Thread.sleep(3000);
           return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * Click on the "ADD TO CART" button for that element //button[contains(text(),'Add to cart')
             * Return true if these operations succeeds
             */
            // List<WebElement> checkAllProducts = driver.findElements(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']"));
        
            // for(WebElement allProduct :  checkAllProducts ){
            //     if(allProduct.getText().equals(productName)){
            //         //will find "ADD TO CART" button
            //         allProduct = driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]"));
            //         allProduct.click();
            //         Thread.sleep(3000);
            //         return true;
            //     }

            // }
            

            List<WebElement> gridContent = driver.findElementsByClassName("css-sycj1h");
            for (WebElement cell : gridContent) {
                if (cell.findElement(By.className("css-yg30e6")).getText().equalsIgnoreCase(productName)) {
                    cell.findElement(By.tagName("button")).click();
                    return true;
                }
            }   


            System.out.println("Unable to find the given product");
            return false;
            
        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find and click on the the Checkout button
            WebElement checkOutButton = driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"));
            checkOutButton.click();
            return status;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart
     * operation
     */
    // public Boolean changeProductQuantityinCart(String productName, int quantity) {
    //     try {
    //         // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5

    //         // Find the item on the cart with the matching productName

    //         // Increment or decrement the quantity of the matching product until the current
    //         // quantity is reached (Note: Keep a look out when then input quantity is 0,
    //         // here we need to remove the item completely from the cart)
            
    //         WebElement addButton = driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]/..//*[@data-testid='AddOutlinedIcon']/.."));
    //         WebElement removeButton = driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]/..//*[@data-testid='RemoveOutlinedIcon']/.."));
    //         WebElement cartItem = driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]/..//div//div//div"));
    //         int a = 0;
    //         // FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout((Duration.ofSeconds(30L)))
	// 		// 				.pollingEvery(Duration.ofMillis(250)).ignoring(Exception.class);
    //         int itemInCart = cartItem.getText().length();
    //         if(quantity>itemInCart) {
    //             for (a = 1; a < quantity; a++) {
    //                 // Thread.sleep(2000);
    //                 WebDriverWait wait = new WebDriverWait(driver, 10);
    //                 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'"+a+"')]")));
    //                 addButton.click();
    //             }
    //         }
    //         else if(quantity<itemInCart){
    //             for (a = itemInCart; a > quantity; a--) {
                    
    //                 // Thread.sleep(2000);
    //                 WebDriverWait wait = new WebDriverWait(driver, 10);
    //                 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'"+a+"')]")));
    //                 removeButton.click();
    //             }
    //         }

    //         return false;
    //     } catch (Exception e) {
    //         if (quantity == 0)
    //             return true;
    //         System.out.println("exception occurred when updating cart: " + e.getMessage());
    //         return false;
    //     }
    // }

//////////////////////////////////////////////////////////////AR
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
     try {
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5

        // Find the item on the cart with the matching productName

        // Increment or decrement the quantity of the matching product until the current
        // quantity is reached (Note: Keep a look out when then input quantity is 0,
        // here we need to remove the item completely from the cart)
        String itemNameOnCart = driver.findElement(By.xpath("//div[@class='MuiBox-root css-1gjj37g']/div")).getText();
        String cartItemCountString = driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]//following-sibling::div//*[@class='MuiBox-root css-olyig7']")).getText();
        int cartItemCount =Integer.parseInt(cartItemCountString);
        WebElement addBtn = driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]/following-sibling::div[@class='MuiBox-root css-69i1ev']/div//button[2]"));
        WebElement removeBtn = driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]/following-sibling::div[@class='MuiBox-root css-69i1ev']/div//button[1]"));
        if(quantity > cartItemCount){
                for(int i=cartItemCount; i<quantity; i++){
                    //Thread.sleep(3000);
                    WebDriverWait wait = new WebDriverWait(driver, 1);
                    String s = "//div[contains(text(),'"+i+"')]";
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(s)));
                    addBtn.click();
                    
                }
                return true;
            }
            else if(quantity < cartItemCount){
                for(int i=cartItemCount; i>quantity; i--){
                    WebDriverWait wait = new WebDriverWait(driver, 1);
                    String s = "//div[contains(text(),'"+i+"')]";
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(s)));
                    removeBtn.click();
                    
                }
                return true;
            }

        return false;
    } catch (Exception e) {
        if (quantity == 0)
            return true;
        System.out.println("exception occurred when updating cart: " + e.getMessage());
        return false;
    }
}






    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6

            // Get all the cart items as an array of webelements

            // Iterate through expectedCartContents and check if item with matching product
            // name is present in the cart
            WebElement checkOutButtn = driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"));
           
            if(checkOutButtn.isDisplayed()){
                List<WebElement> cartContents = driver.findElements(By.xpath("//div[@class='MuiBox-root css-1gjj37g']"));
                String s ;
                String arr[] ;
                int flag = 0;

    
                    for (int i =0; i<expectedCartContents.size();i++){
                      //  System.out.println(cartContents.get(i).getText().trim());
                        s = cartContents.get(i).getText().trim();
                        arr = s.split("\n");
                       // System.out.println(arr[0]);
                        if (expectedCartContents.get(i).equalsIgnoreCase(arr[0])){
                            flag++;
                        }
                        
                    }
                if(flag == expectedCartContents.size()){
                    return true;
                }
            }
           
          

            System.out.println("Contents does not match");
            return false;
            

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
