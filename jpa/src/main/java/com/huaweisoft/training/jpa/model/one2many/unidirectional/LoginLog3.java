package com.huaweisoft.training.jpa.model.one2many.unidirectional;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(LoginLog3.LogId.class)
public class LoginLog3 {

    @Id
    private Date loginTime;

    @Id
    //LoginLog2和Customer的关系是多对一，
    //由于Customer没有与LoginLog2关联的字段，所以这个关系是单向关联关系
    @ManyToOne
    //JoinColumn定义LoginLog2表和Customer表关联的字段名称是customerId，
    //也就是说LoginLog2有一个外键customer_id
    @JoinColumn(name="customerId", nullable=false, updatable=false)
    private Customer3 customer;

    protected class LogId implements Serializable {
	private static final long serialVersionUID = 5533713855588963984L;

	private Customer3 customer;

	private Date loginTime;

	public Customer3 getCustomer() {
	    return customer;
	}

	public void setCustomer(Customer3 customer) {
	    this.customer = customer;
	}

	public Date getLoginTime() {
	    return loginTime;
	}

	public void setLoginTime(Date loginTime) {
	    this.loginTime = loginTime;
	}

	
    }

}
