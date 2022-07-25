import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHttp {

    private static final int RESPONSE_OK = 200;
    
    public String buscaDados(String url) {
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response;
        try {
            response = client.send(request, BodyHandlers.ofString());
            String body = "";
            if(response.statusCode() == RESPONSE_OK) {
                body = response.body();
            }
    
            return body;
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
