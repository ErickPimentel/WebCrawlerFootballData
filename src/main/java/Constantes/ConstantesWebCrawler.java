package Constantes;

public interface ConstantesWebCrawler {
    //Classe WebCrawler
    String SITE_FOOTBALLDATA = "https://www.football-data.co.uk/";
    String HREF_ABSOLUTO = "abs:href";
    String HREF = "href";
    String ELEMENTOS_PAISES = "body > table > tbody > tr > td > table > tbody > tr > td > div > a";
    String A_HREF_COMECANDO_COM_MMZ = "a[href^=mmz]";
    String DATA_FILES = "Data Files";
    String A_HREF_TERMINANDO_COM_PORNTOCSV = "a[href$=.csv]";
    String CSV_CONTAINS = "CSV";
    String B_SELECTOR = "b";
}
