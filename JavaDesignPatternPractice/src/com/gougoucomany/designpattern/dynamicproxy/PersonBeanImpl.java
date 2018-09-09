package com.gougoucomany.designpattern.dynamicproxy;


public class PersonBeanImpl implements PersonBean {

	String name;
	String gender;
	String interests;
	int rating;
	int ratingCount = 0;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	@Override
	public int getHotOrNotRating() {
		if(ratingCount == 0) return 0;
		return (rating / ratingCount);
	}
	@Override
	public void setHotOrNotRating(int rating) {
		this.rating = rating;
		ratingCount ++;
		
	}
}
