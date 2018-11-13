package edu.gatech.cs2340.group75.donationtracker.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class ActivityClasses {
	
	private static final Map<String, Class> classes = new HashMap<>();
	
	public static void add(Class... newClasses) {
		for (Class newClass : newClasses) {
			classes.put(newClass.getSimpleName(), newClass);
		}
	}
	
	public static Class get(String name) {
		return classes.get(name);
	}
	
	protected ActivityClasses() {}
	
	public static Map<String, Class> getInstance() {
		return Collections.unmodifiableMap(classes);
	}
}
