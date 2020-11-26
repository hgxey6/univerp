package edu.project.univerp.controllers;

import edu.project.univerp.models.Article;
import edu.project.univerp.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/blog/{id}")
    public String blogReadArticle(@PathVariable(value = "id") long id, Model model) {
        if (articleRepo.existsById(id) == false) {
            return "redirect:/blog";
        }

        Optional<Article> byId = articleRepo.findById(id);
        ArrayList<Article> list = new ArrayList<>();
        byId.ifPresent(list::add);
        model.addAttribute("article", list);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogArticleEdit(@PathVariable(value = "id") long id, Model model) {
        if (articleRepo.existsById(id) == false) {
            return "redirect:/blog";
        }

        Optional<Article> byId = articleRepo.findById(id);
        ArrayList<Article> list = new ArrayList<>();
        byId.ifPresent(list::add);
        model.addAttribute("article", list);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(
            @PathVariable(value = "id") long id, @RequestParam String title,
            @RequestParam String anons, @RequestParam String text, Model model) {

        Article article = articleRepo.findById(id).orElseThrow();
        article.setTitle(title);
        article.setAnons(anons);
        article.setText(text);

        articleRepo.save(article);

        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/delete")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {

        Article article = articleRepo.findById(id).orElseThrow();
        articleRepo.delete(article);

        return "redirect:/blog";
    }
}
