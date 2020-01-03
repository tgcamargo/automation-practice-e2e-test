package com.hfchallenge.support;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hfchallenge.pages.*;
import com.hfchallenge.utils.DateUtils;
import com.hfchallenge.utils.ScreenShotUtils;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public AutenticationPage autenticationPage;
    public AccountCreationPage accountCreationPage;
    public MenuSection menuSection;
    public WomenPage womenPage;
    public ProductPage productPage;
    public CheckoutFooterSection checkoutFooterSection;
    public ShippingPage shippingPage;
    public PaymentPage paymentPage;
    public OrderConfirmationPage orderConfirmationPage;
    public static ExtentReports extent;
    public ExtentTest test;
    public static ExtentHtmlReporter htmlReporter;
    private static StringBuilder testLog = new StringBuilder();
    public static DateUtils dateUtils = new DateUtils();
    protected WebDriverFactory factory = new WebDriverFactory();

    @BeforeClass
    public static void beforeClass(){
        // initialize the HtmlReporter
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/target/testReport.html");

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        //Adding system or environment info
        extent.setSystemInfo("OS", System.getProperty("OS").toUpperCase());
        extent.setSystemInfo("Browser", System.getProperty("browser").toUpperCase());

        //configuration items to change the look and feel
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Automation Practice - Test Report");
        htmlReporter.config().setReportName("Automation Practice - Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'");

        //Set Initial line of the log
        testLog.append("###################### TEST EXECUTION STARTS IN: ").append(dateUtils.getCurrentDate()).append(" ######################\n");
    }

    @AfterClass
    public static void afterClass() throws IOException {
        //Set Final line of the log and write the log information (testLog) on the file /target/testLog.txt
        testLog.append("###################### TEST EXECUTION ENDS IN: ").append(dateUtils.getCurrentDate()).append(" ######################\n\n");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(System.getProperty("user.dir") +"/target/testLog.txt", true)));
        out.append(testLog.toString());
        out.close();
    }

    @Before
    public void setUp() {
        //Setup WebDriver
        driver = factory.createInstance(System.getProperty("browser"), System.getProperty("OS"));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(System.getProperty("url"));

        //Setup PageObjects
        autenticationPage = new AutenticationPage(driver);
        accountCreationPage = new AccountCreationPage(driver);
        menuSection = new MenuSection(driver);
        womenPage = new WomenPage(driver);
        productPage = new ProductPage(driver);
        checkoutFooterSection = new CheckoutFooterSection(driver);
        shippingPage = new ShippingPage(driver);
        paymentPage = new PaymentPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
    }

    @Rule
    public final TestRule watchman = new TestWatcher(){
        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        // This method gets invoked if the test fails for any reason:
        @Override
        protected void failed(Throwable e, Description description) {
            testLog.append(dateUtils.getCurrentDate()).append(" - ");

            if (description != null) {
                testLog.append(description);
            }

            testLog.append(" FAIL\n");

            if (e != null) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                testLog.append(exceptionAsString);
            }

            try {
                String screenShotPath;
                screenShotPath = ScreenShotUtils.capture(driver, "screenshot");
                test.log(Status.FAIL, MarkupHelper.createLabel(" Test case FAILED due to below issues:", ExtentColor.RED));
                test.fail(e);
                test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // This method gets invoked if the test success:
        @Override
        protected void succeeded(Description description) {
            testLog.append(dateUtils.getCurrentDate()).append(" - ");
            if (description != null) { testLog.append(description); }
            testLog.append(" SUCCESS\n");
        }

        // This method gets called when the test finishes, regardless of status
        // If the test fails, this will be called after the method above
        @Override
        protected void finished(Description description) {
            if (driver != null){
                extent.flush();
                driver.quit();
            }
        }
    };
}