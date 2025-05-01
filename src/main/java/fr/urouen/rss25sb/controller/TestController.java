package fr.urouen.rss25sb.controller;

import fr.urouen.rss25sb.model.Article;
import fr.urouen.rss25sb.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final ArticleService articleService;

    public TestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // Ajouter un article de test
    @PostMapping("/article")
    public ResponseEntity<Article> addTestArticle() {
        Article article = new Article(
                "Test Article",
                "test-guid-123", // GUID fixe pour correspondre à l'URL
                "Ceci est un article de test.",
                "Test Category",
                LocalDateTime.now()
        );
        Article savedArticle = articleService.addArticle(article);
        return ResponseEntity.ok(savedArticle);
    }
    // Lister tous les articles
    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }
}