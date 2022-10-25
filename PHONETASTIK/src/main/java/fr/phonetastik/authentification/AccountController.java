package fr.phonetastik.authentification;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * Controller utilise pour la creation d'un nouveau compte utilisateur
 */

@Controller
public class AccountController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/new-account")
	public ModelAndView newAccount() {
		return new ModelAndView("new-account", Map.of("user", new User()));
	}

	@PostMapping(path = "/new-account")
	public String createAccount(User user) {
		try {
			userService.createUser(user);

		} catch (Exception e) {
			return "redirect:/login?error2=" + e.getMessage();
		}
		return "redirect:/login?reussite=true";

	}

}
