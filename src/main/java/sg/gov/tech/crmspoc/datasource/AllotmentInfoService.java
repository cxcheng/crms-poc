package sg.gov.tech.crmspoc.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.gov.tech.crmspoc.value.AllotmentInfo;
import sg.gov.tech.crmspoc.value.MhaAddress;
import sg.gov.tech.crmspoc.value.MhaAddressTypeEnum;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllotmentInfoService {

    @Autowired
    private AllotmentService allotmentService;

    public AllotmentInfo getByNRIC(String nric) {
        /*
        AllotmentInfo dummyInfo = new AllotmentInfo(nric,
            "name " + nric,
            "SG",
            new MhaAddress(MhaAddressTypeEnum.fromString("A"),
                "123",
                "Jalan Jalan",
                "999",
                "9999",
                "Babylon",
                "123456"),
            true,
            null);
         */
        AllotmentInfo dummyInfo = new AllotmentInfo(nric,
            "name " + nric,
            "SG",
            true);
        return dummyInfo;
    }

    public List<AllotmentInfo> findByNameMobilePostalCode(String name, String mobile, String postalCode) {
        return new ArrayList<AllotmentInfo>();
    }

    public MhaAddress getAddress(String nric, String asOfDate) {
        MhaAddress dummyAddress = new MhaAddress(MhaAddressTypeEnum.fromString("A"),
            "123",
            "Jalan Jalan",
            "999",
            "9999",
            "Babylon",
            nric);
        return dummyAddress;
    }

}
