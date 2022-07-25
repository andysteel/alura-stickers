import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        //var url = "https://imdb-api.com/pt-BR/API/Top250Movies/"+System.getenv("IMDB_KEY");
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoImdb();

        var url = "https://api.nasa.gov/planetary/apod?api_key="+System.getenv("NASA_KEY");
        ExtratorDeConteudo extrator = new ExtratorDeConteudoNasa();

        var clienteHttp = new ClienteHttp();
        String json = clienteHttp.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        for (Conteudo conteudo : conteudos) {

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            var nomeDoArquivo = conteudo.title() + ".png";

            var geradoraDeFigurinhas = new GeradorDeFigurinhas();
            geradoraDeFigurinhas.cria(inputStream, nomeDoArquivo, "TOPZERA");

            System.out.println(conteudo.title());
        }
    }


}
