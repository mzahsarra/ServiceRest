package fr.urouen.rss25sb.repository;

import fr.urouen.rss25sb.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    // Recherche par titre et date pour éviter les doublons
    Optional<Article> findByTitleAndPubDate(String title, LocalDateTime pubDate);

    // Recherche par GUID
    Optional<Article> findByGuid(String guid);
}