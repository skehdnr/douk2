package com.douk.web.pxy;

import java.util.ArrayList;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Inventory <T>{
	private ArrayList<T> list;
	public Inventory() {list = new ArrayList<T>();}
	public void add (T t) {list.add(t);}
	public void clear() {list.clear();}
	public T get(int i) {return list.get(i);}
	public ArrayList<T> get(){return list;}
}
