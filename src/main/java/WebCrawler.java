import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebCrawler {

    public static void main(String[] args) throws IOException {

        String url = "https://www.football-data.co.uk/englandm.php";

        Document document = Jsoup.connect(url).get();

        Elements links = document.select("a[href^=mmz]");


        String link_csv;
        String campeonato;
        List<Data> lista_data = new ArrayList<Data>();
        for (Element link: links) {
            link_csv = link.attr("abs:href");
            campeonato = link.text();
            Data d = new Data(link_csv, campeonato);
            lista_data.add(d);
        }

        System.out.println(lista_data.toString());
    }
}
