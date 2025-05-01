package fr.urouen.rss25sb.controller;

import fr.urouen.rss25sb.model.Article;
import fr.urouen.rss25sb.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/{guid}")
    public ResponseEntity<Article> getArticleByGuid(@PathVariable String guid) {
        Optional<Article> article = articleRepository.findByGuid(guid);
        return article.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{guid}")
    public ResponseEntity<Void> deleteArticleByGuid(@PathVariable String guid) {
        Optional<Article> article = articleRepository.findByGuid(guid);
        if (article.isPresent()) {
            articleRepository.delete(article.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{guid}")
    public ResponseEntity<Article> updateArticleByGuid(@PathVariable String guid, @RequestBody Article updatedArticle) {
        Optional<Article> existingArticle = articleRepository.findByGuid(guid);
        if (existingArticle.isPresent()) {
            Article article = existingArticle.get();
            article.setTitle(updatedArticle.getTitle());
            article.setDescription(updatedArticle.getDescription());
            article.setCategory(updatedArticle.getCategory());
            article.setPubDate(updatedArticle.getPubDate());
            Article savedArticle = articleRepository.save(article);
            return ResponseEntity.ok(savedArticle);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        Article savedArticle = articleRepository.save(article);
        return ResponseEntity.ok(savedArticle);
    }
}
