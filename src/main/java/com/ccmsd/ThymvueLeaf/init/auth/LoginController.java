package com.ccmsd.ThymvueLeaf.init.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
class LoginController {
	@Autowired
	LoginService userService;

	@GetMapping("/")
	String index(Model model) {
		return "test";
	}

	@GetMapping("/login")
	String viewLogin(Model model) {
		model.addAttribute("now", LocalDateTime.now());
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/login")
	String doLogin(@Valid User user, Model model,HttpSession session) {
		model.addAttribute("now", LocalDateTime.now());
		model.addAttribute("user", user);
		int isvalidUser = userService.getUserDetails(user.getUsername());
		System.out.println(isvalidUser);
		if(isvalidUser>0)
		{
			System.out.println("valid user");
			session.setAttribute("LOGGEDIN_USER", "true");
			return "redirect:/";
		}
		return "login";
	}

	@GetMapping("/register")
	String viewRegister(Model model) {
		model.addAttribute("now", LocalDateTime.now());
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	String doRegister(@Valid User user, Model model) {
		model.addAttribute("now", LocalDateTime.now());
		model.addAttribute("user", user);
		userService.createUserDetails(user);
		return "register";
	}
	@GetMapping("/test")
	@ResponseBody
	PostModel dotest() {
		PostModel modl = new PostModel();
		modl.setPostId(1);
		modl.setTitle("test");
		return modl;
	}

	@GetMapping("properties")
	@ResponseBody
	java.util.Properties properties() {
		return System.getProperties();
	}

}
