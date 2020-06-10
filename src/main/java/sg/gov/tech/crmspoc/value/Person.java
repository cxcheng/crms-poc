package sg.gov.tech.crmspoc.value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    @NonNull
    private String id;
    @NonNull
    private String nric;
    @NonNull
    private String name;
    @NonNull
    private String nationalityType;
}
