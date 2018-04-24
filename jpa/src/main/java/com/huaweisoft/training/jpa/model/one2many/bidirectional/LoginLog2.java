package com.huaweisoft.training.jpa.model.one2many.bidirectional;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(LoginLog2.LogId.class)
public class LoginLog2 {

    @Id
    private Date loginTime;

    @Id
    //LoginLog2和Customer的关系是多对一，
    //由于Customer没有与LoginLog2关联的字段，所以这个关系是单向关联关系
    @ManyToOne
    //JoinColumn定义LoginLog2表和Customer表关联的字段名称是customerId，
    //也就是说LoginLog2有一个外键customer_id
    @JoinColumn(name="customerId", nullable=false, updatable=false)
    private Customer2 customer;

    protected class LogId implements Serializable {
	private static final long serialVersionUID = 5533713855588963984L;

	private Customer2 customer;

	private Date loginTime;

	public Customer2 getCustomer() {
	    return customer;
	}

	public void setCustomer(Customer2 customer) {
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
