import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Util.SeasonUtil.converteSeasonSubstringPaisesPrimarios;

public class WebCrawler {

    private List<String> lista_link_paises_principais = new ArrayList<String>();
    private List<String> lista_link_paises_secundarios = new ArrayList<String>();

    private List<Arquivo> lista_arquivos_paises_principais = new ArrayList<Arquivo>();
    private List<Arquivo> lista_arquivos_paises_secundarios = new ArrayList<Arquivo>();

    public List<Arquivo> getLista_arquivos_paises_principais() {
        return lista_arquivos_paises_principais;
    }

    public List<Arquivo> getLista_arquivos_paises_secundarios() {
        return lista_arquivos_paises_secundarios;
    }

    public void buscaLinkPaises() throws IOException {

        String url = "https://www.football-data.co.uk/";

        Document document = Jsoup.connect(url).get();

        Elements elementos_paises = document.select("body > table > tbody > tr > td > table > tbody > tr > td > div > a");

        String link_pais;
        int count = 0;
        for (Element pais : elementos_paises) {
            if (count > 37 && count < 60) {
                link_pais = pais.attr("abs:href");
                if (!lista_link_paises_principais.contains(link_pais)) {
                    lista_link_paises_principais.add(link_pais);
                }
            }

            if (count > 62 && count < 94) {
                link_pais = pais.attr("abs:href");
                if (!lista_link_paises_secundarios.contains(link_pais)) {
                    lista_link_paises_secundarios.add(link_pais);
                }
            }
            count++;
        }

    }

    public void runPaisesPrincipais() throws IOException {

        for (String link_pais : lista_link_paises_principais) {
            String url = link_pais;
            Document document = Jsoup.connect(url).get();

            Elements links = document.select("a[href^=mmz]");

            Elements b_elements = document.select("b");
            String country = null;
            for (Element campo_country : b_elements) {
                if (campo_country.text().contains("Data Files")) {
                    country = campo_country.text().substring(12);
                }
            }

            String link_csv;
            String league;
            String season;

            for (Element link : links) {

                link_csv = link.attr("abs:href");
                league = link.text();
                season = link.attr("href").substring(8, 12);

                String inicio_season = converteSeasonSubstringPaisesPrimarios(season.substring(0, 2));
                String fim_season = converteSeasonSubstringPaisesPrimarios(season.substring(2, 4));

                Arquivo d = new Arquivo(link_csv, country, league, season, inicio_season, fim_season);
                lista_arquivos_paises_principais.add(d);
            }
        }
    }

    public void runPaisesSecundarios() throws IOException {
        for (String link_pais : lista_link_paises_secundarios) {
            String url = link_pais;
            Document document = Jsoup.connect(url).get();

            String link_csv;
            Elements links = document.select("a[href$=.csv]");
            for (Element link : links) {
                if (link.text().contains("CSV")) {
                    link_csv = link.attr("abs:href");
                    Arquivo d = new Arquivo(link_csv);
                    lista_arquivos_paises_secundarios.add(d);
                }
            }
        }
    }
}