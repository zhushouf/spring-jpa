package com.huaweisoft.training.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 员工
 */
@Entity
@Table( name = "employee")
public class Employee implements Serializable{
    
    private static final long serialVersionUID = 7419229779731522702L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id; // 员工ID
    @Column
	private String name; // 员工姓名
    @Column
	private String gender; // 性别：1-男、2-女
    @Column
	private String phone; // 联系电话
    @Column
	private Date birthday; // 出生日期 （格式：2018-04-17）    

	public Employee() {
        super();
    }

    public Employee(Integer id, String name, String gender, String phone, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.birthday = birthday;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", phone=" + phone + ", birthday=" + birthday + "]";
    }
	
	

}
