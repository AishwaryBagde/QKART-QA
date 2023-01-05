package QKART_TESTNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener{
    

    public void onTestStart(ITestResult result) {
        System.out.println("Test Started" +result.getName());
        QKART_Tests.takeScreenshot("Start Test Case" +" "+result.getName(), " Taking Screenshot ! ") ;
    }

    // When Test case get failed, this method is called.		
   	
    public void onTestFailure(ITestResult result) {
       // System.out.println("Test Failed : " + result.getName() + " Taking Screenshot !");
        QKART_Tests.takeScreenshot("Failing Test Case", " Taking Screenshot ! ") ;
    }
  	
    public void onFinish(ITestContext context) {
        System.out.println("onFinish method started");
        QKART_Tests.takeScreenshot("End Test Case", " Taking Screenshot ! ") ;
    }
    
}