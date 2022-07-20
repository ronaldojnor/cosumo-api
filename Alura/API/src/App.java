import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    //lista de cores
    public static final String RESET = "\033[0m";
    public static final String BRANCO = "\033[1;37m";
    public static final String VERMELHO = "\033[1;31m";
    public static final String AZUL = "\033[1;34m";
    public static final String AMARELO = "\033[1;33m";
    public static final String VERDE = "\033[1;32m";


    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();  
        
        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body); 

        //exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(AZUL + "Titulo: " + RESET + filme.get("title"));
            System.out.println(VERMELHO + "Capa: " + RESET + filme.get("image"));
            System.out.println(VERDE + "Classificação: " + RESET + filme.get("imDbRating"));
            
            //verificar a nota e aplicar as estrelas referente a nota
            double nota = Double.parseDouble(filme.get("imDbRating"));
            int classificacao = (int) nota;
            for (int i = 1; i < 10; i++) {
                if (i > classificacao) {
                break;
                } else {
                System.out.print("\u001b[40m\u2B50");
            }
      }
      System.out.println();
      //System.out.println();
    }
}
}
