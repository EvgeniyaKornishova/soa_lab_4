package ru.ifmo.soa.lab3;

import com.sun.mail.iap.ConnectionException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.glassfish.jersey.SslConfigurator;
import ru.ifmo.soa.lab3.XMLUtils.PersonList;
import ru.ifmo.soa.lab3.data.Color;

import javax.ejb.Stateless;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Stateless(name="DemographyInfo")
public class DemographyInfoBean implements DemographyInfo {

    private final ServiceDiscovery serviceDiscovery;

    @AllArgsConstructor
    @Getter
    public static class Pair{
        private final Long chosen;
        private final Long amount;
    }

    public DemographyInfoBean() {
        serviceDiscovery = new ServiceDiscovery();
    }

    private Pair countPersonsByHairColor(Color hair_color) throws ConnectionException, IOException{
        Client client = ClientBuilder.newBuilder().build();
//        WebTarget target = client.target(serviceDiscovery.getUriFromEureka());
        WebTarget target = client.target(serviceDiscovery.getProxyUrl());

        Response response = target.path("persons").queryParam("hair_color", hair_color.toString()).request(MediaType.APPLICATION_XML).get();

        if (response.getStatus() != 200) {
            throw new ConnectionException();
        }

        PersonList personList;

        try {
            personList = response.readEntity(PersonList.class);
        } catch (Exception e){
            System.out.println(e);
            return new Pair(0L, 0L);
        }

        if (personList.getPersons() == null)
            return new Pair(0L, 0L);

        return new Pair((long) personList.getPersons().size(), personList.getCount());
    }

    @Override
    public Long getAmountOfPeopleByHairColor(Color hair_color){
        Long personsCount;

        try{
            personsCount = countPersonsByHairColor(hair_color).getChosen();
        } catch (Exception e) {
            return null;
        }

        return personsCount;
    }

    @Override
    public Double getPercentOfPeopleByHairColor(Color hair_color){
        double personsPercent;

        try{
            Pair countResult = countPersonsByHairColor(hair_color);

            if (countResult.getAmount() == 0)
                personsPercent = 0;
            else
                personsPercent = Double.valueOf(countResult.getChosen()) * 100 / Double.valueOf(countResult.getAmount());
        } catch (Exception e) {
            return null;
        }

        return personsPercent;
    }

}
