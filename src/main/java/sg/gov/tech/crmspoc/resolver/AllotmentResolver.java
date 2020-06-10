package sg.gov.tech.crmspoc.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.gov.tech.crmspoc.datasource.AllotmentService;
import sg.gov.tech.crmspoc.datasource.PersonService;
import sg.gov.tech.crmspoc.datasource.Util;
import sg.gov.tech.crmspoc.value.Allotment;
import sg.gov.tech.crmspoc.value.MhaAddress;
import sg.gov.tech.crmspoc.value.Person;

@Component
public class AllotmentResolver implements GraphQLResolver<Allotment> {

    @Autowired
    private PersonService personService;

    public MhaAddress getAddress(final Allotment allotment) {
        String asOfDate = Util.yearCycleToISODate(allotment.getYear(), allotment.getCycle());
        return personService.getAddress(allotment.getPersonId(), asOfDate);
    }

}
