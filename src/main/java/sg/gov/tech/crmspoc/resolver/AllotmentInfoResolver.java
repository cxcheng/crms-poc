package sg.gov.tech.crmspoc.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.gov.tech.crmspoc.datasource.AllotmentInfoService;
import sg.gov.tech.crmspoc.datasource.AllotmentService;
import sg.gov.tech.crmspoc.value.Allotment;
import sg.gov.tech.crmspoc.value.AllotmentInfo;
import sg.gov.tech.crmspoc.value.MhaAddress;

@Component
public class AllotmentInfoResolver implements GraphQLResolver<AllotmentInfo> {

    @Autowired
    private AllotmentInfoService allotmentInfoService;

    @Autowired
    private AllotmentService allotmentService;

    public MhaAddress getLatestAddress(final AllotmentInfo allotmentInfo) {
        return allotmentInfoService.getAddress(allotmentInfo.getNric(), null);
    }

    public Iterable<Allotment> getAllotments(final AllotmentInfo allotmentInfo) {
        return allotmentService.findAllotments(allotmentInfo.getNric());
    }

}
