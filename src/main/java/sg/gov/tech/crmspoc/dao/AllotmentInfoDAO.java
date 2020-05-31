package sg.gov.tech.crmspoc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.gov.tech.crmspoc.value.AllotmentInfo;
import sg.gov.tech.crmspoc.value.MhaAddress;
import sg.gov.tech.crmspoc.value.MhaAddressTypeEnum;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllotmentInfoDAO {

    @Autowired
    private AllotmentDAO allotmentDAO;

    public AllotmentInfo getByNRIC(String nric) {
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
            allotmentDAO.findByNRIC(nric));
        return dummyInfo;
    }

    public List<AllotmentInfo> findByNameMobilePostalCode(String name, String mobile, String postalCode) {
        return new ArrayList<AllotmentInfo>();
    }
}
