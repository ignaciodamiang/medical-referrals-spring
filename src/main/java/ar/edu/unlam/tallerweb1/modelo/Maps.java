package ar.edu.unlam.tallerweb1.modelo;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class Maps {
    private final String url = "https://maps.googleapis.com/maps/api/";
    private final String key = "&key=AIzaSyCtr4ecOGJjwlxG3eXQeDCksZdMe2PNxBs";
    private HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    public  String obtenerCoordenadas(String direccion) throws URISyntaxException, IOException, InterruptedException {
        // Armando URL
        String request = this.url + "place/findplacefromtext/json?input=";
        String inputType = "&inputtype=textquery";
        String field = "&fields=geometry";
        String ubicacion = direccion.trim().replace(" ", "%20");

        //creando el request
        final HttpRequest requestPosts = HttpRequest.newBuilder().GET().uri(
                URI.create(request+ubicacion+inputType+field+key)
                ).build();
        //obtieniendo el response
        try {
            final HttpResponse<String> apiResponse = httpClient.send(requestPosts, HttpResponse.BodyHandlers.ofString());
            return apiResponse.body();
        }catch (IOException | InterruptedException ex) {
            System.out.println(ex);
            return null;
        }
    }
}



