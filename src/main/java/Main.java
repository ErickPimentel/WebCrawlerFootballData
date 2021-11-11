import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        WebCrawler wb = new WebCrawler();

        List<String> lista_link_paises = wb.busca_link_paises();

        List<Arquivo> lista_arquivos = wb.run(lista_link_paises);
//
//        GerenciadorCSV g = new GerenciadorCSV();
//        g.baixa(lista_arquivos);
//        g.adicionaColunas(lista_arquivos);


    }
}