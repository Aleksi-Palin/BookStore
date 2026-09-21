package fi.haagahelia.bookstorepalinaleksi.config;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource(DataSourceProperties properties) {
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl != null && !databaseUrl.isBlank()) {
            configureFromRenderUrl(properties, databaseUrl);
        }

        return properties.initializeDataSourceBuilder().build();
    }

    private void configureFromRenderUrl(DataSourceProperties properties, String databaseUrl) {
        URI uri = URI.create(databaseUrl);
        String userInfo = uri.getUserInfo();
        String host = uri.getHost();
        String port = uri.getPort() > 0 ? ":" + uri.getPort() : "";
        String database = uri.getPath();

        if (userInfo == null || host == null || database == null || database.isBlank()) {
            throw new IllegalArgumentException("DATABASE_URL is not a valid PostgreSQL connection URL");
        }

        String[] credentials = userInfo.split(":", 2);
        if (credentials.length != 2) {
            throw new IllegalArgumentException("DATABASE_URL must include a username and password");
        }

        properties.setUrl("jdbc:postgresql://" + host + port + database);
        properties.setUsername(decode(credentials[0]));
        properties.setPassword(decode(credentials[1]));
    }

    private String decode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }
}
