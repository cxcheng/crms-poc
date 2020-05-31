package sg.gov.tech.crmspoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sg.gov.tech.crmspoc.resolver.AllotmentInfoResolver;

@SpringBootApplication
public class CrmsPocApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrmsPocApplication.class, args);
  }

}
