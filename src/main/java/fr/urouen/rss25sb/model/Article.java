package fr.urouen.rss25sb.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
@Data // Lombok pour générer getters, setters, toString, etc.
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique auto-généré

    @Column(nullable = false)
    private String title; // Titre de l'article

    @Column(nullable = false, unique = true)
    private String guid; // URL de l'article (globalement unique)

    @Column(columnDefinition = "TEXT")
    private String description; // Description ou contenu de l'article

    private String category; // Catégorie (optionnel)

    @Column(nullable = false)
    private LocalDateTime pubDate; // Date de publication

    // Constructeur par défaut requis par JPA
    public Article() {}

    // Constructeur pour faciliter la création
    public Article(String title, String guid, String description, String category, LocalDateTime pubDate) {
        this.title = title;
        this.guid = guid;
        this.description = description;
        this.category = category;
        this.pubDate = pubDate;
    }
}