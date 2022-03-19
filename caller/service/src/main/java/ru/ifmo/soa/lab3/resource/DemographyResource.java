package ru.ifmo.soa.lab3.resource;

import ru.ifmo.soa.lab3.ServerResponse;
import ru.ifmo.soa.lab3.XMLUtils.XMLConverter;
import ru.ifmo.soa.lab3.data.Color;
import ru.ifmo.soa.lab3.service.DemographyInfoService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/demography")
public class DemographyResource {
    private final XMLConverter converter;
    private DemographyInfoService demographyInfoService;

    public DemographyResource() {
        converter = new XMLConverter();
        demographyInfoService = new DemographyInfoService();

    }

    @GET
    @Path("/hair-color/{hair_color}")
    @Produces({MediaType.APPLICATION_XML})
    public Response getAmountOfPeopleByHairColor(@PathParam("hair_color") Color hair_color){
        System.out.println("GET request - getAmountOfPeopleByHairColor");
        Long personsCount = demographyInfoService.getAmountOfPeopleByHairColor(hair_color);

        if (personsCount == null)
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();

        return Response.ok().entity(converter.toStr(new ServerResponse<>(personsCount))).build();
    }


    @GET
    @Path("/hair-color/{hair_color}/percentage")
    @Produces({MediaType.APPLICATION_XML})
    public Response getPercentOfPeopleByHairColor(@PathParam("hair_color") Color hair_color){
        System.out.println("GET request - getPercentOfPeopleByHairColor");
        Double personsPercent = demographyInfoService.getPercentOfPeopleByHairColor(hair_color);

        if (personsPercent == null)
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();

        return Response.ok().entity(converter.toStr(new ServerResponse<>(personsPercent))).build();
    }

}
