package sg.gov.tech.crmspoc.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum MhaAddressTypeEnum {
    // revised_file_layout_MHA_v3.2_post_agency_discussion.xlsx
    // space : due to old migrated records
    APARTMENT_BLK("A"),
    WITHOUT_APARTMENT_BLK("B"),
    REVERSE_OF_APARTMENT_BLK_STREET_NAME("X"),
    OVERSEAS_ADDRESS("C"),
    PRIVATE_FLATS_WITH_APARTMENT_BLK("D"),
    CO_APARTMENT_BLK("E"),
    CO_WITHOUT_APARTMENT_BLK("F"),
    QUARTER_ADDRESS("Q"),
    ISLAND_ADDRESS("I"),
    SPACE("");

    @Getter
    private String value;

    // returns the relevant enum for a given string value
    public static MhaAddressTypeEnum fromString(String str) {
        for (MhaAddressTypeEnum enumVal : MhaAddressTypeEnum.values()) {
            if (enumVal.getValue().equals(str)) {
                return enumVal;
            }
        }
        return null;
    }

}



