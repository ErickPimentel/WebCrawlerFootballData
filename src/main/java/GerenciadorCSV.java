import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.List;

public class GerenciadorCSV {

    public void baixa(List<Dados> lista_dados) throws IOException{
        for (Dados dados: lista_dados) {
            URL url = new URL(dados.getLink_csv());
            String fileName = dados.getLink_csv().substring(40,44) + "_" + dados.getDivisao();
            String filePath = "C:\\Users\\Erick\\Desktop\\Dados\\" +  fileName;
            File file = new File(filePath);
            FileUtils.copyURLToFile(url, file);

            adicionaColunaDivisao(filePath, dados);
        }
    }

    public void adicionaColunaDivisao(String filePath, Dados dados) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        final String lineSep = System.getProperty("line.separator");

        try {
            File file = new File(filePath);
            File file2 = new File(filePath + ".1");

            br = new BufferedReader(new InputStreamReader(new FileInputStream(file))) ;
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));

            String line = null;
            int i = 0;
            int iteraction = 0;
            for(line = br.readLine(); line != null; line = br.readLine(), i++){
                if (iteraction==0){
                    bw.write(line+","+"Divisao"+lineSep);
                }else {
                    String addedColumn = String.valueOf(dados.getDivisao());
                    bw.write(line+","+addedColumn+lineSep);
                }
                iteraction++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(br!=null)
                br.close();
            if(bw!=null)
                bw.close();
        }


    }
}
