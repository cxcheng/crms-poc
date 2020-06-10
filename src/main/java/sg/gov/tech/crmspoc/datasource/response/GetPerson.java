package sg.gov.tech.crmspoc.datasource.response;

import lombok.Data;
import lombok.NonNull;
import sg.gov.tech.crmspoc.value.Person;

@Data
@lombok.NoArgsConstructor
public class GetPerson {
    @NonNull
    private Person data;
}

