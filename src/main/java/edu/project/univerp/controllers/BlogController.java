package edu.project.univerp.controllers;

import edu.project.univerp.models.Article;
import edu.project.univerp.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @Autowired
    private ArticleRepo articleRepo;

    @GetMapping("/blog")
    public String mainBlock(Model model) {
        Iterable<Article> articles = articleRepo.findAll();
        model.addAttribute("articles", articles);
        return "main-blog";
    }
}
