import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GerenciadorCSV {

    public void baixa(List<Dados> lista_dados) throws IOException{
        for (Dados dados: lista_dados) {
            URL url = new URL(dados.getLink_csv());
            String fileName = dados.getLink_csv().substring(40,44) + "_" + dados.getDivisao() + ".csv";
            String filePath = "C:\\Users\\Erick\\Desktop\\Dados\\" +  fileName;
            File file = new File(filePath);
            FileUtils.copyURLToFile(url, file);
        }
    }
}
