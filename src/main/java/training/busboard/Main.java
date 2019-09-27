package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws KeyManagementException, NoSuchAlgorithmException {

        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "9090");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "9090");

        SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
            }

            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, new java.security.SecureRandom());

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).sslContext(sslContext).hostnameVerifier((s1, s2) -> true).build();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your Postcode: ");
        String postcode = scanner.nextLine();

        PostcodeWrapper Postcodes = getPostcodes(client, postcode);
        StopPointsWrapper stopPoints = getStopPoints(client, Postcodes);

        for (int i = 0; i < stopPoints.stopPoints.size(); i++) {
            List<Arrivals> arrivalsList = getStops(client, i, stopPoints);
            printBusTimes(arrivalsList);
        }


        }


    private static PostcodeWrapper getPostcodes(Client client, String postCode) {
        PostcodeWrapper postcodes = client.target("https://api.postcodes.io/postcodes/" + postCode)
                .request("text/json")
                .get(PostcodeWrapper.class);

        return postcodes;
    }


    public static StopPointsWrapper getStopPoints(Client client, PostcodeWrapper postcodes) {
        StopPointsWrapper stopPoints = client.target("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&lat=" + postcodes.result.latitude + "&lon=" + postcodes.result.longitude)
                .request("text/json")
                .get(StopPointsWrapper.class);

        return stopPoints;
    }

    public static List<Arrivals> getStops(Client client,int i, StopPointsWrapper stopPoints) {
        List<Arrivals> arrivalsList = client.target("https://api.tfl.gov.uk/StopPoint/" + stopPoints.stopPoints.get(i).naptanId + "/Arrivals")
                .request("text/json")
                .get(new GenericType<List<Arrivals>>() {
                });

        return arrivalsList;

    }

    public static void printBusTimes(List<Arrivals> arrivalsList) {
        System.out.println("");
        System.out.println(arrivalsList.get(0).stationName);

        for (int j = 0; j < (Math.min(arrivalsList.get(j).timeToStation, 5)); j++) {

            System.out.println(arrivalsList.get(j).lineId + " to " + arrivalsList.get(j).destinationName + " expected in " + arrivalsList.get(j).timeToStation / 60 + " minutes.");
            System.out.println("");
        }
    }
}
