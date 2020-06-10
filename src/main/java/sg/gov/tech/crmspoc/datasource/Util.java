package sg.gov.tech.crmspoc.datasource;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Log4j2
public class Util {

    private static RestTemplate restTemplate = new RestTemplate();

    static {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(java.util.Collections.singletonList(org.springframework.http.MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
    }

    /**
     * Validates Singapore NRIC / FIN in 2 stages:
     * 1) Ensure first letter starts with S, T, F or G and the last letter is A-Z
     * 2) Calculate weight of digits in between first and last character of the input string,
     * determine what the last letter should be, then match it against the last character of the
     * input string.
     *
     * @param inputString NRIC / FIN string to be validated
     * @return true if NRIC/FIN passes, false otherwise
     */
    public static boolean validateNric(String inputString) {
        String nricToTest = inputString.toUpperCase();

        // first letter must start with S, T, F or G. Last letter must be A - Z
        if (!Pattern.compile("^[STFG]\\d{7}[A-Z]$").matcher(nricToTest).matches()) {
            return false;
        } else {
            char[] icArray = new char[9];
            char[] st = "JZIHGFEDCBA".toCharArray();
            char[] g = "XWUTRQPNMLK".toCharArray();

            for (int i = 0; i < 9; i++) {
                icArray[i] = nricToTest.charAt(i);
            }

            // calculate weight of positions 1 to 7
            int weight = (Integer.parseInt(String.valueOf(icArray[1]), 10)) * 2 +
                (Integer.parseInt(String.valueOf(icArray[2]), 10)) * 7 +
                (Integer.parseInt(String.valueOf(icArray[3]), 10)) * 6 +
                (Integer.parseInt(String.valueOf(icArray[4]), 10)) * 5 +
                (Integer.parseInt(String.valueOf(icArray[5]), 10)) * 4 +
                (Integer.parseInt(String.valueOf(icArray[6]), 10)) * 3 +
                (Integer.parseInt(String.valueOf(icArray[7]), 10)) * 2;

            int offset = icArray[0] == 'T' || icArray[0] == 'G' ? 4 : 0;

            int lastCharPosition = (offset + weight) % 11;

            if (icArray[0] == 'S' || icArray[0] == 'T') {
                return icArray[8] == st[lastCharPosition];
            } else if (icArray[0] == 'F' || icArray[0] == 'G') {
                return icArray[8] == st[lastCharPosition];
            } else {
                return false; // this line should never reached due to regex above
            }
        }
    }


    public static <T> T makeGet(String baseUri, String path, Class<T> outClass) {
        String getUri = String.format("%s/%s", baseUri, path);
        try {
            T result = restTemplate.getForObject(getUri, outClass);
            return result;
        } catch (HttpClientErrorException httpE) {
            String message = String.format("HTTP error %s: %s", getUri, httpE.getMessage());
            log.error(message, httpE);
            throw new DatasourceError(message, httpE.getStatusCode().toString());
        } catch (RestClientException restE) {
            String message = String.format("REST error %s: %s", getUri, restE.getMessage());
            log.error(message, restE);
            throw new DatasourceError(message, restE.getLocalizedMessage());
        }
    }


}
