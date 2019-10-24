package com.ansel.testall.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloBean {
	private String id;
	private String name;
	private String age;
	private String sex;
	private String other;
	 
    public HelloBean() {
    }
    public HelloBean(String id, String name, String age, String sex, String other) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.other = other;
    }

	@XmlElement(name = "id")
	public void setId(String id) {
	    this.id = id;
	}

	public String getId() {
	    return id;
	}
	@XmlElement(name = "name")
	public void setName(String name) {
	    this.name = name;
	}

	public String getName() {
	    return name;
	}
	@XmlElement(name = "age")
	public void setAge(String age) {
	    this.age = age;
	}

	public String getAge() {
	    return age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	 
}