package com.example.liberary.utils;

import com.example.liberary.exeption.RepositoryException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariCPDataSource {

    private static final Logger logger = LoggerFactory.getLogger(HikariCPDataSource.class);

    private static HikariConfig dbConfig;

    static {
        Properties properties = getProperties();
        dbConfig = new HikariConfig();

        String dbUrl = properties.getProperty("database.url");
        String dbUser = properties.getProperty("database.user");
        String dbPassword = properties.getProperty("database.password");
        String driver = properties.getProperty("database.driver");

        dbConfig.setJdbcUrl(dbUrl);
        dbConfig.setUsername(dbUser);
        dbConfig.setPassword(dbPassword);
        dbConfig.setDriverClassName(driver);

        dbConfig.addDataSourceProperty("cachePrepStmts", "true");
        dbConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        dbConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dbConfig.setMinimumIdle(5);
        dbConfig.setIdleTimeout(600000);
        dbConfig.setMaximumPoolSize(10);
        dbConfig.setMaximumPoolSize(10);
        dbConfig.setAutoCommit(true);
        dbConfig.setMaxLifetime(1800000);
        dbConfig.setConnectionTimeout(30000);
    }

    private static HikariDataSource ds = new HikariDataSource(dbConfig);

    public static <T> T execute(ConnectionCallback<T> callback, String error) {
        try (Connection conn = ds.getConnection()) {
            return callback.doInConnection(conn);
        } catch (SQLException ex) {
            logger.warn("Repository " + error + ex.getMessage());
            throw new RepositoryException(error, ex);
        }
    }

    public static interface ConnectionCallback<T> {
        public T doInConnection(Connection conn) throws SQLException;

    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream resource = HikariCPDataSource.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private HikariCPDataSource() {
    }
}
