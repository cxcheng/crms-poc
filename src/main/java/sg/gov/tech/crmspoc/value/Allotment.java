package sg.gov.tech.crmspoc.value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Allotment {

    @NonNull
    private String id;
    @NonNull
    private String personId;
    @NonNull
    private String scheme;
    private String subScheme;
    private int year;
    private String cycle;
    private Double amount;

}
