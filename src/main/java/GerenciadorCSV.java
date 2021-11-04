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

    public void baixa(List<Dados> lista_dados) throws IOException{
        for (Dados dados: lista_dados) {
            URL url = new URL(dados.getLink_csv());
            String fileName = dados.getCountry() + UNDERSCORE + dados.getLeague() + UNDERSCORE + dados.getLink_csv().substring(40,44) + FORMATO_CSV;
            String filePath = "C:\\Users\\Erick\\Desktop\\Dados\\v1\\";
            String fileAbsPath = filePath + fileName;
            File file = new File(fileAbsPath);
            FileUtils.copyURLToFile(url, file);

            adicionaColunas(filePath, fileName, dados);
        }
    }

    public void adicionaColunas(String filePath, String fileName, Dados dados) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            File file = new File(filePath, fileName);
            File file2 = new File("C:\\Users\\Erick\\Desktop\\Dados\\v2\\", fileName);

            br = new BufferedReader(new InputStreamReader(new FileInputStream(file))) ;
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));

            String line = null;
            int i = 0;
            int iteraction = 0;
            for(line = br.readLine(); line != null; line = br.readLine(), i++){
                if (iteraction==0){
                    bw.write(COUNTRY + VIRGULA + LEAGUE + VIRGULA + SEASON + VIRGULA + line + LINESEP);
                }else {
                    String addedColumnCountry = String.valueOf(dados.getCountry());
                    String addedColumnLeague = String.valueOf(dados.getLeague());
                    String addedColumnSeason = String.valueOf(dados.getSeason());
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