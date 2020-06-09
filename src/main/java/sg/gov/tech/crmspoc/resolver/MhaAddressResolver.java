package sg.gov.tech.crmspoc.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.gov.tech.crmspoc.datasource.AllotmentInfoService;
import sg.gov.tech.crmspoc.value.MhaAddress;

@Component
public class MhaAddressResolver implements GraphQLResolver<MhaAddress> {

    @Autowired
    private AllotmentInfoService allotmentInfoService;

    public MhaAddress getAddress(String nric, String asOfDate) {
        return allotmentInfoService.getAddress(nric, asOfDate);
    }
}
