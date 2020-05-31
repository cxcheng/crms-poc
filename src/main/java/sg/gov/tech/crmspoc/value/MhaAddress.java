package sg.gov.tech.crmspoc.value;

import lombok.Value;

@Value
public class MhaAddress {

    private MhaAddressTypeEnum type;
    private String blockNo;
    private String streetName;
    private String floorNo;
    private String unitNo;
    private String buildingName;
    private String postalCode;

}
