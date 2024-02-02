package ru.danilenko;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.command.core.UpdateCommandStep;
import liquibase.command.core.helpers.DbUrlConnectionCommandStep;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.postgresql.core.ConnectionFactory.openConnection;


/**
 * Main class
 */
public class Main {

    /**
     * Here strats point of the program
     * @param args comand line values
     */
    public static void main(String[] args) {

        Compilation compilation = new Compilation();

        compilation.appStart();

//        final String URL = "jdbc:postgresql://localhost:5432/postgres";
//        final String USERNAME = "y_lab";
//        final String PASSWORD = "ylab";
//
//        Connection connection;
//
//
//        try{
//                Class.forName("org.postgresql.Driver");
//            } catch (ClassNotFoundException e){
//                e.printStackTrace();
//            }
//
//            try {
//                connection  = DriverManager.getConnection(URL,USERNAME, PASSWORD);
//                Scope.child(new HashMap<>(), () -> {
////            Connection connection = openConnection(); //your openConnection logic
//                    Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
//                    Liquibase liquibase = new liquibase.Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
//                    database.setDefaultSchemaName("support");
//                    CommandScope updateCommand = new CommandScope(UpdateCommandStep.COMMAND_NAME)
//                            .addArgumentValue(DbUrlConnectionCommandStep.DATABASE_ARG, liquibase.getDatabase())
//                            .addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, "db/changelog/changelog.xml");
//                    updateCommand.execute();
//                });
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }


    }
}