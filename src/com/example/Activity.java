package com.example;

import java.util.ArrayList;

public class Activity {

	public Activity() {
		// TODO Auto-generated constructor stub
	}
	
	String name;
	ArrayList<String> action;
	ArrayList<String> category;
	String parent;
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Activity [name=" + name + ", action=" + action + ", category=" + category + ", parent=" + parent + "]";
	}
	public ArrayList<String> getAction() {
		return action;
	}
	public void setAction(ArrayList<String> action) {
		this.action = action;
	}
	public ArrayList<String> getCategory() {
		return category;
	}
	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}
	
	
	

}
