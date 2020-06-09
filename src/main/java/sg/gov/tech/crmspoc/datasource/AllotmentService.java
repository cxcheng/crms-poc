package sg.gov.tech.crmspoc.datasource;

import org.springframework.stereotype.Service;
import sg.gov.tech.crmspoc.value.Allotment;
import sg.gov.tech.crmspoc.value.MhaAddress;
import sg.gov.tech.crmspoc.value.MhaAddressTypeEnum;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllotmentService {
    public List<Allotment> findAllotments(String nric) {
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
}
