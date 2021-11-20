import java.io.IOException;
import java.util.ArrayList;
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
    }
}










//        List<Arquivo> lista_arquivos_paises_secundarios = new ArrayList<Arquivo>(){
//            {
//                add(new Arquivo("https://www.football-data.co.uk/new/ARG.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "ARG.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/AUT.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "AUT.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/CHN.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "CHN.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/DNK.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "DNK.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/FIN.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "FIN.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/IRL.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "IRL.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/JPN.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "JPN.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/MEX.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "MEX.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/NOR.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "NOR.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/POL.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "POL.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/ROU.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "ROU.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/RUS.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "RUS.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/SWE.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "SWE.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/SWZ.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "SWZ.csv"));
//                add(new Arquivo("https://www.football-data.co.uk/new/USA.csv", "C:\\Users\\EPIMENT5\\Desktop\\Arquivos\\v1\\", "USA.csv"));
//            }
//        };