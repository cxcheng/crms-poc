package sg.gov.tech.crmspoc.datasource.response;

import lombok.Data;
import lombok.NonNull;
import sg.gov.tech.crmspoc.value.Allotment;

import java.util.List;

@Data
@lombok.NoArgsConstructor
public class FindAllotments {
    @NonNull
    private List<Allotment> data;
}


