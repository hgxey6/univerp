package edu.project.univerp.controllers;

import edu.project.univerp.models.Article;
import edu.project.univerp.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    @Autowired
    private ArticleRepo articleRepo;

    @GetMapping("/blog")
    public String mainBlog(Model model) {
        Iterable<Article> articles = articleRepo.findAll();
        model.addAttribute("articles", articles);
        return "main-blog";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(
            @RequestParam String title, @RequestParam String anons, @RequestParam String text,
            Model model) {
        Article article = new Article(title, anons, text);
        articleRepo.save(article); //Сохраняет в репозитории объект
        return "redirect:/blog";
    }
}
