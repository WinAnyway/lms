package pl.com.kubachmielowiec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//    D:\hsqldb-2.3.4\hsqldb
//    java -cp lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:D:/lmsResources/lmsDB --dbname.0 lms

@SpringBootApplication
public class LmsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(LmsApplication.class);
    }
}
