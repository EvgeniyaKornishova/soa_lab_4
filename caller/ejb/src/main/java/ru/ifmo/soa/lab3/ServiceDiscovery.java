package ru.ifmo.soa.lab3;


import com.sun.mail.iap.ConnectionException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServiceDiscovery {
    private String serviceDiscoveryUrl;
    private String proxyUrl;

    public ServiceDiscovery() {
        try (InputStream input = ServiceDiscovery.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();

            if (input == null) {
                serviceDiscoveryUrl = null;
                System.out.println("Properties file not found!");
                return;
            }

            prop.load(input);

            serviceDiscoveryUrl = prop.getProperty("discovery.url");
            proxyUrl = prop.getProperty("proxy.url");
        } catch (IOException ex){
            System.out.println("Properties file not found!");
            serviceDiscoveryUrl  = null;
            proxyUrl = null;
        }
    }


    public String getUriFromEureka() throws ConnectionException, IOException {
        Client client = ClientBuilder.newBuilder().build();

        WebTarget target = client.target(serviceDiscoveryUrl);

        Response response;
        try {
            response = target.path("apps").path("person_service").request().get();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

        if (response.getStatus() != 200) {
            throw new ConnectionException();
        }

        String body = response.readEntity(String.class);

        String ip = body.substring(body.indexOf("<hostName>")+"<hostName>".length(), body.indexOf("</hostName>"));
        String port = body.substring(body.indexOf("<port enabled=\"false\">") + "<port enabled=\"false\">".length(), body.indexOf("</port>"));

        if (ip.isEmpty() || port.isEmpty())
            return null;

        String url = "https://" + ip + ":" + port + "/";
        System.out.println("Person service url: " + url);
        return url;
    }

    public String getProxyUrl() {
        return proxyUrl + "/api";
    }

}
