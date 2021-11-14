import Util.SeasonUtil;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.List;

import static Util.SeasonUtil.converteSeasonSubstringPaisesSecundarios;

public class GerenciadorCSV {

    public void baixaPaisesPrimarios(List<Arquivo> lista_arquivos) throws IOException{
        for (Arquivo arquivo : lista_arquivos) {

            URL url = new URL(arquivo.getLink_csv());
            String fileName = arquivo.getCountry() + Constantes.UNDERSCORE + arquivo.getLeague() + Constantes.UNDERSCORE + arquivo.getLink_csv().substring(40,44) + Constantes.FORMATO_CSV;
            String filePath = Constantes.FILE_PATH_PASTA_V1;

            File file = criaArquivo(arquivo, fileName, filePath);
            copiaUrlParaArquivo(url, file);
        }
    }

    public void baixaPaisesSecundarios(List<Arquivo> lista_arquivos)throws IOException{
        for (Arquivo arquivo : lista_arquivos) {

            URL url = new URL(arquivo.getLink_csv());
            String fileName = arquivo.getLink_csv().substring(36, 39) + Constantes.FORMATO_CSV;
            String filePath = Constantes.FILE_PATH_PASTA_V1;

            File file = criaArquivo(arquivo, fileName, filePath);
            copiaUrlParaArquivo(url, file);
        }
    }

    private File criaArquivo(Arquivo arquivo, String fileName, String filePath) {
        arquivo.setFilePath(filePath);
        arquivo.setFileName(fileName);
        String fileAbsPath = filePath + fileName;
        return new File(fileAbsPath);
    }

    private void copiaUrlParaArquivo(URL url, File file) throws IOException {
        FileUtils.copyURLToFile(url, file);
    }

    public void adicionaColunasPaisesPrincipais(List<Arquivo> lista_arquivos) throws IOException {

        for (Arquivo arquivo : lista_arquivos) {

            BufferedReader br = null;
            BufferedWriter bw = null;

            try {

                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(arquivo.getFilePath(), arquivo.getFileName())))) ;
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(Constantes.FILE_PATH_PASTA_V2, arquivo.getFileName()))));

                String line;
                int i = 0;
                int iteraction = 0;
                for(line = br.readLine(); line != null; line = br.readLine(), i++){
                    if (iteraction==0){
                        bw.write(Constantes.COUNTRY + Constantes.V + Constantes.LEAGUE + Constantes.V + Constantes.INICIO_SEASON + Constantes.V + Constantes.FIM_SEASON + Constantes.V + line + Constantes.LINESEP);
                    }else {
                        bw.write(arquivo.getCountry() + Constantes.V + arquivo.getLeague() + Constantes.V + arquivo.getInicio_season() + Constantes.V + arquivo.getFim_season() + Constantes.V + line + Constantes.LINESEP);
                    }
                    iteraction++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(br!=null) br.close();
                if(bw!=null) bw.close();
            }
        }

    }

    public void adicionaColunasPaisesSecundarios(List<Arquivo> lista_arquivos) throws IOException{
        for (Arquivo arquivo: lista_arquivos) {

            BufferedReader br = null;
            BufferedWriter bw = null;

            try {

                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(arquivo.getFilePath(), arquivo.getFileName())))) ;
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(Constantes.FILE_PATH_PASTA_V2, arquivo.getFileName()))));

                String line;
                int i = 0;
                int iteraction = 0;
                for(line = br.readLine(); line != null; line = br.readLine(), i++){
                    if (iteraction == 0){
                        bw.write(Constantes.COLUNAS_PAISES_SECUNDARIOS);
                    }else {
                        String[] j = line.split(Constantes.V);
                        Jogo jogo = new Jogo(j[0],j[1],j[2],j[3],j[4],j[5],j[6],j[7],j[8],j[9],j[10],j[11],j[12],j[13],j[14],j[15],j[16],j[17],j[18]);
                        SeasonUtil seasonUtil = converteSeasonSubstringPaisesSecundarios(j[2]);
                        jogo.setInicio_season(seasonUtil.getInicio_season());
                        jogo.setFim_season(seasonUtil.getFim_season());
                        bw.write(jogo.getCountry()+ Constantes.V+jogo.getLeague()+ Constantes.V+jogo.getInicio_season()+ Constantes.V+jogo.getFim_season()+ Constantes.V+jogo.getDate()+ Constantes.V+jogo.getTime()+ Constantes.V+jogo.getHome()+ Constantes.V+jogo.getAway()+ Constantes.V+
                                jogo.getHg()+ Constantes.V+jogo.getAg()+ Constantes.V+jogo.getRes()+ Constantes.V+jogo.getPh()+ Constantes.V+jogo.getPd()+ Constantes.V+jogo.getPa()+ Constantes.V+jogo.getMaxh()+ Constantes.V+jogo.getMaxd()+ Constantes.V+jogo.getMaxa()+ Constantes.V+jogo.getAvgh()+ Constantes.V+jogo.getAvgd()+ Constantes.V+jogo.getAvga() + Constantes.LINESEP);
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
}