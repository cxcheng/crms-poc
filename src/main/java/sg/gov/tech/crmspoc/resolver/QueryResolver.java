package sg.gov.tech.crmspoc.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.gov.tech.crmspoc.datasource.PersonService;
import sg.gov.tech.crmspoc.value.Person;
import sg.gov.tech.crmspoc.value.UEN;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private PersonService personService;

    public Iterable<Person> getInfo(Iterable<UEN> uens) {
        List<Person> results = new ArrayList<Person>();

        for (UEN uen : uens) {
            if (uen.getNric() != null) {
                results.add(personService.getByNRIC(uen.getNric()));
            } else if (uen.getName() != null) {
                results.addAll(personService.findByName(uen.getName()));
            }
        }

        return results;
    }

}
