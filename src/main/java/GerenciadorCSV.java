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
    public static final String FILE_PATH_PASTA_V1 = "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\";
    public static final String FILE_PATH_PASTA_V2 = "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v2\\";

    public void baixa_paises_primarios(List<Arquivo> lista_arquivos) throws IOException{
        for (Arquivo arquivo : lista_arquivos) {
            URL url = new URL(arquivo.getLink_csv());

            String fileName = arquivo.getCountry() + UNDERSCORE + arquivo.getLeague() + UNDERSCORE + arquivo.getLink_csv().substring(40,44) + FORMATO_CSV;
            String filePath = FILE_PATH_PASTA_V1;

            arquivo.setFilePath(filePath);
            arquivo.setFileName(fileName);

            String fileAbsPath = filePath + fileName;
            File file = new File(fileAbsPath);

            FileUtils.copyURLToFile(url, file);
        }
    }

    public void baixa_paises_secundarios(List<Arquivo> lista_arquivos)throws IOException{
        for (Arquivo arquivo : lista_arquivos) {

            URL url = new URL(arquivo.getLink_csv());

            String fileName = arquivo.getLink_csv().substring(36, 39) + FORMATO_CSV;
            String filePath = FILE_PATH_PASTA_V2;

            arquivo.setFilePath(filePath);
            arquivo.setFileName(fileName);

            String fileAbsPath = filePath + fileName;
            File file = new File(fileAbsPath);

            FileUtils.copyURLToFile(url, file);
        }

    }

    public void adicionaColunas(List<Arquivo> lista_arquivos) throws IOException {

        for (Arquivo arquivo : lista_arquivos) {

            BufferedReader br = null;
            BufferedWriter bw = null;

            try {
                File file = new File(arquivo.getFilePath(), arquivo.getFileName());
                File file2 = new File(FILE_PATH_PASTA_V2, arquivo.getFileName());

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