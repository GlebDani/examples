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
import ru.danilenko.dao.AuditDAO;
import ru.danilenko.dao.CounterDAO;
import ru.danilenko.dao.CounterTypeDAO;
import ru.danilenko.dao.UserDAO;
import ru.danilenko.liquibase.LiquibaseStart;
import ru.danilenko.mapper.CounterMapper;
import ru.danilenko.mapper.CounterTypeMapper;
import ru.danilenko.mapper.UserMapper;
import ru.danilenko.model.User;
import ru.danilenko.util.ConnectionToDB;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

//        LiquibaseStart.migration(new ConnectionToDB());




    }
}