package jp.co.rakus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Article;
import jp.co.rakus.repository.ArticleRepository;
import jp.co.rakus.repository.CommentRepository;

@Controller
@RequestMapping("/")
public class ArticleCotroller {
	@Autowired
	private ArticleRepository areticleRepository;
	@Autowired
	private CommentRepository commentRepository;
	
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
	
	
}
