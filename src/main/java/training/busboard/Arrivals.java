package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.time.LocalDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Arrivals {

    public String stationName;
    public String lineId;
    public String destinationName;
    public int timeToStation;
}
