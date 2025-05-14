package fr.urouen.rss25sb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Value("${POSTGRESQL_ADDON_URI:}")
    private String postgresqlUri;

    @Bean
    public String dataSourceUrl() {
        if (postgresqlUri != null && !postgresqlUri.isEmpty()) {
            if (postgresqlUri.startsWith("postgresql://")) {
                return "jdbc:" + postgresqlUri;
            } else if (!postgresqlUri.startsWith("jdbc:postgresql://")) {
                return "jdbc:postgresql://" + postgresqlUri;
            }
        }
        // Valeur par défaut pour l'environnement local
        return "jdbc:postgresql://localhost:5432/rss25sb";
    }
}