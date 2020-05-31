package sg.gov.tech.crmspoc.dao;

import org.springframework.stereotype.Component;
import sg.gov.tech.crmspoc.value.Allotment;
import sg.gov.tech.crmspoc.value.MhaAddress;
import sg.gov.tech.crmspoc.value.MhaAddressTypeEnum;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllotmentDAO {
    public List<Allotment> findByNRIC(String nric) {
        // return dummy
        List<Allotment> results = new ArrayList<Allotment>();

        Allotment dummyAllotment = new Allotment("scheme " + nric,
            "subScheme " + nric,
            2021,
            "cycle 2",
            1234.56,
            new MhaAddress(MhaAddressTypeEnum.fromString("A"),
                "123",
                "Jalan Jalan",
                "999",
                "9999",
                "Babylon",
                "123456"));


        results.add(dummyAllotment);
        return results;
    }

    public List<Allotment> findByNameMobilePostalCode(String name, String mobile, String postalCode) {
        return new ArrayList<Allotment>();
    }
}
