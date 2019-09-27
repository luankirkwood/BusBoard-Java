package training.busboard;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostcodeWrapper{
    public Postcode result;
}
