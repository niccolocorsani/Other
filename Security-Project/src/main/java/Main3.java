import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import okhttp3.internal.http.RequestLine;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.nio.charset.UnsupportedCharsetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Main3 {


    public static void main(String[] args) throws InterruptedException {


        //ecessario per farlo funzionare
        WebDriver driver = FactoryDriver.getChromeDriver();
        WebDriver driver2 = FactoryDriver.getFireFoxDriver();
        driver2.quit();
        // driver.get("https://www.google.com/");

        driver.quit();
        BrowserMobProxy proxy = new BrowserMobProxyServer();

        proxy.addResponseFilter(new ResponseFilter() {
            @Override
            public void filterResponse(HttpResponse httpResponse, HttpMessageContents httpMessageContents, HttpMessageInfo httpMessageInfo) {
                System.out.println("risposta.............. " + httpResponse.getStatus().reasonPhrase());


                try {
                    System.err.println(LocalDateTime.now() + "    stato    " + httpResponse.getStatus());
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        proxy.addRequestFilter(new RequestFilter() {

            @Override
            public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {

                System.out.println("richiesta.............. \n\n");

                try {

                    System.out.println("Prima "+request.headers().get(HttpHeaders.Names.USER_AGENT));
                    //if(request.getUri().contains("http://detectportal.firefox.com/success.txt"))
                     request.headers().set(HttpHeaders.Names.USER_AGENT, " ' ");
                //    System.out.println("Dopo "+request.headers().get(HttpHeaders.Names.USER_AGENT));
                     System.out.println("uri   "+request.getUri());


/*
                    System.out.println("contenuto  "+contents.getTextContents());
                    System.out.println("uri prima  "+request.getUri());

                    if(request.getUri().toString().contains("http://testphp.vulnweb.com/artists.php?artist=1")) {
                        System.out.println("intercepted cat");
                       request.setUri("http://testphp.vulnweb.com/artists.php?artist=1%20order%20by%204");


                    }
                    System.out.println("Decoder result " + request.getDecoderResult());
                    System.out.println("uri dopo  " + request.getUri());
                    System.err.println(request.getMethod().name());
                    System.err.println(messageInfo.getOriginalUrl());
*/


                    for (Map.Entry<String, String> entryMap : request.headers().entries())
                        System.err.println(LocalDateTime.now() + "    entry    " + entryMap.getKey() + "      " + entryMap.getValue());
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }

                return null;
            }
        });


        proxy.start(); // can specify a port here if you like


        // get the selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        seleniumProxy.setHttpProxy("localhost:" + proxy.getPort()); //The port generated by server.start();

        ///Funziona con rete spadolini, ma non le altre
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        //WebDriver driver1 = new ChromeDriver(capabilities);
        WebDriver driver1 = new FirefoxDriver(capabilities);
        driver1.get("localhost:9000");
        WebDriver driver3= new FirefoxDriver(capabilities);
        driver3.get("localhost:8080");
        // driver2.get("https://www.google.com/");

        proxy.newHar("ilmiohar");
        System.err.println("ooooo");


        Har har = proxy.getHar();

        List<HarEntry> results = har.getLog().getEntries();

     /*   try {
            for (HarEntry result : results) {
                for (int i = 0; i < 20; i++) {
                    if (result.getRequest().getQueryString().get(i).getName().contentEquals("action")) {
                        System.out.println("action value: " + result.getRequest().getQueryString().get(i).getValue());
                    }
                }

            }
        }
        catch(Exception e){
            }*/
    }


}
