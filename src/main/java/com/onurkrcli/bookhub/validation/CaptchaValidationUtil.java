package com.onurkrcli.bookhub.validation;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class CaptchaValidationUtil {

	public static boolean isCaptchaValueValid(HttpServletRequest servletRequest, String captchaValue) {
		String captchaSession = (String) servletRequest.getSession().getAttribute("captcha_value");
		if (!StringUtils.isEmpty(captchaSession)) {
			if (captchaSession.equals(captchaValue)) {
				return true;
			}
		}
		return false;
	}
}
