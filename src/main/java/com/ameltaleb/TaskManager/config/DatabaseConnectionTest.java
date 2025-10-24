package com.ameltaleb.TaskManager.config;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionTest implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionTest.class);
    private final DataSource dataSource;

    public DatabaseConnectionTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            logger.info("✅ Conexión a la base de datos establecida correctamente");
            logger.info("✅ URL: {}", connection.getMetaData().getURL());
            logger.info("✅ Driver: {}", connection.getMetaData().getDriverName());
        } catch (Exception e) {
            logger.error("❌ Error conectando a la base de datos: {}", e.getMessage());
        }
    }
}