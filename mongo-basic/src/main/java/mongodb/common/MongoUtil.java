package mongodb.common;

import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import org.bson.Document;

/**
 * Created by info on 4/24/2016.
 */
public class MongoUtil {

    public static void printEmployeeDocuments(AggregateIterable<Document> aggregateIterable){
        aggregateIterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        });
    }

    public static void printEmployeeDocuments(FindIterable<Document> findResultIterator){
        findResultIterator.forEach(new Block<Document>() {
            public void apply(Document document) {
                System.out.println("Employee :"+ document.toString());
            }
        });
    }
}
