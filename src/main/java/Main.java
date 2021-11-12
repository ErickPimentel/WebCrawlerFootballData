import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        WebCrawler wb = new WebCrawler();

        wb.busca_link_paises();
        wb.run();

//        GerenciadorCSV g = new GerenciadorCSV();
//        g.baixa(lista_arquivos);

//        g.adicionaColunas(lista_arquivos);


    }
}