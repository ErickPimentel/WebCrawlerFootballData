import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorCSV {

    public static final String LINESEP = System.getProperty("line.separator");
    public static final String V = ",";
    public static final String COUNTRY = "Country";
    public static final String LEAGUE = "League";
    public static final String INICIO_SEASON = "Inicio Season";
    public static final String FIM_SEASON = "Fim Season";
    public static final String UNDERSCORE = "_";
    public static final String FORMATO_CSV = ".csv";
    public static final String FILE_PATH_PASTA_V1 = "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\";
    public static final String FILE_PATH_PASTA_V2 = "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v2\\";

    public void baixaPaisesPrimarios(List<Arquivo> lista_arquivos) throws IOException{
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

    public void baixaPaisesSecundarios(List<Arquivo> lista_arquivos)throws IOException{
        for (Arquivo arquivo : lista_arquivos) {

            URL url = new URL(arquivo.getLink_csv());

            String fileName = arquivo.getLink_csv().substring(36, 39) + FORMATO_CSV;
            String filePath = FILE_PATH_PASTA_V1;

            arquivo.setFilePath(filePath);
            arquivo.setFileName(fileName);

            String fileAbsPath = filePath + fileName;
            File file = new File(fileAbsPath);

            FileUtils.copyURLToFile(url, file);
        }

    }

    public void adicionaColunasPaisesPrincipais(List<Arquivo> lista_arquivos) throws IOException {

        for (Arquivo arquivo : lista_arquivos) {

            BufferedReader br = null;
            BufferedWriter bw = null;

            try {
                File file = new File(arquivo.getFilePath(), arquivo.getFileName());
                File file2 = new File(FILE_PATH_PASTA_V2, arquivo.getFileName());

                br = new BufferedReader(new InputStreamReader(new FileInputStream(file))) ;
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));

                String line;
                int i = 0;
                int iteraction = 0;
                for(line = br.readLine(); line != null; line = br.readLine(), i++){
                    if (iteraction==0){
                        bw.write(COUNTRY + V + LEAGUE + V + INICIO_SEASON + V + FIM_SEASON + V + line + LINESEP);
                    }else {
                        String addedColumnCountry = arquivo.getCountry();
                        String addedColumnLeague = arquivo.getLeague();
                        String addedColumnInicioSeason = arquivo.getInicio_season();
                        String addedColumnFimSeason = arquivo.getFim_season();
                        bw.write(addedColumnCountry + V + addedColumnLeague + V + addedColumnInicioSeason + V + addedColumnFimSeason + V + line + LINESEP);
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

    public void adicionaColunasPaisesSecundarios(List<Arquivo> lista_arquivos) throws IOException{
        for (Arquivo arquivo: lista_arquivos) {

            BufferedReader br = null;
            BufferedWriter bw = null;

            try {

                File file = new File(arquivo.getFilePath(), arquivo.getFileName());
                File file2 = new File(FILE_PATH_PASTA_V2, arquivo.getFileName());

                br = new BufferedReader(new InputStreamReader(new FileInputStream(file))) ;
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));

                String line;
                int i = 0;
                int iteraction = 0;
                for(line = br.readLine(); line != null; line = br.readLine(), i++){
                    if (iteraction == 0){
                        bw.write("Country,League," + INICIO_SEASON +"," + FIM_SEASON +",Date,Time,Home,Away,HG,AG,Res,PH,PD,PA,MaxH,MaxD,MaxA,AvgH,AvgD,AvgA");
                    }else {
                        String[] j = line.split(",");
                        Jogo jogo = new Jogo(j[0],j[1],j[2],j[3],j[4],j[5],j[6],j[7],j[8],j[9],j[10],j[11],j[12],j[13],j[14],j[15],j[16],j[17],j[18]);
                        converteSeasonSubstringPaisesSecundarios(jogo, j[2]);
                        bw.write(jogo.getCountry()+V+jogo.getLeague()+V+jogo.getInicio_season()+V+jogo.getFim_season()+V+jogo.getDate()+V+jogo.getTime()+V+jogo.getHome()+V+jogo.getAway()+V+
                                jogo.getHg()+V+jogo.getAg()+V+jogo.getRes()+V+jogo.getPh()+V+jogo.getPd()+V+jogo.getPa()+V+jogo.getMaxh()+V+jogo.getMaxd()+V+jogo.getMaxa()+V+jogo.getAvgh()+V+jogo.getAvgd()+V+jogo.getAvga() + LINESEP);
                    }
                    iteraction++;
                }

            }catch (ArrayIndexOutOfBoundsException ignored){
            }finally {
                if (br != null) br.close();
                if (bw != null) br.close();
            }
        }
    }

    public void converteSeasonSubstringPaisesSecundarios(Jogo jogo, String season){
        if (season.length() == 4){
            jogo.setInicio_season(season);
            jogo.setFim_season(season);
        }else {
            season = season.trim();
            jogo.setInicio_season(season.substring(0, 4));
            jogo.setFim_season(season.substring(5, 9));
        }

    }
}