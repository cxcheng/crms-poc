package sg.gov.tech.crmspoc.datasource.response;

import lombok.Data;
import lombok.NonNull;
import sg.gov.tech.crmspoc.value.MhaAddress;

@Data
@lombok.NoArgsConstructor
public class GetAddress {
    @NonNull
    private MhaAddress data;
}
