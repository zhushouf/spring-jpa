package com.huaweisoft.training.jpa.specifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import com.huaweisoft.training.jpa.model.Customer;
import com.huaweisoft.training.jpa.repository.CustomerRepository;

public class CustomerSpecification {

	@Autowired
	CustomerRepository customerDao;
	
	public Customer findOne(Integer id) {
	    return customerDao.findOne(id);
	}

	public Page<Customer> find(String name, Date logTimestamp, Integer pageNum, Integer pageSize) {

		// 根据某个字段排序
		Sort sort = new Sort(Direction.DESC, new String[] { "logTimestamp" });

		Page<Customer> beanPage = customerDao.findAll(new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			    List<Predicate> predicates = new ArrayList<>();
	            if (name != null) {
	                Predicate p = cb.like(root.get("name").as(String.class), "%" + name + "%");
	                predicates.add(p);
	            }
	            if (logTimestamp != null) {
	                Predicate p = cb.greaterThan(root.get("logTimestamp").as(Date.class), logTimestamp);
	                predicates.add(p);
	            }

	            if (logTimestamp != null) {
	                Predicate p = cb.lessThan(root.get("logTimestamp").as(Date.class), logTimestamp);
	                predicates.add(p);
	            }

				query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				return query.getRestriction();
			}
		}, new PageRequest(pageNum - 1, pageSize, sort));
		
		/*Page<Customer> beanPage = customerDao.findAll((root,query,cb) ->{
			List<Predicate> predicates = new ArrayList<>();
			if (name != null) {
				Predicate p = cb.like(root.get("name").as(String.class), "%" + name + "%");
				predicates.add(p);
			}
			if (logTimestamp != null) {
				Predicate p = cb.greaterThan(root.get("logTimestamp").as(Date.class), logTimestamp);
				predicates.add(p);
			}

			if (logTimestamp != null) {
				Predicate p = cb.lessThan(root.get("logTimestamp").as(Date.class), logTimestamp);
				predicates.add(p);
			}

			query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			return query.getRestriction();}, new PageRequest(pageNum - 1, pageSize, sort));
*/
		return beanPage;
	}

}
