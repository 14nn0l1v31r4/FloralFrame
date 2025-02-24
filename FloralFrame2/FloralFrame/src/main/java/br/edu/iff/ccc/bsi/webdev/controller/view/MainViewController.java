package br.edu.iff.ccc.bsi.webdev.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path= "/home/")
public class MainViewController {

	@GetMapping("/")
	public String gethome() {
		return "home.html";
	}
}
