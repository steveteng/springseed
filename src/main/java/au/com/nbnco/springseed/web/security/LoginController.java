package au.com.nbnco.springseed.web.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static au.com.nbnco.springseed.utils.StringConstants.REQUEST;

@Controller
@RequestMapping("/security")
public class LoginController {

	public static final String SECURITY_LOGIN_URL = "security/login";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = REQUEST, required = false) String request) {
		ModelAndView mv = new ModelAndView(SECURITY_LOGIN_URL);
		mv.addObject("loginFailed", false);
		mv.addObject("request", request);
		return mv;
	}

	@RequestMapping(value = "/login-failed", method = RequestMethod.GET)
	public ModelAndView loginFailed(@RequestParam(value = REQUEST, required = false) String request) {
		ModelAndView mv = new ModelAndView(SECURITY_LOGIN_URL);
		mv.addObject("loginFailed", true);
		mv.addObject("request", request);
		return mv;
	}
}
