package sg.gov.tech.crmspoc.datasource.response;

import lombok.Data;
import lombok.NonNull;
import sg.gov.tech.crmspoc.value.Person;

import java.util.List;

@Data
@lombok.NoArgsConstructor
public class FindPersons {
    @NonNull
    private List<Person> data;
}


