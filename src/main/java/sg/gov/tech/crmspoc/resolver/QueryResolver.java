package sg.gov.tech.crmspoc.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.gov.tech.crmspoc.datasource.AllotmentInfoService;
import sg.gov.tech.crmspoc.value.AllotmentInfo;
import sg.gov.tech.crmspoc.value.UEN;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private AllotmentInfoService allotmentInfoService;

    public Iterable<AllotmentInfo> getInfo(Iterable<UEN> uens) {
        List<AllotmentInfo> results = new ArrayList<AllotmentInfo>();

        for (UEN uen : uens) {
            if (uen.getNric() != null) {
                results.add(allotmentInfoService.getByNRIC(uen.getNric()));
            } else {
                results.addAll(allotmentInfoService.findByNameMobilePostalCode(uen.getName(), uen.getMobile(), uen.getPostalCode()));
            }
        }

        return results;
    }

}
