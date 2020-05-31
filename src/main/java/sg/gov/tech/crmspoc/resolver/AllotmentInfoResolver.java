package sg.gov.tech.crmspoc.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.gov.tech.crmspoc.dao.AllotmentDAO;
import sg.gov.tech.crmspoc.dao.AllotmentInfoDAO;
import sg.gov.tech.crmspoc.value.Allotment;
import sg.gov.tech.crmspoc.value.AllotmentInfo;
import sg.gov.tech.crmspoc.value.UEN;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllotmentInfoResolver implements GraphQLQueryResolver {

    @Autowired
    private AllotmentInfoDAO allotmentInfoDAO;

    @Autowired
    private AllotmentDAO allotmentDAO;

    public Iterable<AllotmentInfo> getInfo(Iterable<UEN> uens) {
        List<AllotmentInfo> results = new ArrayList<AllotmentInfo>();

        for (UEN uen : uens) {
            if (uen.getNric() != null) {
                results.add(allotmentInfoDAO.getByNRIC(uen.getNric()));
            } else {
                results.addAll(allotmentInfoDAO.findByNameMobilePostalCode(uen.getName(), uen.getMobile(), uen.getPostalCode()));
            }
        }

        return results;
    }

    public Iterable<Allotment> getAllotments(UEN uen) {
        if (uen.getNric() != null) {
            return allotmentDAO.findByNRIC(uen.getNric());
        } else {
            return allotmentDAO.findByNameMobilePostalCode(uen.getName(), uen.getMobile(), uen.getPostalCode());
        }
    }

}
