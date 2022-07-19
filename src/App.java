import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    private static final String MOCK_BODY = "\"items\":[{\"id\":\"tt0111161\",\"rank\":\"1\",\"title\":\"The Shawshank Redemption\",\"fullTitle\":\"The Shawshank Redemption (1994)\",\"year\":\"1994\",\"image\":\"https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX128_CR0,3,128,176_AL_.jpg\",\"crew\":\"Frank Darabont (dir.), Tim Robbins, Morgan Freeman\",\"imDbRating\":\"9.2\",\"imDbRatingCount\":\"2610991\"},{\"id\":\"tt0101414\",\"rank\":\"250\",\"title\":\"Beauty and the Beast\",\"fullTitle\":\"Beauty and the Beast (1991)\",\"year\":\"1991\",\"image\":\"https://m.media-amazon.com/images/M/MV5BMzE5MDM1NDktY2I0OC00YWI5LTk2NzUtYjczNDczOWQxYjM0XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX128_CR0,3,128,176_AL_.jpg\",\"crew\":\"Gary Trousdale (dir.), Paige O'Hara, Robby Benson\",\"imDbRating\":\"8.0\",\"imDbRatingCount\":\"447845\"}],\"errorMessage\":\"\"}";
    private static final int RESPONSE_OK = 200;
    public static void main(String[] args) throws Exception {
        var url = "https://imdb-api.com/pt-BR/API/Top250Movies/"+System.getenv("IMDB_KEY");
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = "";
        if(response.statusCode() == RESPONSE_OK) {
            body = response.body();
        } else {
            body = MOCK_BODY;
        }
        
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(TextFormatter.format(filme.get("title"), filme.get("image"), filme.get("imDbRating")));
        }
    }


}
