package sg.gov.tech.crmspoc.value;

import lombok.NonNull;
import lombok.Value;

@Value
public class AllotmentInfo {
    @NonNull private String nric;
    @NonNull private String name;
    @NonNull private String latestCitizenship;
    private MhaAddress latestAddress;
    private boolean exist;
    private Iterable<Allotment> allotments;
}
