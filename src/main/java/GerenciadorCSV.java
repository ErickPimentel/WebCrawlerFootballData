import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.List;

public class GerenciadorCSV {

    public static final String LINESEP = System.getProperty("line.separator");
    public static final String VIRGULA = ",";
    public static final String COUNTRY = "Country";
    public static final String LEAGUE = "League";
    public static final String SEASON = "Season";
    public static final String UNDERSCORE = "_";
    public static final String FORMATO_CSV = ".csv";

    public void baixa_paises_primarios(List<Arquivo> lista_arquivos) throws IOException{
        for (Arquivo arquivo : lista_arquivos) {
            //pega a URL do csv
            URL url = new URL(arquivo.getLink_csv());

            //cria o arquivo
            String fileName = arquivo.getCountry() + UNDERSCORE + arquivo.getLeague() + UNDERSCORE + arquivo.getLink_csv().substring(40,44) + FORMATO_CSV;
            String filePath = "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\";

            arquivo.setFilePath(filePath);
            arquivo.setFileName(fileName);

            String fileAbsPath = filePath + fileName;
            File file = new File(fileAbsPath);

            //copia o conteudo da URL para o arquivo csv
            FileUtils.copyURLToFile(url, file);

            //adicionaColunas(filePath, fileName, arquivo);
        }
    }

    public void adicionaColunas(List<Arquivo> lista_arquivos) throws IOException {

        for (Arquivo arquivo : lista_arquivos) {

            BufferedReader br = null;
            BufferedWriter bw = null;

            try {
                File file = new File(arquivo.getFilePath(), arquivo.getFileName());
                File file2 = new File("C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v2\\", arquivo.getFileName());

                br = new BufferedReader(new InputStreamReader(new FileInputStream(file))) ;
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));

                String line = null;
                int i = 0;
                int iteraction = 0;
                for(line = br.readLine(); line != null; line = br.readLine(), i++){
                    if (iteraction==0){
                        bw.write(COUNTRY + VIRGULA + LEAGUE + VIRGULA + SEASON + VIRGULA + line + LINESEP);
                    }else {
                        String addedColumnCountry = String.valueOf(arquivo.getCountry());
                        String addedColumnLeague = String.valueOf(arquivo.getLeague());
                        String addedColumnSeason = String.valueOf(arquivo.getSeason());
                        bw.write(addedColumnCountry + VIRGULA + addedColumnLeague + VIRGULA + addedColumnSeason + VIRGULA + line + LINESEP);
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
}