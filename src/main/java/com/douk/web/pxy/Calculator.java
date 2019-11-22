package com.douk.web.pxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Calculator {
	@Autowired Box<Integer> box;
	public int sum(int a ,int b) {
		box.put("c", a+b);
		return box.get("c");
	}
	public int sub(int a ,int b) {
		return a-b;
	}
	public int abs(int a) {
		return Math.abs(a);
	}
}
