package com.bio.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creates the application database if it does not exist, before the DataSource
 * connects. Ensures "Unknown database" is avoided on first run.
 */
@Component("databaseCreator")
public class DatabaseCreator implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DatabaseCreator.class);
    private static final Pattern JDBC_MYSQL_DB = Pattern.compile("jdbc:mysql://([^/]+)/([^?]*)(.*)");

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public void afterPropertiesSet() {
        Matcher m = JDBC_MYSQL_DB.matcher(jdbcUrl);
        if (!m.matches()) {
            return;
        }
        String serverPart = m.group(1);
        String dbName = m.group(2).trim();
        String queryPart = m.group(3);
        if (dbName.isEmpty()) {
            return;
        }
        String baseUrl = "jdbc:mysql://" + serverPart + "/" + (queryPart != null ? queryPart : "");
        if (!baseUrl.contains("?")) {
            baseUrl += "?";
        } else if (!baseUrl.endsWith("&") && !baseUrl.endsWith("?")) {
            baseUrl += "&";
        }
        if (!baseUrl.contains("allowPublicKeyRetrieval")) {
            baseUrl += "allowPublicKeyRetrieval=true";
        }
        String safeDbName = dbName.replace("`", "``");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(baseUrl, username, password);
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("CREATE DATABASE IF NOT EXISTS `" + safeDbName + "`");
                log.info("Database '{}' ensured.", dbName);
            }
        } catch (Exception e) {
            log.warn("Could not ensure database exists (will rely on existing DB): {}", e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ignored) {
                }
            }
        }
    }
}
