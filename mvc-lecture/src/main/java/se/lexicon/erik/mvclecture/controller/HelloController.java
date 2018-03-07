package se.lexicon.erik.mvclecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	@RequestMapping("/hello")
	public String sayHello(Model theModel) {
		String greeting = "Hi there";
		
		theModel.addAttribute("greeting", greeting);
		return "helloworld";
	}
}
