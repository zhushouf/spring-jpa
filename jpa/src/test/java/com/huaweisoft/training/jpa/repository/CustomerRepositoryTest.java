package com.huaweisoft.training.jpa.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huaweisoft.training.jpa.model.Customer;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerRepositoryTest {

	@Autowired
	CustomerRepository dao;

	@Before
	public void testSave() {
		Customer customer = new Customer(1L, "zhang san");
		dao.save(customer);
	}

	@Test
	public void testFindById() {
		Customer customer = dao.findOne(1L);
		assertEquals("zhang san", customer.getName());
	}

	@Test
	public void testFindByName() {
		Customer customer = dao.readByName("zhang san");
		assertEquals("zhang san", customer.getName());
	}

	@Test
	public void testFindByNameWithNamedQuery() {
		Customer customer = dao.findByName("zhang san");
		assertEquals("zhang san", customer.getName());
	}

	@Test
	public void testQueryLikeName() {
		List<Customer> customers = dao.queryLikeName("%san%");
		for (Customer cus : customers) {
			assertEquals(cus.getName(), "zhang san");
		}
	}

	@Test
	public void testQueryLikeNameBySeqParam() {
		List<Customer> customers = dao.queryLikeNameBySeqParam("%san%");
		for (Customer cus : customers) {
			assertEquals(cus.getName(), "zhang san");
		}
	}

	@Test
	public void testFindByNameLike() {
		List<Customer> customers = dao.findAllByNameLike("%san%");
		for (Customer cus : customers) {
			assertEquals(cus.getName(), "zhang san");
		}
	}

	@Test
	public void testFindByNameLikeWithSortAndPage() {
		// 测试数据
		initData();

		// 排序条件，降序，
		Sort sort = new Sort(Direction.DESC, new String[] { "name", "id" });

		// 分页条件，第一页，每页10条
		Pageable pagination = new PageRequest(0, 10, sort);

		List<Customer> customers = dao.findAllByNameLike("%name%", pagination);

		assertEquals(10, customers.size());

		for (int i = 0; i < 10; i++) {
			// 降序的结果，最后一条id是99，依次递减，所以使用下面这个断言判断id是否获取正确
			assertEquals((99L - i), customers.get(i).getId().longValue());
		}

	}

	private void initData() {
		// 为了易读，使用了单条插入
		// 实际应用中应该使用批量插入数据 参考save(Iterable iterable)方法
		for (int i = 0; i < 100; i++) {
			Customer cust = new Customer(Long.valueOf(i), "name " + i);
			dao.save(cust);
		}
	}

	// 更新
	@Test
	@Transactional
	public void testUpdateAllByNameLike() {
		dao.updateAllByNameLike("%san%", "new zhang san");
		Customer cus = dao.findOne(1L);
		assertEquals("new zhang san", cus.getName());
	}

	// 删除
	@Test
	@Transactional
	public void testDeleteAllByNameLike() {
		dao.deleteAllByNameLike("%san%");
		Customer cus = dao.findOne(1L);
		assertNull(cus);
	}

	@Test
	public void testFindAllByNameLikeAndId() {
		List<Customer> existCustomers = dao.findAllByNameLikeAndId("%san%", 1L);
		List<Customer> nullCustomers = dao.findAllByNameLikeAndId("%san%", 2L);

		assertEquals(1, existCustomers.size());
		assertEquals(0, nullCustomers.size());
	}

	@Test
	public void testCountByNameLike() {
		int count = dao.countByNameLike("%san%");

		assertEquals(1, count);
	}
}
