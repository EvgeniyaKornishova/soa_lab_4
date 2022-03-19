package ru.ifmo.soa.lab3.service;

import ru.ifmo.soa.lab3.DemographyInfo;
import ru.ifmo.soa.lab3.data.Color;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class DemographyInfoService{
    private DemographyInfo demographyInfo = lookupRemoteStatelessBean();

    public Long getAmountOfPeopleByHairColor(Color hair_color) {
        return demographyInfo.getAmountOfPeopleByHairColor(hair_color);
    }

    public Double getPercentOfPeopleByHairColor(Color hair_color) {
        return demographyInfo.getPercentOfPeopleByHairColor(hair_color);
    }

    private static DemographyInfo lookupRemoteStatelessBean() {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(javax.naming.Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            final javax.naming.Context context = new InitialContext(jndiProperties);
            final String appName = "global";
            final String moduleName = "ejb";
            final String beanName = "DemographyInfo";
            final String viewClassName = DemographyInfo.class.getName();
            System.out.println("java:" + appName + "/" + moduleName + "/" +  beanName + "!" + viewClassName);
            return (DemographyInfo) context.lookup("java:" + appName + "/" + moduleName + "/" +  beanName + "!" + viewClassName);
        } catch (NamingException e) {
            System.out.println("EJB doesn't work");
            return new DemographyInfo() {
                @Override
                public Long getAmountOfPeopleByHairColor(Color hair_color) {
                    throw new EJBException("EJB is not available");
                }

                @Override
                public Double getPercentOfPeopleByHairColor(Color hair_color) {
                    throw new EJBException("EJB is not available");
                }
            };
        }
    }

}