package comtestcontainers.TestContainers.containers;

import org.testcontainers.containers.MySQLContainer;

public class MySQLTestContainer extends MySQLContainer<MySQLTestContainer> {

    private static final String IMAGE = "mysql";
    private static MySQLContainer INSTANCE;

    public MySQLTestContainer(){
        super(IMAGE);
    }
    
    @Override
    public void start(){
        super.start();
        System.setProperty("HOST", this.getJdbcUrl());
        System.setProperty("USERNAME", this.getUsername());
        System.setProperty("PASSWORD", this.getPassword());
    }
    
    @Override
    public void stop(){}
    
    public static MySQLContainer getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MySQLTestContainer()
                    .withDatabaseName("kairos-db")
                    .withUsername("kairos")
                    .withPassword("ruterute");
        }
        return INSTANCE;
    }
    
    
}
