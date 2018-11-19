package jp.co.rakus.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.util.BeanUtil;

import jp.co.rakus.domain.Article;
import jp.co.rakus.form.ArticleForm;
import jp.co.rakus.repository.ArticleRepository;
import jp.co.rakus.repository.CommentRepository;

@Controller
@RequestMapping("/")
public class ArticleCotroller {
	@Autowired
	private ArticleRepository areticleRepository;
	@Autowired
	private CommentRepository commentRepository;
	
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	/**
	 * 掲示板画面の表示.
	 * 
	 * @param model モデル
	 * @return 掲示板画面
	 */
	@RequestMapping("/")
	public String index(Model model) {
		List<Article> articles = areticleRepository.findAllAtOnceSql();
		model.addAttribute("articles", articles);
		return "bbs";
	}
	
	@RequestMapping("/insertarticle")
	public String insertArticle(Model model,ArticleForm form) {
		Article article = new Article(null,form.getName(),form.getContent(),null);
		areticleRepository.insert(article);
		return index(model);
	}
	
	@RequestMapping("/insertcomment")
	public String insertComment(Model model) {
		return index(model);
	}
	
	/**
	 * 記事削除機能
	 * 
	 * @param model モデル
	 * @param id 記事id
	 * @return 掲示板画面
	 */
	@RequestMapping("/delete")
	public String deleteArticle(Model model,int id) {
		areticleRepository.deleteByIdOnceSql(id);
		return index(model);
	}
}
