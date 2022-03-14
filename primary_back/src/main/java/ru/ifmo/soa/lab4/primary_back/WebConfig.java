package ru.ifmo.soa.lab4.primary_back;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/*");
    }

    @Bean(name = "createPerson")
    public DefaultWsdl11Definition createPersonWsdl11Definition(XsdSchema createPersonSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CreatePersonPort");
        wsdl11Definition.setLocationUri("/persons/create");
        wsdl11Definition.setTargetNamespace("http://www.ifmo.ru/soa/lab4/primary_back/data/gen");
        wsdl11Definition.setSchema(createPersonSchema);
        return wsdl11Definition;
    }

    @Bean(name = "updatePerson")
    public DefaultWsdl11Definition updatePersonWsdl11Definition(XsdSchema updatePersonSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UpdatePersonPort");
        wsdl11Definition.setLocationUri("/persons/update");
        wsdl11Definition.setTargetNamespace("http://www.ifmo.ru/soa/lab4/primary_back/data/gen");
        wsdl11Definition.setSchema(updatePersonSchema);
        return wsdl11Definition;
    }

    @Bean(name = "deletePerson")
    public DefaultWsdl11Definition deletePersonWsdl11Definition(XsdSchema deletePersonSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("DeletePersonPort");
        wsdl11Definition.setLocationUri("/persons/delete");
        wsdl11Definition.setTargetNamespace("http://www.ifmo.ru/soa/lab4/primary_back/data/gen");
        wsdl11Definition.setSchema(deletePersonSchema);
        return wsdl11Definition;
    }

    @Bean(name = "getPerson")
    public DefaultWsdl11Definition getPersonWsdl11Definition(XsdSchema getPersonSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GetPersonPort");
        wsdl11Definition.setLocationUri("/persons/get");
        wsdl11Definition.setTargetNamespace("http://www.ifmo.ru/soa/lab4/primary_back/data/gen");
        wsdl11Definition.setSchema(getPersonSchema);
        return wsdl11Definition;
    }

    @Bean(name = "listPerson")
    public DefaultWsdl11Definition listPersonWsdl11Definition(XsdSchema listPersonSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ListPersonPort");
        wsdl11Definition.setLocationUri("/persons/list");
        wsdl11Definition.setTargetNamespace("http://www.ifmo.ru/soa/lab4/primary_back/data/gen");
        wsdl11Definition.setSchema(listPersonSchema);
        return wsdl11Definition;
    }

    @Bean(name = "findPerson")
    public DefaultWsdl11Definition findPersonWsdl11Definition(XsdSchema findPersonSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("FindPersonPort");
        wsdl11Definition.setLocationUri("/persons/find");
        wsdl11Definition.setTargetNamespace("http://www.ifmo.ru/soa/lab4/primary_back/data/gen");
        wsdl11Definition.setSchema(findPersonSchema);
        return wsdl11Definition;
    }

    @Bean(name = "getUniqLocations")
    public DefaultWsdl11Definition getUniqLocationsWsdl11Definition(XsdSchema getUniqLocationsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GetUniqLocationsPort");
        wsdl11Definition.setLocationUri("/persons/uniq_locations");
        wsdl11Definition.setTargetNamespace("http://www.ifmo.ru/soa/lab4/primary_back/data/gen");
        wsdl11Definition.setSchema(getUniqLocationsSchema);
        return wsdl11Definition;
    }

    @Bean(name = "getHeightSum")
    public DefaultWsdl11Definition getHeightSumWsdl11Definition(XsdSchema getHeightSumSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("GetHeightSumPort");
        wsdl11Definition.setLocationUri("/persons/height_sum");
        wsdl11Definition.setTargetNamespace("http://www.ifmo.ru/soa/lab4/primary_back/data/gen");
        wsdl11Definition.setSchema(getHeightSumSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema createPersonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/createPerson.xsd"));
    }
    @Bean
    public XsdSchema updatePersonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/updatePerson.xsd"));
    }
    @Bean
    public XsdSchema deletePersonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/deletePerson.xsd"));
    }
    @Bean
    public XsdSchema getPersonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/getPerson.xsd"));
    }
    @Bean
    public XsdSchema listPersonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/listPerson.xsd"));
    }
    @Bean
    public XsdSchema findPersonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/findPerson.xsd"));
    }
    @Bean
    public XsdSchema getHeightSumSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/getHeightSum.xsd"));
    }
    @Bean
    public XsdSchema getUniqLocationsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/getUniqLocations.xsd"));
    }
    @Bean
    public XsdSchema types(){
        return new SimpleXsdSchema(new ClassPathResource("schemas/types.xsd"));
    }
}
