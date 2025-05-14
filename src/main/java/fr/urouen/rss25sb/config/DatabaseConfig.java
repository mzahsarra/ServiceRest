package fr.urouen.rss25sb.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${POSTGRESQL_ADDON_URI:jdbc:postgresql://localhost:5432/rss25sb}")
    private String postgresqlUri;

    @Value("${POSTGRESQL_ADDON_USER:postgres}")
    private String username;

    @Value("${POSTGRESQL_ADDON_PASSWORD:password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        // Corrige l'URL si nécessaire
        String correctedUrl = postgresqlUri;
        if (correctedUrl.startsWith("postgresql://")) {
            correctedUrl = "jdbc:" + correctedUrl;
        } else if (!correctedUrl.startsWith("jdbc:")) {
            correctedUrl = "jdbc:postgresql://" + correctedUrl;
        }

        config.setJdbcUrl(correctedUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(config);
    }
}