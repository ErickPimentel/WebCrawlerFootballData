import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        WebCrawler wb = new WebCrawler();

        List<Dados> lista_alunos = wb.run();

        GerenciadorCSV g = new GerenciadorCSV();
        g.baixa(lista_alunos);

    }
}
