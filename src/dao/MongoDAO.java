package dao;

import config.DBConfig;
import entidade.Nota;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.bson.Document;

import com.mongodb.MongoClient; 
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;

public class MongoDAO {

    public static List<Nota> listarNotas(){
        List<Nota> lista = new ArrayList<>();

        MongoClient client = DBConfig.getNoSqlConnection();

        MongoDatabase database = client.getDatabase("notas_db");

        MongoCollection collection = database.getCollection("tb_nota");

        Gson gson = new Gson();

        MongoCursor<Document> cursor = collection.find().iterator();

        while(cursor.hasNext()){
            Nota nota = gson.fromJson(cursor.next().toJson(), Nota.class);

            lista.add(nota);
        }
        client.close();

        return lista;
    }

}