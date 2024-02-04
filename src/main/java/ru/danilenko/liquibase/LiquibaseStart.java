package ru.danilenko.liquibase;

import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.command.core.UpdateCommandStep;
import liquibase.command.core.helpers.DbUrlConnectionCommandStep;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import ru.danilenko.util.ConnectionToDB;

import java.sql.Connection;

public class LiquibaseStart {

    public LiquibaseStart(){

    }

    public static void migration() {

        try {
                Connection connection = ConnectionToDB.getConnection();
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
                database.setDefaultSchemaName("support");
                Liquibase liquibase = new liquibase.Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
                CommandScope updateCommand = new CommandScope(UpdateCommandStep.COMMAND_NAME)
                        .addArgumentValue(DbUrlConnectionCommandStep.DATABASE_ARG, liquibase.getDatabase())
                        .addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, "db/changelog/changelog.xml");
                updateCommand.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
