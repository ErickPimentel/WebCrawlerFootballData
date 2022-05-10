package DataBase;

import Model.Arquivo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static Constantes.ConstantesGerenciadorArquivo.*;


public class MongoDB {

    public void JsonToMongoDataBase(List<Arquivo> lista_arquivos, MongoCollection<Document> col) throws IOException {

        List<Document> lista_documento = new ArrayList<Document>();

        for (Arquivo arquivo: lista_arquivos){
            JSONParser jsonP = new JSONParser();

            try (FileReader reader = new FileReader(FILE_PATH_PASTA_V3 + arquivo.getFileName() + FORMATO_JSON)) {

                Object obj = jsonP.parse(reader);
                JSONArray empList = (JSONArray) obj;

                for (Object o : empList) {
                    Document doc = Document.parse(o.toString());
                    //col.insertOne(doc);
                    lista_documento.add(doc);
                    System.out.println(doc);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        col.insertMany(lista_documento);
    }


}

