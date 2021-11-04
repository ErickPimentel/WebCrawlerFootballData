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


        String link_csv;
        String divisao;
        String season;
        List<Dados> lista_data = new ArrayList<Dados>();
        for (Element link: links) {


            link_csv = link.attr("abs:href");
            divisao = link.text();
            season = link.attr("href").substring(8,12);

            Dados d = new Dados(link_csv, divisao, season);
            lista_data.add(d);
        }

        return lista_data;
    }
}
