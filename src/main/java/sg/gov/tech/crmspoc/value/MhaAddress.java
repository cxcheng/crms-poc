package sg.gov.tech.crmspoc.value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MhaAddress {

    private MhaAddressTypeEnum type;
    private String blockNumber;
    private String streetCode;
    private String streetName;
    private String floor;
    private String unit;
    private String buildingName;
    private String postalCode;
    private String newPostalCode;

}
