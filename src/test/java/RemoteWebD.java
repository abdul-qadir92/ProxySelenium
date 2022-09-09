import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpClient;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class RemoteWebD {

    @Test
    public void runBs() throws IOException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Edge");
        caps.setCapability("browser_version", "latest");

        WebDriver driver;
        driver =  connectViaProxy(caps);
        driver.get("https://bstackdemo.com/");
        driver.quit();
    }

    public RemoteWebDriver connectViaProxy(DesiredCapabilities caps) {

        //region ProxyCred
        String proxyHost = "?";
        int proxyPort = 8080;
        String proxyUser = "?";
        String proxyPassword = "?";
        //endregion

        URL url;

        try {
            url = new URL("http://bsuser:bspassword@hub-cloud.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        HttpClientBuilder builder = HttpClientBuilder.create();

        HttpHost proxy = new HttpHost(proxyHost, proxyPort);

        CredentialsProvider credsProvider = new BasicCredentialsProvider();

        credsProvider.setCredentials(new AuthScope(proxyHost, proxyPort),
                new UsernamePasswordCredentials(proxyUser, proxyPassword));

        if (url.getUserInfo() != null && !url.getUserInfo().isEmpty()) {
            credsProvider.setCredentials(new AuthScope(url.getHost(), (url.getPort() > 0 ? url.getPort() : url.getDefaultPort())), new UsernamePasswordCredentials(url.getUserInfo()));
        }

        builder.setProxy(proxy);
        builder.setDefaultCredentialsProvider(credsProvider);

        HttpClient.Factory factory = new MyHttpClientFactory(builder);

        HttpCommandExecutor executor = new HttpCommandExecutor(new HashMap<String, CommandInfo>(), url, factory);

        return new RemoteWebDriver(executor, caps);
    }

}
