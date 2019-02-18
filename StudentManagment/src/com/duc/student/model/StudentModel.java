package com.duc.student.model;

public class StudentModel {

	private int id = 0;
	private String name;
	private int age = 0;
	private String address;
	private double gpa = 0;

	/**
	 * <b><i>toString</i></b>
	 * <p>
	 * public String toString()
	 * <p>
	 * This method is overrided to return all the instance variables.
	 * 
	 * @return str - str contains data of an object.
	 */
	@Override
	public String toString() {
		return "Student" + "\nID: " + getId() + "\nName: " + getName() + "\nAge: " + getAge() + "\nAddress: "
		+ getAddress() + "\nGpa: " + getGpa();
	}

	/**
	 * <b><i>getInfo</i></b>
	 * <p>
	 * public {@link String} getInfo()
	 * <p>
	 * This method is used to get all info of a student in a line.
	 * 
	 * @return str - the string that contains all info of a student.
	 */
	public String getInfo() {
		return getId() + "@" + getName() + "@" + getAge() + "@" + getAddress() + "@" + getGpa();
	}

	/**
	 * <b><i>getInfo</i></b>
	 * <p>
	 * public String getType()
	 * <p>
	 * This method is used to get the catagory output in the file.
	 * <p>
	 * In this case it is: ID||NAME||AGE||ADDRESS||GPA.
	 * 
	 * @return - the string above.
	 */
	public String getType() {
		return "ID@NAME@AGE@ADDRESS@GPA";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
}