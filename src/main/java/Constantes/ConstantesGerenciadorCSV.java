package Constantes;

public interface ConstantesGerenciadorCSV {

    //Classe GerenciadorCSV
    String LINESEP = System.getProperty("line.separator");
    String V = ",";
    String COUNTRY = "Country";
    String LEAGUE = "League";
    String INICIO_SEASON = "InicioSeason";
    String FIM_SEASON = "FimSeason";
    String COLUNAS_PAISES_SECUNDARIOS = "Country,League," + INICIO_SEASON + V + FIM_SEASON + ",Date,Time,Home,Away,HG,AG,Res,PH,PD,PA,MaxH,MaxD,MaxA,AvgH,AvgD,AvgA" + LINESEP;
    String UNDERSCORE = "_";
    String FORMATO_CSV = ".csv";
    String FILE_PATH_PASTA_V1 = "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\";
    String FILE_PATH_PASTA_V2 = "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v2\\";
    String FILE_PATH_PASTA_V3 = "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\";
    String PAISES_SECUNDARIOS = "PaisesSecundarios";


}
