import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebCrawler {

    public List<Dados> run() throws IOException {
        String url = "https://www.football-data.co.uk/englandm.php";

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
        List<Dados> lista_dados = new ArrayList<Dados>();
        for (Element link: links) {


            link_csv = link.attr("abs:href");
            league = link.text();
            season = link.attr("href").substring(8,12);

            Dados d = new Dados(link_csv, country, league, season);
            lista_dados.add(d);
        }

        return lista_dados;
    }
}