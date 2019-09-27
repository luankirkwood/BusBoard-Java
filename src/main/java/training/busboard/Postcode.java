package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Postcode {

    public Double longitude;
    public Double latitude;


}