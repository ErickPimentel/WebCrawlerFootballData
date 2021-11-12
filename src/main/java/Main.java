import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        WebCrawler wb = new WebCrawler();

        wb.busca_link_paises();
        wb.run();

        List<Arquivo> lista_arquivos_paises_principais = wb.getLista_arquivos_paises_principais();
        List<Arquivo> lista_arquivos_paises_secundarios = wb.getLista_arquivos_paises_secundarios();

        GerenciadorCSV g = new GerenciadorCSV();
        g.baixa_paises_primarios(lista_arquivos_paises_principais);
        g.adicionaColunas(lista_arquivos_paises_principais);


    }
}