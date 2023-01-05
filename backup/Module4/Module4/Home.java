package QKART_SANITY_LOGIN.Module4;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("css-1urhf6j"), "Logout"));

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
    // TODO: CRIO_TASK_MODULE_XPATH - M1_1 Update locator using Dynamic Xpath to fix the error
    public Boolean searchForProduct(String product) {
        try {
            // Clear the contents of the search box and Enter the product name in the search
            // box
            WebElement searchBox = driver.findElement(By.xpath("//input[@name='search'][1]"));
            searchBox.clear();
            searchBox.sendKeys(product);
            if(product.equals("yonex")){
                product = product.toUpperCase();
            }
            // wait.until(ExpectedConditions.or(ExpectedConditions.textToBePresentInElementLocated(By.className("css-yg30ev6"), product),
            // ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[2]/div/h4"))));
            // WebDriverWait wait = new WebDriverWait(driver, 30);
            // wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated((By.xpath("//p[contains(text(),'"+product+"')]"))),
            // ExpectedConditions.visibilityOfElementLocated((By.xpath("//h4[contains(text(),'No products found')]")))));
            Thread.sleep(3000);
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
            // Find all webelements corresponding to the card content section of each of
            // search results
            searchResults = driver.findElementsByClassName("css-1qw96cp");
            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    // TODO: CRIO_TASK_MODULE_XPATH - M1_2 Update Xpath to fix the error
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // Check the presence of "No products found" text in the web page. Assign status
            // = true if the element is *displayed* else set status = false
            status = driver.findElementByXPath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div[2]/div/h4").isDisplayed();
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
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            // List<WebElement> gridContent = driver.findElementsByClassName("css-sycj1h");
            // for (WebElement cell : gridContent) {
            //     if (productName.contains(cell.findElement(By.className("css-yg30e6")).getText())) {
            //         cell.findElement(By.tagName("button")).click();

            //         WebDriverWait wait = new WebDriverWait(driver, 30);
            //         wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
            //                 String.format("//*[@class='MuiBox-root css-1gjj37g']/div[1][text()='%s']", productName))));
            //         return true;
            //     }
           // }
            // SLEEP_STMT_12: If product found, wait till the product gets added
            // successfully
            // System.out.println("Unable to find the given product: " + productName);
            // return false;

            WebElement checkAllProducts = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']"));
        
            if(checkAllProducts.getText().equals(productName)){
                //will find "ADD TO CART" button
                checkAllProducts = driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]"));
                checkAllProducts.click();
                Thread.sleep(3000);
                return true;
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
            // Find and click on the the Checkout button
            WebElement checkoutBtn = driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"));
            Thread.sleep(2000);
            checkoutBtn.click();
           
            status = true;
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
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {

            // Find the item on the cart with the matching productName

            // Increment or decrement the quantity of the matching product until the current
            // quantity is reached (Note: Keep a look out when then input quantity is 0,
            // here we need to remove the item completely from the cart)

            // Find web element corresponding to each of the cart items
            // WebElement cartParent = driver.findElement(By.className("cart"));
            // List<WebElement> cartContents = cartParent.findElements(By.className("css-zgtx0t"));

            // int currentQty;
            // for (WebElement item : cartContents) {
            //     // Find the matching product from the cart items
            //     if (productName.contains(item.findElement(By.xpath("//*[@class='MuiBox-root css-1gjj37g']/div[1]")).getText())) {
            //         currentQty = Integer.valueOf(item.findElement(By.className("css-olyig7")).getText());

            //         // Click on the + or - buttons appropriately to set the correct quantity of the
            //         // product
            //         while (currentQty != quantity) {
            //             if (currentQty < quantity) {
            //                 // increase Qty
            //                 item.findElements(By.tagName("button")).get(1).click();
                         
            //             } else {
            //                 // decrease Qty
            //                 item.findElements(By.tagName("button")).get(0).click();
            //             }

            //             synchronized (driver){
            //                 driver.wait(2000);
            //             }

            //             currentQty = Integer
            //                     .valueOf(item.findElement(By.xpath("//div[@data-testid=\"item-qty\"]")).getText());
            //         }

            //         return true;
            //     }
            // }
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
            System.out.println(("exception occurred when updating cart"));
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    // TODO: CRIO_TASK_MODULE_XPATH - M2_1 Update locators to use Xpath
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            // Get all the cart items as an array of webelements

            // Iterate through expectedCartContents and check if item with matching product
            // name is present in the cart

           // WebElement cartParent = driver.findElement(By.className("cart"));
            WebElement cartParent = driver.findElement(By.xpath("//div[@class='cart MuiBox-root css-0']"));

            List<WebElement> cartContents = cartParent.findElements(By.className("css-zgtx0t"));

            ArrayList<String> actualCartContents = new ArrayList<String>() {
            };
            for (WebElement cartItem : cartContents) {
                actualCartContents.add(cartItem.findElement(By.className("css-1gjj37g")).getText().split("\n")[0]);
            }

            for (String expected : expectedCartContents) {
                // To trim as getText() trims cart item title
                if (!actualCartContents.contains(expected.trim())) {
                    return false;
                }
            }

            return true;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
