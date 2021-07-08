package ar.edu.unlam.tallerweb1.modelo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

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

    public String calcularDistancia(String origen, String destino) throws URISyntaxException, IOException, InterruptedException {
        String punto1 = this.obtenerCoordenadas(origen);
        String punto2 = this.obtenerCoordenadas(destino);

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getJsonFactory(); // since 2.1 use mapper.getFactory() instead

        JsonParser jp1 = factory.createJsonParser(punto1);
        ArrayNode jsonOrigen = (ArrayNode) mapper.readTree(jp1).get("candidates");

        JsonParser jp2 = factory.createJsonParser(punto2);
        ArrayNode jsonDestino = (ArrayNode) mapper.readTree(jp2).get("candidates");

        String latOrigen ="";
        String lngOrigen ="";

        if(jsonOrigen.isArray()) {
            for(JsonNode jsonNode : jsonOrigen) {
                latOrigen = jsonNode.get("geometry").get("location").get("lat").asText();
                lngOrigen = jsonNode.get("geometry").get("location").get("lng").asText();
                break;
            }
        }


        String latDestino = "";
        String lngDestino = "";

        if(jsonOrigen.isArray()) {
            for(JsonNode jsonNode : jsonDestino) {
                latDestino = jsonNode.get("geometry").get("location").get("lat").asText();
                lngDestino = jsonNode.get("geometry").get("location").get("lng").asText();
                break;
            }
        }

        // Armando URL
        String request = this.url + "distancematrix/json?units=metric";


        //creando el request
        final HttpRequest requestPosts = HttpRequest.newBuilder().GET().uri(
                URI.create(request+"&origins="+latOrigen+","+lngOrigen+"&destinations="+latDestino+","+lngDestino+key)
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

    public String devolverDistancia(String json) throws IOException {
        String distancia = "";

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getJsonFactory(); // since 2.1 use mapper.getFactory() instead

        JsonParser jp1 = factory.createJsonParser(json);
        ArrayNode jsonRows = (ArrayNode) mapper.readTree(jp1).get("rows");

        ArrayNode jsonElements = null;

        if(jsonRows.isArray()) {
            for(JsonNode jsonNode : jsonRows) {
                jsonElements = (ArrayNode) jsonNode.get("elements");
                break;
            }
            for(JsonNode jsonNode : jsonElements){
                distancia = jsonNode.get("distance").get("text").asText();
            }
        }
        return distancia;
    }
}



