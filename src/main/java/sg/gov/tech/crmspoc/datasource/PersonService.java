package sg.gov.tech.crmspoc.datasource;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import sg.gov.tech.crmspoc.datasource.response.FindPersons;
import sg.gov.tech.crmspoc.datasource.response.GetAddress;
import sg.gov.tech.crmspoc.datasource.response.GetPerson;
import sg.gov.tech.crmspoc.value.MhaAddress;
import sg.gov.tech.crmspoc.value.Person;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PersonService {

    @Value("${service.person.url}")
    private String baseUrl;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient
            .builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    public Person getById(String personId) {
        WebClient.ResponseSpec resp = webClient
            .get().uri(uriBuilder -> uriBuilder.path("datasource/persons/{id}")
                .build(personId))
            .retrieve();
        return resp.bodyToMono(GetPerson.class).block().getData();
    }

    public Person getByNRIC(String nric) {
        WebClient.ResponseSpec resp = webClient
            .get()
            .uri(uriBuilder -> uriBuilder.path("datasource/search")
                .queryParam("nric", nric)
                .build())
            .retrieve();
        return resp.bodyToMono(GetPerson.class).block().getData();
    }

    public List<Person> findByName(String name) {

        WebClient.ResponseSpec resp = webClient
            .get()
            .uri(uriBuilder -> uriBuilder.path("datasource/search")
                .queryParam("name", name)
                .build())
            .retrieve();
        return resp.bodyToFlux(FindPersons.class).blockLast().getData();
    }

    public MhaAddress getAddress(String personId, String asOfDate) {
        WebClient.ResponseSpec resp;
        if (asOfDate != null) {
            resp = webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("datasource/search")
                    .path("/datasource/persons/{id}/address")
                    .queryParam("asOf", asOfDate)
                    .build(personId))
                .retrieve();
        } else {
            resp = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                    .path("/datasource/persons/{id}/address")
                    .build(personId))
                .retrieve();
        }
        return resp.bodyToMono(GetAddress.class).block().getData();
    }

}
