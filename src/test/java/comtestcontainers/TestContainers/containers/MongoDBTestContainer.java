package comtestcontainers.TestContainers.containers;

import org.testcontainers.containers.MongoDBContainer;

import java.util.ArrayList;
import java.util.List;

public class MongoDBTestContainer extends MongoDBContainer {

    private static final String IMAGE = "mongo:4.0.10";
    private static MongoDBContainer INSTANCE;

    public MongoDBTestContainer(){
        super(IMAGE);
    }

    @Override
    public void start(){
        super.start();
        System.setProperty("HOST", this.getHost() + ":" + this.getFirstMappedPort());
        System.setProperty("DATABASE", "test-database");

    }

    @Override
    public void stop(){

    }

    public static MongoDBContainer getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MongoDBTestContainer();
        }
        return INSTANCE;
    }
}
