import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebCrawler {

    public static void main(String[] args) throws IOException {

        String url = "https://www.football-data.co.uk/englandm.php";

        Document document = Jsoup.connect(url).get();

        Elements links = document.select("a[href^=mmz]");

        String relHref;
        for (Element link: links) {
            relHref = link.attr("abs:href");
            System.out.println(relHref);
        }

//        for (Element link: links) {
//            System.out.println(link);
//        }
    }
}
