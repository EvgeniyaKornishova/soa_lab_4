package ru.ifmo.soa.lab4.primary_back.endpoints;

import org.apache.tomcat.util.log.SystemLogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.ifmo.soa.lab4.primary_back.XMLUtils.XMLConverter;
import ru.ifmo.soa.lab4.primary_back.data.PersonExtensions.Paginator;
import ru.ifmo.soa.lab4.primary_back.data.PersonExtensions.PersonSpecificationBuilder;
import ru.ifmo.soa.lab4.primary_back.data.SOAPException;
import ru.ifmo.soa.lab4.primary_back.data.gen.*;
import ru.ifmo.soa.lab4.primary_back.data.validators.PaginatorValidator;
import ru.ifmo.soa.lab4.primary_back.data.validators.PersonValidator;
import ru.ifmo.soa.lab4.primary_back.data.validators.SorterValidator;
import ru.ifmo.soa.lab4.primary_back.entities.DBLocation;
import ru.ifmo.soa.lab4.primary_back.entities.DBPerson;
import ru.ifmo.soa.lab4.primary_back.repositories.LocationRepository;
import ru.ifmo.soa.lab4.primary_back.repositories.PersonRepository;


import javax.xml.ws.http.HTTPException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Endpoint
public class PersonEndpoint {
    private static final String NAMESPACE_URI = "http://www.ifmo.ru/soa/lab4/primary_back/data/gen";

    private final PersonValidator personValidator;
    private final PaginatorValidator paginatorValidator;
    private final SorterValidator sorterValidator;
    private final XMLConverter converter;

    @Autowired
    PersonRepository personRepository;
    @Autowired
    LocationRepository locationRepository;

    public PersonEndpoint(){
        personValidator = new PersonValidator();
        paginatorValidator = new PaginatorValidator();
        sorterValidator = new SorterValidator();
        converter = new XMLConverter();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonListRequest")
    @ResponsePayload
    public GetPersonListResponse getPersonList(
            @RequestPayload GetPersonListRequest request
            ) throws SOAPException {
        boolean sorting = false;
        Optional<String> sort = (request.getSort() == null) ? Optional.empty() : Optional.of(request.getSort());
        // pagination
        Integer pageId = (request.getPageId() == null) ? 1 : request.getPageId().intValue();
        Optional<Integer> pageSize = (request.getPageSize() == null) ? Optional.empty() : Optional.of(request.getPageSize().intValue());
        // filters
        Optional<String> name = (request.getName() == null) ? Optional.empty() : Optional.of(request.getName());
        Optional<Float> height = (request.getHeight() == null) ? Optional.empty() : Optional.of(request.getHeight());
        Optional<Color> eyeColor = (request.getEyeColor() == null) ? Optional.empty() : Optional.of(request.getEyeColor());
        Optional<Color> hairColor = (request.getHairColor() == null) ? Optional.empty() : Optional.of(request.getHairColor());
        Optional<Country> nationality = (request.getNationality() == null) ? Optional.empty() : Optional.of(request.getNationality());
        Optional<Integer> coordinatesX = (request.getCoordinatesX() == null) ? Optional.empty() : Optional.of(request.getCoordinatesX().intValue());
        Optional<Double> coordinatesY = (request.getCoordinatesY() == null) ? Optional.empty() : Optional.of(request.getCoordinatesY());
        Optional<Integer> locationX = (request.getLocationX() == null) ? Optional.empty() : Optional.of(request.getLocationX().intValue());
        Optional<Float> locationY = (request.getLocationY() == null) ? Optional.empty() : Optional.of(request.getLocationY());
        Optional<Float> locationZ = (request.getLocationZ() == null) ? Optional.empty() : Optional.of(request.getLocationZ());
        Optional<String> sCreationDate = (request.getCreationDate() == null) ? Optional.empty() : Optional.of(request.getCreationDate());



        if (pageSize.isEmpty()) {
            pageSize = Optional.of((int) personRepository.count());
            if (pageSize.get() == 0){
                GetPersonListResponse response = new GetPersonListResponse();
                GetPersonListResponse.Persons persons = new GetPersonListResponse.Persons();
                persons.getPerson(); // to set it to zero length array
                persons.setCount(0L);
                response.setPersons(persons);
                return response;
            }
        }

        Paginator paginator = new Paginator(pageId, pageSize.get());

        List<String> errors = paginatorValidator.validate(paginator);
        if (!errors.isEmpty())
            throw new SOAPException(400, converter.listToStr(errors, "errors", new String[0]));

        if (sort.isPresent()) {
            errors = sorterValidator.validate(sort.get());
            if (!errors.isEmpty())
                throw new SOAPException(400, converter.listToStr(errors, "errors", new String[0]));
            sorting = true;
            sort = Optional.of(sorterValidator.format(sort.get()));
        }

        Pageable sortAndPaging;
        if (sorting){
            sortAndPaging = PageRequest.of(paginator.getPageId()-1, paginator.getPageSize(), Sort.by(sort.get()));
        }else{
            sortAndPaging = PageRequest.of(paginator.getPageId()-1, paginator.getPageSize());
        }

        Optional<LocalDateTime> creationDate = Optional.empty();
        if (sCreationDate.isPresent())
            try {
                creationDate = Optional.of(LocalDateTime.parse(sCreationDate.get()));
            }catch (DateTimeParseException e) {
                throw new SOAPException(400, "Invalid creation_date format");
            }

        PersonSpecificationBuilder builder = new PersonSpecificationBuilder();

        name.ifPresent(x -> builder.with("name", "=", x));
        height.ifPresent(x -> builder.with("height", "=", x));
        eyeColor.ifPresent(x -> builder.with("eyeColor", "=", x));
        hairColor.ifPresent(x -> builder.with("hairColor", "=", x));
        nationality.ifPresent(x -> builder.with("nationality", "=", x));
        creationDate.ifPresent(x -> builder.with("creationDate", "=", x));
        coordinatesX.ifPresent(x -> builder.with("coordinates.x", "=", x));
        coordinatesY.ifPresent(x -> builder.with("coordinates.y", "=", x));
        locationX.ifPresent(x -> builder.with("location.x", "=", x));
        locationY.ifPresent(x -> builder.with("location.y", "=", x));
        locationZ.ifPresent(x -> builder.with("location.z", "=", x));

        Specification<DBPerson> spec = builder.build();

        Page<DBPerson> dbPersonList = personRepository.findAll(spec, sortAndPaging);
        long count = personRepository.count();

        GetPersonListResponse response = new GetPersonListResponse();
        GetPersonListResponse.Persons persons = new GetPersonListResponse.Persons();
        persons.getPerson().addAll(dbPersonList.getContent().stream().map(DBPerson::toPerson).collect(Collectors.toList()));
        persons.setCount(count);
        response.setPersons(persons);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
    @ResponsePayload
    public GetPersonResponse getPerson(
        @RequestPayload GetPersonRequest request
    )throws SOAPException{
        Long id = request.getPersonId();

        Optional<DBPerson> opDbPerson = personRepository.findById(id);
        if (opDbPerson.isEmpty())
            throw new SOAPException(404, "Person with specified id not found");

        DBPerson dbPerson = opDbPerson.get();

        GetPersonResponse response = new GetPersonResponse();
        response.setPerson(dbPerson.toPerson());
        return response;
    }

    private boolean compareLocations(DBLocation left, DBLocation right){
        return (Float.compare(left.getY(), right.getY()) == 0 && Float.compare(left.getZ(), right.getZ()) == 0 && Objects.equals(left.getX(), right.getX()));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUniqLocationsRequest")
    @ResponsePayload
    public GetUniqLocationsResponse getUniqLocations(){
        List<DBLocation> locations = locationRepository.findAll();

        List<DBLocation> uniq_locations = new java.util.ArrayList<>();

        for (DBLocation loc : locations) {
            boolean flag = true;
            for (DBLocation uniq_loc : uniq_locations) {
                if (compareLocations(loc, uniq_loc))
                    flag = false;
            }
            if (flag)
                uniq_locations.add(loc);
        }

        GetUniqLocationsResponse response = new GetUniqLocationsResponse();
        GetUniqLocationsResponse.Locations locs = new GetUniqLocationsResponse.Locations();
        locs.getLocation().addAll(uniq_locations.stream().map(DBLocation::toLocation).collect(Collectors.toList()));
        response.setLocations(locs);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHeightsSumRequest")
    @ResponsePayload
    public GetHeightsSumResponse getHeightsSum() {
        Float sumHeight = personRepository.calcSumHeight();
        if (sumHeight == null)
            sumHeight = (float) 0;

        GetHeightsSumResponse response = new GetHeightsSumResponse();
        response.setServerResponse(sumHeight);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonByNameRequest")
    @ResponsePayload
    public GetPersonByNameResponse getPersonByName(
            @RequestPayload GetPersonByNameRequest request
    ){
        String name = request.getName();
        System.out.println("Name:");
        System.out.println(name);

        List<DBPerson> dbPersonList = personRepository.findByNameContains(name);

        GetPersonByNameResponse response = new GetPersonByNameResponse();
        GetPersonByNameResponse.Persons persons = new GetPersonByNameResponse.Persons();
        persons.getPerson().addAll(dbPersonList.stream().map(DBPerson::toPerson).collect(Collectors.toList()));
        response.setPersons(persons);
        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPersonRequest")
    @ResponsePayload
    public CreatePersonResponse createPerson(
            @RequestPayload CreatePersonRequest request
    ) throws SOAPException {
        System.out.println("Person:");
        System.out.println(request.getPerson());

        InPerson person = request.getPerson();
        List<String> errors = new ArrayList<>();
        try{
            errors.addAll(personValidator.validate(person));
        }catch (IllegalAccessException e){
            throw new SOAPException(400, "Failed to validate person parameters");
        }

        if (!errors.isEmpty())
            throw new SOAPException(400, converter.listToStr(errors, "errors", new String[0]));

        DBPerson newDBPerson = DBPerson.fromInPerson(person);
        personRepository.save(newDBPerson);
        long id = newDBPerson.getId();

        CreatePersonResponse response = new CreatePersonResponse();
        response.setServerResponse(id);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonRequest")
    @ResponsePayload
    public UpdatePersonResponse updatePerson(
        @RequestPayload UpdatePersonRequest request
    ) throws SOAPException {
        Long id = request.getPersonId();
        InPerson person = request.getPerson();
        System.out.println("Location: ");
        System.out.println(person.getLocation().getX());
        System.out.println(person.getLocation().getY());
        System.out.println(person.getLocation().getZ());

        List<String> errors = null;
        try{
            errors = personValidator.validate(person);
        }catch (IllegalAccessException e) {
            throw new SOAPException(400, "Failed to validate person parameters");
        }

        if (!errors.isEmpty())
            throw new SOAPException(400, converter.listToStr(errors, "errors", new String[0]));

        Optional<DBPerson> opDbPerson = personRepository.findById(id);

        if (opDbPerson.isEmpty())
            throw new SOAPException(404, "Person with specified id not found");

        DBPerson dbPerson = opDbPerson.get();
        System.out.println("Location: ");
        System.out.println(dbPerson.getLocation().getX());
        System.out.println(dbPerson.getLocation().getY());
        System.out.println(dbPerson.getLocation().getZ());

        dbPerson.update(person);
        personRepository.save(dbPerson);

        System.out.println("Location: ");
        System.out.println(dbPerson.getLocation().getX());
        System.out.println(dbPerson.getLocation().getY());
        System.out.println(dbPerson.getLocation().getZ());

        UpdatePersonResponse response = new UpdatePersonResponse();
        response.setStatus("OK");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    @ResponsePayload
    public DeletePersonResponse deletePerson(
            @RequestPayload DeletePersonRequest request
    ) throws SOAPException {
        Long id = request.getPersonId();

        Optional<DBPerson> person = personRepository.findById(id);

        if (person.isEmpty())
            throw new SOAPException(404, "Person with specified id not found");

        personRepository.delete(person.get());

        DeletePersonResponse response = new DeletePersonResponse();
        response.setStatus("OK");
        return response;
    }
}
