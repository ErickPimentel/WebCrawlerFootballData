import Model.Arquivo;
import Util.SeasonUtil;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static Constantes.ConstantesGerenciadorArquivo.*;
import static Util.CsvToJsonUtil.readObjectsFromCsv;
import static Util.CsvToJsonUtil.writeAsJson;
import static Util.SeasonUtil.converteSeasonSubstringPaisesSecundarios;

public class GerenciadorCSV {

    //pensar em refatorar
    public void baixaPaisesPrimarios(List<Arquivo> lista_arquivos) throws IOException{
        for (Arquivo arquivo : lista_arquivos) {
            copiaUrlParaArquivo(arquivo,
                    arquivo.getCountry() + UNDERSCORE + arquivo.getLeague() + UNDERSCORE + arquivo.getLink_csv().substring(40,44),
                    FILE_PATH_PASTA_V1);
        }
    }

    public void baixaPaisesSecundarios(List<Arquivo> lista_arquivos)throws IOException{
        for (Arquivo arquivo : lista_arquivos) {
            copiaUrlParaArquivo(arquivo,
                    arquivo.getLink_csv().substring(36, 39),
                    FILE_PATH_PASTA_V1);
        }
    }

    private void copiaUrlParaArquivo(Arquivo arquivo, String fileName, String filePath) throws IOException {
        URL url = new URL(arquivo.getLink_csv());
        File file = criaArquivo(arquivo, fileName, filePath);
        FileUtils.copyURLToFile(url, file);
    }

    private File criaArquivo(Arquivo arquivo, String fileName, String filePath) {
        arquivo.setFilePath(filePath);
        arquivo.setFileName(fileName);
        String fileAbsPath = filePath + fileName + FORMATO_CSV;
        return new File(fileAbsPath);
    }



    public void adicionaColunasPaisesPrincipais(List<Arquivo> lista_arquivos) throws IOException {

        for (Arquivo arquivo : lista_arquivos) {

            BufferedReader br = null;
            BufferedWriter bw = null;

            try {

                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(arquivo.getFilePath(), arquivo.getFileName() + FORMATO_CSV)))) ;
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(FILE_PATH_PASTA_V2, arquivo.getFileName() + FORMATO_CSV))));

                arquivo.setFilePath(FILE_PATH_PASTA_V2);

                String line;
                int i = 0;
                int iteraction = 0;
                for(line = br.readLine(); line != null; line = br.readLine(), i++){
                    if (iteraction==0){
                        bw.write((COUNTRY + V + LEAGUE + V + INICIO_SEASON + V + FIM_SEASON + V + line + LINESEP).replaceAll("\\.", "").replaceAll(">", ""));
                    }else {
                        bw.write((arquivo.getCountry() + V + arquivo.getLeague() + V + arquivo.getInicio_season() + V + arquivo.getFim_season() + V + line + LINESEP));
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

                br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(arquivo.getFilePath(), arquivo.getFileName() + FORMATO_CSV)))) ;
                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(FILE_PATH_PASTA_V2, arquivo.getFileName() + FORMATO_CSV))));

                arquivo.setFilePath(FILE_PATH_PASTA_V2);

                String line;
                int i = 0;
                int iteraction = 0;
                for(line = br.readLine(); line != null; line = br.readLine(), i++){
                    if (iteraction == 0){
                        bw.write(COLUNAS_PAISES_SECUNDARIOS.replaceAll("\\.", "").replaceAll(">", ""));
                    }else {
                        String[] j = line.split(V);
                        SeasonUtil seasonUtil = converteSeasonSubstringPaisesSecundarios(j[2]);
                        String inicio_season = seasonUtil.getInicio_season();
                        String fim_season = seasonUtil.getFim_season();
                        bw.write((j[0]+ V+j[1]+ V+inicio_season+ V+fim_season+ V+j[3]+ V+j[4]+ V+j[5]+ V+j[6]+ V+j[7]+ V+j[8]+ V+j[9]+ V+j[10]+ V+j[11]+ V+j[12]+ V+j[13]+ V+j[14]+ V+j[15]+ V+j[16]+ V+j[17]+ V+j[18] + LINESEP));
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

    public void csvToJson(List<Arquivo> lista_arquivos) throws IOException{

        for (Arquivo arquivo: lista_arquivos){


            File input = new File(FILE_PATH_PASTA_V2, arquivo.getFileName() + FORMATO_CSV);
            File output = new File(FILE_PATH_PASTA_V3, arquivo.getFileName() + FORMATO_JSON);

            List<Map<?, ?>> data = readObjectsFromCsv(input);
            writeAsJson(data, output);
        }
    }


}