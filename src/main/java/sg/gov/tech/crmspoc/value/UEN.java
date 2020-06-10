package sg.gov.tech.crmspoc.value;

import lombok.Value;

@Value
public class UEN {

    private String nric;
    private String name;
    private String mobile;

    public UEN(String nric, String name, String mobile, String postalCode) {
        if (nric != null) {
            if (name != null || mobile != null) {
                throw new IllegalArgumentException("if NRIC is populated, the other entries must be empty");
            }
        } else if (name == null && mobile == null) {
            throw new IllegalArgumentException("At least NRIC, name, mobile must not be empty");
        }
        this.nric = nric;
        this.name = name;
        this.mobile = mobile;
    }

}
