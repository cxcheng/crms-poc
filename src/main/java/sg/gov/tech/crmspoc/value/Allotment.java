package sg.gov.tech.crmspoc.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class Allotment {

    @NonNull private String scheme;
    private String subScheme;
    private int year;
    private String cycle;
    private Double amount;
    private MhaAddress address;

}
