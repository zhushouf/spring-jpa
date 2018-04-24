package com.huaweisoft.training.jpa.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

@Entity

// 由于LoginLog中有两个Field使用了@Id,这就是组合主键，因此一定要指定IdClass，否则报错
// IdClass可以是内部类，也可以是外部类
@IdClass(LoginLog.LogId.class)
public class LoginLog {

	@Id
	private Long customerId;

	@Id
	private Date loginTime;

	// 省略其他普通字段

	// 这个是ID类，类中的Field和LoginLog的ID Field匹配，
	// ID类必须实现Serializable
	protected class LogId implements Serializable {
		private static final long serialVersionUID = 5533713855588963984L;

		private Long customerId;

		private Date loginTime;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
			result = prime * result + ((loginTime == null) ? 0 : loginTime.hashCode());
			return result;

		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			LogId other = (LogId) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (customerId == null) {
				if (other.customerId != null)
					return false;
			} else if (!customerId.equals(other.customerId))
				return false;
			if (loginTime == null) {
				if (other.loginTime != null)
					return false;
			} else if (!loginTime.equals(other.loginTime))
				return false;
			return true;
		}

		private LoginLog getOuterType() {
			return LoginLog.this;
		}
	}
}
