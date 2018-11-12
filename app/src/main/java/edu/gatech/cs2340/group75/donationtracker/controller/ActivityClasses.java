package edu.gatech.cs2340.group75.donationtracker.controller;

import java.util.HashMap;
import java.util.Map;

class ActivityClasses {
	
	private static final Map<String, Class> classes = new HashMap<>();
	
	public void add(Class newClass) {
		classes.put(newClass.getSimpleName(), newClass);
	}
	
	public Class get(String name) {
		return classes.get(name);
	}
}
