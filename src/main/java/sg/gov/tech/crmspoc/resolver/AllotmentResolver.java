package sg.gov.tech.crmspoc.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.gov.tech.crmspoc.datasource.AllotmentService;
import sg.gov.tech.crmspoc.value.Allotment;
import sg.gov.tech.crmspoc.value.AllotmentInfo;

@Component
public class AllotmentResolver implements GraphQLResolver<Allotment> {

    @Autowired
    private AllotmentService allotmentService;

    public Iterable<Allotment> getAllotments(final AllotmentInfo allotmentInfo) {
        return allotmentService.findAllotments(allotmentInfo.getNric());
    }

}
