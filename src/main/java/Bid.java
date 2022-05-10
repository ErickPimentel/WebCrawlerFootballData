import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Constantes.ConstantesGerenciadorArquivo.BID_FILE_PATH;

public class Bid {

    private static void jsonBIDToMongoDataBase(ArrayList<String> listJsonFiles, MongoCollection<Document> col) throws IOException {

        List<Document> listDocument = new ArrayList<Document>();

        for (String jsonFile: listJsonFiles){
            JSONParser jsonP = new JSONParser();
            try (FileReader reader = new FileReader( BID_FILE_PATH + jsonFile)) {

                Object obj = jsonP.parse(reader);
                JSONArray empList = (JSONArray) obj;

                for (Object o : empList) {
                    Document doc = Document.parse(o.toString());
                    //col.insertOne(doc);
                    listDocument.add(doc);
                    System.out.println(doc);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        col.insertMany(listDocument);
    }

    private static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    private static ArrayList<Document> retrieveAllDocuments(MongoCollection<Document> col){
        ArrayList<Document> listAllDocuments = new ArrayList<Document>();
        FindIterable<Document> iterDoc = col.find();
        System.out.println("Listing All Mongo Documents");
        for (Document document : iterDoc) {
            listAllDocuments.add(document);
        }
        return listAllDocuments;
    }

    private static ArrayList<Document> retrieveSpecificDocument(MongoCollection<Document> col){
        ArrayList<Document> listSpecificDocuments = new ArrayList<Document>();
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("apelido", "RONALDO");
        System.out.println("Retrieving specific Mongo Document");
        for (Document document : col.find(searchQuery)) {
            listSpecificDocuments.add(document);
        }
        return listSpecificDocuments;
    }

    private static ArrayList<String> retrieveAllTeamNames(MongoCollection<Document> col){
        ArrayList<String> listTeamNames = new ArrayList<String>();
        for (String teamName : col.distinct("clube", String.class)){
            listTeamNames.add(teamName);
        }
        return listTeamNames;
    }

    private static ArrayList<Document> retrieveAllTeamNamesAndTeamCodes(MongoCollection<Document> col){
        ArrayList<Document> listTeamNames = new ArrayList<Document>();
        Bson projection = Projections.fields(Projections.include("clube", "codigo_clube"));
        for (Document team : col.find().projection(projection)){
            listTeamNames.add(team);
        }
        return listTeamNames;
    }

    private static Set<Document> retrieveTeams(MongoCollection<Document> col){
        Set<Document> listTeamNames = new HashSet<Document>();
        Bson projection = Projections.fields(Projections.include("clube", "codigo_clube", "uf"), Projections.excludeId());
        for (Document team : col.find().projection(projection)){
            listTeamNames.add(team);
        }
        return listTeamNames;
    }

    private static ArrayList<Document> retrievePlayers(MongoCollection<Document> col){
        ArrayList<Document> listAllDocuments = new ArrayList<Document>();
        Bson projection = Projections.fields(Projections.include("codigo_atleta", "apelido", "nome", "data_nascimento", "sexo"));
        for (Document document : col.find().projection(projection)) {
            listAllDocuments.add(document);
        }
        return listAllDocuments;
    }

    public static void main(String[] args) throws IOException {

        Set<String> setJsonFiles = Bid.listFilesUsingJavaIO(BID_FILE_PATH);
        ArrayList<String> listJsonFiles = new ArrayList<String>();
        listJsonFiles.addAll(setJsonFiles);


        MongoClient client = MongoClients.create("mongodb+srv://testUser:55555555@footballdata.wskzl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoDatabase db = client.getDatabase("BidDB");
        MongoCollection<Document> col = db.getCollection("BidCollection");

        //jsonBIDToMongoDataBase(listJsonFiles, col);
        //System.out.println(retrieveAllDocuments(col));
        //System.out.println(retrieveSpecificDocument(col));
        //System.out.println(retrieveAllTeamNames(col));
        //System.out.println(retrieveAllTeamNamesAndTeamCodes(col));


        System.out.println(retrieveTeams(col));
        System.out.println(retrievePlayers(col));

    }
}
