import okhttp3.*;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.Authenticator;
import java.nio.charset.StandardCharsets;

public class TestHttp {

    @Test
    public void checkapi() throws IOException {
        URL url = new java.net.URL("http://hub.browserstack.com/wd/hub/status");

        //region Authenticator
        final String authUser = "user";
        final String authPassword = "password";
        Authenticator.setDefault(
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(authUser, authPassword.toCharArray());
                    }
                }
        );
        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", ""); //jdk.https.auth.tunneling.disabledSchemes
        //endregion

        //region HTTPProxy
        /*System.getProperties().put("https.proxyHost", "localhost");
        System.getProperties().put("https.proxyPort", "8888");
        System.getProperties().put("https.proxyUser", "<USER>");
        System.getProperties().put("https.proxyPassword", "<PASSWORD>");

        System.getProperties().put("http.proxyHost", "localhost");
        System.getProperties().put("http.proxyPort", "8888");
        System.getProperties().put("http.proxyUser", "<USER>");
        System.getProperties().put("http.proxyPassword", "<PASSWORD>");*/
        //endregion

        //region SystemProxy
        //System.getProperties().put("java.net.useSystemProxies", "true");
        //endregion

        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8888)); //Will need Authenticator for authentication

        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        boolean proxyused = http.usingProxy();
        System.out.println("ProxyUsed? "+proxyused);
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }

    @Test
    public void runapi() throws IOException {
        URL url = new URL("https://hub-cloud.browserstack.com/wd/hub/session");
        //URL url = new URL("http://usrkey:accesskey@hub-cloud.browserstack.com/wd/hub/session");//Gives 401 but works when with user and pass in body
        HttpURLConnection httpClient = (HttpURLConnection) url.openConnection();

        //add request header
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json");
        httpClient.setRequestProperty("Accept", "*/*");

        httpClient.setDoOutput(true);
        String jsonInputString = "{\"capabilities\":{\"firstMatch\":[{}],\"alwaysMatch\":{}},\"desiredCapabilities\":{\"name\":\"http-session\",\"browserstack.user\":\"userkey\",\"browserstack.key\":\"accesskey\"}}";
        //String jsonInputString = "{\"capabilities\":{\"firstMatch\":[{}],\"alwaysMatch\":{}},\"desiredCapabilities\":{\"name\":\"http-session\"}}";
        OutputStream os = httpClient.getOutputStream();
        byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);

        BufferedReader br = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }
        System.out.println(response);
        httpClient.disconnect();
    }

    @Test
    public void whenPostJson_thenCorrect() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String json = "{\"capabilities\":{\"firstMatch\":[{}],\"alwaysMatch\":{}},\"desiredCapabilities\":{\"name\":\"http-session\",\"browserstack.user\":\"userkey\",\"browserstack.key\":\"accesskey\"}}";
        URL url = new URL("https://hub-cloud.browserstack.com/wd/hub/session");
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());
    }

}
