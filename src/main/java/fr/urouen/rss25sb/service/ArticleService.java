package fr.urouen.rss25sb.service;

import fr.urouen.rss25sb.model.Article;
import fr.urouen.rss25sb.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // Récupérer tous les articles
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    // Récupérer un article par ID
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    // Ajouter un article
    public Article addArticle(Article article) {
        // Vérifier si un article avec le même titre et date existe
        Optional<Article> existingArticle = articleRepository.findByTitleAndPubDate(
                article.getTitle(), article.getPubDate());
        if (existingArticle.isPresent()) {
            throw new IllegalArgumentException("Un article avec le même titre et date existe déjà.");
        }
        // Vérifier si le GUID est unique
        Optional<Article> existingByGuid = articleRepository.findByGuid(article.getGuid());
        if (existingByGuid.isPresent()) {
            throw new IllegalArgumentException("Un article avec le même GUID existe déjà.");
        }
        return articleRepository.save(article);
    }

    // Supprimer un article
    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new IllegalArgumentException("Article avec l'ID " + id + " n'existe pas.");
        }
        articleRepository.deleteById(id);
    }
}