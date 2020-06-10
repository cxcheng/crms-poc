package sg.gov.tech.crmspoc.datasource;

import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import sg.gov.tech.crmspoc.datasource.response.FindAllotments;
import sg.gov.tech.crmspoc.value.Allotment;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AllotmentService {

    @Value("${service.allotment.url}")
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

    public List<Allotment> findAllotments(String personId) {
        WebClient.ResponseSpec resp = webClient
            .get()
            .uri(uriBuilder -> uriBuilder
                .path("/allotment/persons/{id}/allotment_results")
                .build(personId))
            .retrieve();
        return resp.bodyToFlux(FindAllotments.class).blockLast().getData();
    }
}
