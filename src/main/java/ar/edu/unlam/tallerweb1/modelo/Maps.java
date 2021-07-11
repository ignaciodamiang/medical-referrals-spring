package ar.edu.unlam.tallerweb1.modelo;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public  String obtenerCoordenadas(String direccion){
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

    public Double calcularDistancia(String origen, String destino) throws IOException {
        String punto1 = this.obtenerCoordenadas(origen);
        String punto2 = this.obtenerCoordenadas(destino);

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getJsonFactory(); // since 2.1 use mapper.getFactory() instead

        JsonParser jp1 = factory.createJsonParser(punto1);
        ArrayNode jsonOrigen = (ArrayNode) mapper.readTree(jp1).get("candidates");

        JsonParser jp2 = factory.createJsonParser(punto2);
        ArrayNode jsonDestino = (ArrayNode) mapper.readTree(jp2).get("candidates");

        double latOrigen = 0.0;
        double lngOrigen = 0.0;

        if (jsonOrigen.isArray()) {
            for (JsonNode jsonNode : jsonOrigen) {
                latOrigen = jsonNode.get("geometry").get("location").get("lat").asDouble();
                lngOrigen = jsonNode.get("geometry").get("location").get("lng").asDouble();
                break;
            }
        }


        double latDestino = 0.0;
        double lngDestino = 0.0;

        if (jsonOrigen.isArray()) {
            for (JsonNode jsonNode : jsonDestino) {
                latDestino = jsonNode.get("geometry").get("location").get("lat").asDouble();
                lngDestino = jsonNode.get("geometry").get("location").get("lng").asDouble();
                break;
            }
        }

        //double radioTierra = 3958.75;//en millas
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(latDestino - latOrigen);
        double dLng = Math.toRadians(lngDestino - lngOrigen);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(latOrigen)) * Math.cos(Math.toRadians(latDestino));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        BigDecimal bd=new BigDecimal(distancia).setScale(2, RoundingMode.HALF_DOWN);

        return bd.doubleValue();

    }
}



