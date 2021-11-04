import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GerenciadorCSV {

    public static void main(String[] args) throws IOException {
        WebCrawler wb = new WebCrawler();
        List<Dados> lista_dados = wb.getDados();

        for (Dados dados: lista_dados) {
            URL url = new URL(dados.getLink_csv());
            String fileName = dados.getLink_csv().substring(40,44) + "_" + dados.getDivisao() + ".csv";
            File file = new File("C:\\Users\\Erick\\Desktop\\Dados\\" +  fileName);
            FileUtils.copyURLToFile(url, file);
        }

    }
}
