package mongodb.basic;

import com.abhishek.mongodb.common.MongoConstant;
import com.abhishek.mongodb.common.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

/**
 * Created by info on 4/22/2016.
 */
public class EmployeeAggregateApp {
    public static void main(String arg[]){
        MongoClient mongo = new MongoClient(MongoConstant.DB_HOST, MongoConstant.DB_PORT);

        MongoDatabase mongoDatabase = mongo.getDatabase("testdb");

        MongoCollection<Document> employeeCollection = mongoDatabase.getCollection("employee");

        MongoUtil.printEmployeeDocuments(employeeCollection.find());

        Document groupKey = new Document("_id","$designation");
        Document aggregateFunction = new Document("$sum", 1);

        groupKey.append("count" ,aggregateFunction);

        System.out.println("Group Employees by Designation with Count");
        AggregateIterable<Document> groupByDesignation = employeeCollection.aggregate(Arrays.<Bson>asList(new Document("$group", groupKey)));

        MongoUtil.printEmployeeDocuments(groupByDesignation);

        System.out.println("Group all Patel Employees by Designation with Count");

        Document matchPatelDocument = new Document("lastName","Patel");
        AggregateIterable<Document> groupByDesignationOfPatelEmp = employeeCollection.aggregate(Arrays.<Bson>asList(
                        new Document("$match",matchPatelDocument),
                        new Document("$group", groupKey)
        ));

        MongoUtil.printEmployeeDocuments(groupByDesignationOfPatelEmp);

        mongo.close();
    }

}
