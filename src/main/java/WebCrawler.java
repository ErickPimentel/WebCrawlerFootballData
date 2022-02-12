import Model.Arquivo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Constantes.ConstantesWebCrawler.*;
import static Util.SeasonUtil.converteSeasonSubstringPaisesPrimarios;

public class WebCrawler {

    private final List<String> lista_link_paises_principais = new ArrayList<String>();
    private final List<String> lista_link_paises_secundarios = new ArrayList<String>();

    private final List<Arquivo> lista_arquivos_paises_principais = new ArrayList<Arquivo>();
    private final List<Arquivo> lista_arquivos_paises_secundarios = new ArrayList<Arquivo>();

    public List<Arquivo> getLista_arquivos_paises_principais() {
        return lista_arquivos_paises_principais;
    }

    public List<Arquivo> getLista_arquivos_paises_secundarios() {
        return lista_arquivos_paises_secundarios;
    }

    public void buscaLinkPaises() throws IOException {

        Document document = Jsoup.connect(SITE_FOOTBALLDATA).get();

        Elements elementos_paises = document.select(ELEMENTOS_PAISES);

        int count = 0;
        for (Element pais : elementos_paises) {

            buscaLinkPaises(count, pais, 37, 56, lista_link_paises_principais);

            buscaLinkPaises(count, pais, 58, 90, lista_link_paises_secundarios);
            count++;
        }

    }

    private void buscaLinkPaises(int count, Element pais, int i, int i2, List<String> lista_link_paises_principais) {
        String link_pais;
        if (count > i && count < i2) {
            link_pais = pais.attr(HREF_ABSOLUTO);
            if (!lista_link_paises_principais.contains(link_pais)) {
                lista_link_paises_principais.add(link_pais);
            }
        }
    }

    public void runPaisesPrincipais() throws IOException {

        for (String link_pais : lista_link_paises_principais) {

            Document document = Jsoup.connect(link_pais).get();

            Elements links = document.select(A_HREF_COMECANDO_COM_MMZ);
            Elements b_elements = document.select(B_SELECTOR);
            String country = buscaCountry(b_elements);

            for (Element link : links) {

                String link_csv = buscaLinkCSV(link);
                String league = buscaLeague(link);
                String season = buscaSeason(link);
                String inicio_season = buscaInicioOuFimSeason(season, 0, 2);
                String fim_season = buscaInicioOuFimSeason(season, 2, 4);

                System.out.println(link_csv);

                Arquivo d = new Arquivo(link_csv, country, league, season, inicio_season, fim_season);
                lista_arquivos_paises_principais.add(d);
            }
        }
    }

    private String buscaInicioOuFimSeason(String season, int i, int f) {
        return converteSeasonSubstringPaisesPrimarios(season.substring(i, f));
    }

    private String buscaSeason(Element link) {
        return link.attr(HREF).substring(8, 12);
    }

    private String buscaLeague(Element link) {
        return link.text();
    }

    private String buscaLinkCSV(Element link) {
        return link.attr(HREF_ABSOLUTO);
    }

    private String buscaCountry(Elements b_elements) {
        String country = "";
        for (Element campo_country : b_elements) {
            if (campo_country.text().contains(DATA_FILES)) {
                country = campo_country.text().substring(12);
            }
        }
        return country;
    }

    public void runPaisesSecundarios() throws IOException {
        for (String link_pais : lista_link_paises_secundarios) {

            Document document = Jsoup.connect(link_pais).get();

            String link_csv;
            Elements links = document.select(A_HREF_TERMINANDO_COM_PORNTOCSV);
            for (Element link : links) {
                if (link.text().contains(CSV_CONTAINS)) {
                    link_csv = link.attr(HREF_ABSOLUTO);
                    System.out.println(link_csv);
                    Arquivo d = new Arquivo(link_csv);
                    lista_arquivos_paises_secundarios.add(d);
                }
            }
        }
    }
}