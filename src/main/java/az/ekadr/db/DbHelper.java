package az.ekadr.db;


import az.ekadr.properties.PropertiesHelper;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbHelper {

    public static Connection getConnection() throws Exception{

        Properties properties = PropertiesHelper.getProperties();
        Class.forName(properties.getProperty("db.driver"));
        Connection c = DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
        );

        return c;
    }

}
