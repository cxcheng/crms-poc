package sg.gov.tech.crmspoc.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.gov.tech.crmspoc.datasource.AllotmentService;
import sg.gov.tech.crmspoc.datasource.PersonService;
import sg.gov.tech.crmspoc.value.Allotment;
import sg.gov.tech.crmspoc.value.MhaAddress;
import sg.gov.tech.crmspoc.value.Person;

@Component
public class PersonResolver implements GraphQLResolver<Person> {

    @Autowired
    private PersonService personService;

    @Autowired
    private AllotmentService allotmentService;

    public MhaAddress getAddress(final Person person) {
        return personService.getAddress(person.getId(), null);
    }

    public Iterable<Allotment> getAllotments(final Person person) {
        return allotmentService.findAllotments(person.getId());
    }

}
