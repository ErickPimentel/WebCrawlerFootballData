import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebCrawler {

    public List<String> busca_link_paises() throws IOException {

        String url = "https://www.football-data.co.uk/";

        Document document = Jsoup.connect(url).get();

        Elements elementos_paises = document.select("body > table > tbody > tr > td > table > tbody > tr > td > div > a");

        List<String> lista_link_paises = new ArrayList<String>();
        String link_pais;
        int count = 0;
        for (Element pais: elementos_paises) {
            if(count > 37 && count < 60){
                link_pais = pais.attr("abs:href");
                if (!lista_link_paises.contains(link_pais)){
                    lista_link_paises.add(link_pais);
                }
            }

            if(count > 62 && count < 94){
                link_pais = pais.attr("abs:href");
                if (!lista_link_paises.contains(link_pais)){
                    lista_link_paises.add(link_pais);
                }
            }
            count++;
        }

        return lista_link_paises;

    }

    public List<Arquivo> run(List<String> lista_link_paises) throws IOException {

        List<Arquivo> lista_arquivos = new ArrayList<Arquivo>();
        int count = 0;
        for (String link_pais: lista_link_paises) {
            if (count <= 12){

                String url = link_pais;
                Document document = Jsoup.connect(url).get();

                Elements links = document.select("a[href^=mmz]");

                Elements b_elements = document.select("b");
                String country = null;
                for (Element campo_country: b_elements) {
                    if (campo_country.text().contains("Data Files")){
                        country = campo_country.text().substring(12);
                    }
                }

                String link_csv;
                String league;
                String season;

                for (Element link: links) {

                    link_csv = link.attr("abs:href");
                    league = link.text();
                    season = link.attr("href").substring(8,12);

                    Arquivo d = new Arquivo(link_csv, country, league, season);
                    lista_arquivos.add(d);
                }

            }else {

                String url = link_pais;
                Document document = Jsoup.connect(url).get();

                String link_csv;
                Elements links = document.select("a[href$=.csv]");
                for (Element link: links) {
                    if (link.text().contains("CSV")){
                        link_csv = link.attr("abs:href");
                        Arquivo d = new Arquivo(link_csv);
                        lista_arquivos.add(d);
                    }
                }
            }


            count++;
        }

        System.out.println(lista_arquivos);
        return lista_arquivos;
    }
}