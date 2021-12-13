import DataBase.MongoDB;
import Model.Arquivo;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        WebCrawler wb = new WebCrawler();

        System.out.println("buscaLinkPaises");
        wb.buscaLinkPaises();

        System.out.println("runPaisesPrincipais");
        wb.runPaisesPrincipais();

        System.out.println("runPaisesSecundarios");
        wb.runPaisesSecundarios();

        System.out.println("getLista_arquivos_paises_principais");
        List<Arquivo> lista_arquivos_paises_principais = wb.getLista_arquivos_paises_principais();

        System.out.println("getLista_arquivos_paises_secundarios");
        List<Arquivo> lista_arquivos_paises_secundarios = wb.getLista_arquivos_paises_secundarios();

        GerenciadorCSV g = new GerenciadorCSV();

        System.out.println("baixaPaisesPrimarios");
        g.baixaPaisesPrimarios(lista_arquivos_paises_principais);

        System.out.println("adicionaColunasPaisesPrincipais");
        g.adicionaColunasPaisesPrincipais(lista_arquivos_paises_principais);

        System.out.println("baixaPaisesSecundarios");
        g.baixaPaisesSecundarios(lista_arquivos_paises_secundarios);

        System.out.println("adicionaColunasPaisesSecundarios");
        g.adicionaColunasPaisesSecundarios(lista_arquivos_paises_secundarios);

        System.out.println("lista_arquivos_paises_secundarios");
        g.csvToJson(lista_arquivos_paises_secundarios);

        System.out.println("lista_arquivos_paises_principais");
        g.csvToJson(lista_arquivos_paises_principais);

//                List<Model.Arquivo> lista_arquivos_paises_secundarios = new ArrayList<Arquivo>(){
//            {
//                //add(new Model.Arquivo("https://www.football-data.co.uk/new/ARG.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "ARG"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/AUT.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "AUT"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/CHN.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "CHN"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/DNK.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "DNK"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/FIN.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "FIN"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/IRL.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "IRL"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/JPN.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "JPN"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/MEX.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "MEX"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/NOR.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "NOR"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/POL.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "POL"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/ROU.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "ROU"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/RUS.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "RUS"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/SWE.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "SWE"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/SWZ.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "SWZ"));
//                add(new Model.Arquivo("https://www.football-data.co.uk/new/USA.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v3\\", "USA"));
//            }
//        };


        MongoDB mongo = new MongoDB();
        System.out.println("JsonToMongoDataBase - Paises Secundarios");
        mongo.JsonToMongoDataBase(lista_arquivos_paises_secundarios);

        System.out.println("JsonToMongoDataBase - Paises Principais");
        mongo.JsonToMongoDataBase(lista_arquivos_paises_principais);

    }
}






