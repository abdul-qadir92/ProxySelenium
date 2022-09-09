import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.internal.ApacheHttpClient;

import java.net.URL;

public class MyHttpClientFactory implements org.openqa.selenium.remote.http.HttpClient.Factory{
    final HttpClientBuilder builder;
    public MyHttpClientFactory(HttpClientBuilder builder) {
        this.builder = builder;
    }
    @Override
    public HttpClient createClient(URL url) {
        return new ApacheHttpClient(builder.build(), url);
    }

    @Override
    public void cleanupIdleClients() {
    }
}
