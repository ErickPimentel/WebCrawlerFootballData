package DataBase;

import Model.Jogo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;

import static Constantes.ConstantesGerenciadorCSV.*;

public class MongoDB {
    public static void main(String[] args) throws IOException {
        MongoClient client = MongoClients.create("mongodb+srv://testUser:55555555@footballdata.wskzl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");

        MongoDatabase db = client.getDatabase("sampleDB");

        MongoCollection<Document> col = db.getCollection("sampleCollection");

        enviaJogosSecundariosParaMongoDataBase(col);


    }

    private static void enviaJogosSecundariosParaMongoDataBase(MongoCollection<Document> col) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(FILE_PATH_PASTA_V3 + PAISES_SECUNDARIOS + FORMATO_CSV))));

            String line;
            int i = 0;
            for(line = br.readLine(); line != null; line = br.readLine(), i++){
                String[] j = line.split(V);
                Jogo jogo = new Jogo(j[0], j[1], j[2], j[3], j[4], j[5], j[6], j[7], j[8], j[9], j[10], j[11], j[12], j[13], j[14], j[15], j[16], j[17], j[18], j[19]);
                System.out.println(jogo.hashCode());

                Document doc = new Document("_id", jogo.hashCode())
                        .append("Country", jogo.getCountry())
                        .append("League", jogo.getLeague())
                        .append("InicioSeason", jogo.getInicio_season())
                        .append("FimSeason", jogo.getFim_season())
                        .append("Date", jogo.getDate())
                        .append("Time", jogo.getTime())
                        .append("Home", jogo.getHome())
                        .append("Away", jogo.getAway())
                        .append("HG", jogo.getHg())
                        .append("AG", jogo.getAg())
                        .append("Res", jogo.getRes())
                        .append("PH", jogo.getPh())
                        .append("PD", jogo.getPd())
                        .append("PA", jogo.getPa())
                        .append("MaxH", jogo.getMaxh())
                        .append("MaxD", jogo.getMaxd())
                        .append("MaxA", jogo.getMaxh())
                        .append("AvgH", jogo.getAvgh())
                        .append("avgD", jogo.getAvgd())
                        .append("avgA", jogo.getAvga());

                col.insertOne(doc);
            }

            br.close();
        }catch (ArrayIndexOutOfBoundsException ignored){}
    }
}

