package edu.project.univerp.repo;

import edu.project.univerp.models.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepo extends CrudRepository<Article, Long> {
}
