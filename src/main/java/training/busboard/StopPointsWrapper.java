package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPointsWrapper {

    public List<StopPoints> stopPoints;
}
