package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.time.format.DateTimeFormatter;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Arrivals {

    public String stationName;
    public String lineId;
    public String destinationName;
    public String expectedArrival;
}
