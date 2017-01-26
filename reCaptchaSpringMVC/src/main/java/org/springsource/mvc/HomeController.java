package org.springsource.mvc;

import javax.servlet.ServletRequest;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	static {
		System.setProperty("java.net.useSystemProxies", "true");
	}

	@Autowired
	private ReCaptcha reCaptcha;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showNewUserFrom(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("userInfo") UserInfo userInfo,
			@RequestParam("recaptcha_challenge_field") String challangeField,
			@RequestParam("recaptcha_response_field") String responseField, ServletRequest servletRequest) {

		String remoteAddress = servletRequest.getRemoteAddr();

		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);

		if (reCaptchaResponse.isValid()) {
			return "success";
		} else {
			System.out.println("Invalid reCaptcha Found !!! Please try again");
			return "home";
		}
	}
}
