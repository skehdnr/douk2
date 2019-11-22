package com.douk.web.pxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

@Component("ctx")
@SessionAttributes({"{ctx","js}"})
public class ContextProxy extends Proxy{
	@Autowired HttpSession Session;
	@Autowired HttpServletRequest Request;
	
	public void execute() {
		String ctx = Request.getContextPath();
		Session.setAttribute("ctx", ctx);
		Session.setAttribute("js",ctx + "/resources/js");
	}
}
