package sg.gov.tech.crmspoc.datasource;

import lombok.Data;

@Data
public class DatasourceError extends RuntimeException {

    private String httpStatusCode;

    public DatasourceError(String message, String httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

}
