package edu.gatech.cs2340.group75.donationtracker.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class ActivityClasses implements Map<String, Class> {
	
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
	
	
	@Override
	public void clear() {
		classes.clear();
	}
	
	@Override
	public boolean containsKey(Object key) {
		return classes.containsKey(key);
	}
	
	@Override
	public boolean containsValue(Object value) {
		return classes.containsValue(value);
	}
	
	@Override
	public Set<Map.Entry<String, Class>> entrySet() {
		return classes.entrySet();
	}
	
	@Override
	public boolean equals(Object other) {
		return classes.equals(other);
	}
	
	@Override
	public Class get(Object key) {
		return classes.get(key);
	}
	
	@Override
	public int hashCode() {
		return classes.hashCode();
	}
	
	@Override
	public boolean isEmpty() {
		return classes.isEmpty();
	}
	
	@Override
	public Set<String> keySet() {
		return classes.keySet();
	}
	
	@Override
	public Class put(String key, Class value) {
		return classes.put(key, value);
	}
	
	@Override
	public void putAll(Map<? extends String, ? extends Class> map) {
		classes.putAll(map);
	}
	
	@Override
	public Class remove(Object key) {
		return classes.remove(key);
	}
	
	@Override
	public int size() {
		return classes.size();
	}
	
	@Override
	public Collection<Class> values() {
		return classes.values();
	}
}
