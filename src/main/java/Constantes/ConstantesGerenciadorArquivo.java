package Constantes;

public interface ConstantesGerenciadorArquivo {

    //Classe GerenciadorCSV
    String LINESEP = System.getProperty("line.separator");
    String V = ",";
    String COUNTRY = "Country";
    String LEAGUE = "League";
    String START_SEASON = "StartSeason";
    String END_SEASON = "EndSeason";
    String COLUNAS_PAISES_SECUNDARIOS = "Country,League," + START_SEASON + V + END_SEASON + ",Date,Time,Home,Away,HG,AG,Res,PH,PD,PA,MaxH,MaxD,MaxA,AvgH,AvgD,AvgA" + LINESEP;
    String UNDERSCORE = "_";
    String FORMATO_CSV = ".csv";
    String FORMATO_JSON = ".json";
    String FILE_PATH_PASTA_V1 = "C:\\Users\\Erick\\Desktop\\Arquivos\\v1\\";
    String FILE_PATH_PASTA_V2 = "C:\\Users\\Erick\\Desktop\\Arquivos\\v2\\";
    String FILE_PATH_PASTA_V3 = "C:\\Users\\Erick\\Desktop\\Arquivos\\v3\\";
    String PAISES_SECUNDARIOS = "PaisesSecundarios";
    String PAISES_PRINCIPAIS = "PaisesPrincipais";

    String BID_FILE_PATH = "C:\\Users\\Erick\\Desktop\\BID_JSON\\";


}
