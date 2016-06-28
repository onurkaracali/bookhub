package com.onurkrcli.bookhub.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.bingoohuang.patchca.background.MyCustomBackgroundFactory;
import com.github.bingoohuang.patchca.color.GradientColorFactory;
import com.github.bingoohuang.patchca.color.RandomColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.service.Captcha;
import com.github.bingoohuang.patchca.word.RandomWordFactory;

@Controller
@RequestMapping("/api/capcha")
public class CaptchaController {
	private static Logger logger = LogManager.getLogger(CaptchaController.class);
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public void createCaptchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			logger.info("Trying to create captcha image");
			ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
			RandomWordFactory wordFactory = new RandomWordFactory();
			wordFactory.setMinLength(4);
			wordFactory.setMaxLength(6);
			cs.setWordFactory(wordFactory);
			
			cs.setColorFactory(new GradientColorFactory());
			cs.setFilterFactory(new CurvesRippleFilterFactory(new RandomColorFactory()));
			cs.setBackgroundFactory(new MyCustomBackgroundFactory());
			cs.setWidth(300);
			cs.setHeight(100);
			
			Captcha captcha = cs.getCaptcha();
			BufferedImage image = captcha.getImage();
			
			request.getSession(true).setAttribute("captcha_value", captcha.getChallenge());
			
			ImageIO.write(image, "png", response.getOutputStream());
			response.getOutputStream().close();
			logger.info("Create captcha image success");
		}
		catch(Exception e) {
			
			logger.error("Could not generate captcha image", e);
			request.getSession().setAttribute("captcha_value", "");
		}
	}
}
