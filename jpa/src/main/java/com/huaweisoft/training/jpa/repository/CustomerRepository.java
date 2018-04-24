package com.huaweisoft.training.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.huaweisoft.training.jpa.model.Customer;


//JpaRepository是Spring Data JPA提供的接口
//CustomerRepository继承了JpaRepository，并通范型参数Cusomter， Long指定了实体的类和实体的ID
//开发者不需要对CustomerRepository写任何实现，Spring Data JPA自动为所有Repository的子类生成实现
//生成实现的动作将在Spring 容器创建时完成

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    
    //基于方法名的查询
    public Customer readByName(String name);
    
    //基于方法名的查询, 模糊查询
    public List<Customer> findAllByNameLike(String name);
    
    //基于方法名的查询, 复合条件的模糊查询
    public List<Customer> findAllByNameLikeAndId(String name, Long id);
    
    //基于方法名的查询, 可以分页的模糊查询
    public List<Customer> findAllByNameLike(String name,Pageable pageable);
    
    //基于方法名的查询, 可排序的模糊查询
    public List<Customer> findAllByNameLike(String name,Sort sort);
    
    //基于方法名的查询, 统计符合条件的记录数量
    public int countByNameLike(String name);
    
    //注意：这个方法是容器加载时错误的, 不允许同时使用pageable和sort，因为Pagebale本身可以带sort参数
    //public List<Customer> findAllByNameLikePagination(String name,Pageable pageable,Sort sort);
    
    
    public Customer findOne(Integer id);
    
    //自定义查询， 将findAllByNameLike(name)改写成自定义查询
    //方法名称可以随便写，不用遵守Spring Data查询方法的规则
    //通过命名参数的方式（@Param）注入查询参数
    @Query(nativeQuery = true, value="select c from customer c where c.name like ?1")
    public List<Customer> queryLikeName(String name);
    
    //这种方法通过参数顺序注入查询参数，建议使用命名参数的方式
    @Query("select c from Customer c where c.name like ?1")
    public List<Customer> queryLikeNameBySeqParam(String name);
    
    
    //不是Spring data JPA的查询方法，这是JPA的NamedQuery的方法，
    //在Customer有对应的@NamedQuery定义
    //在使用了Spring data JPA的情况下，不太建议使用这种方式，因为查询语句定义到了Entity中，况且这些需求可以通过JPA查询方法和自定义查询实现
    
    //在CustomerRepository接口中定义：
    public Customer findByName(String name);
    
    /**
     * 
     * 
     * @param name
     * @return
     */
    public List<Customer> findCustomerByFirstNameOrLastNameDesc(String name);

    
    
    @Modifying(clearAutomatically=true)
    //使用了原生SQL，由于命名的原因，这里的原生SQL和JPQL看上去差别不大，
    @Query(value="delete from customer c where c.name like :qname",nativeQuery = true)
    public int deleteAllByNameLike(@Param("qname") String name);
    
    @Modifying(clearAutomatically=true)
    @Query("update Customer c set c.name=:newName where c.name like :qname")
    public int updateAllByNameLike(@Param("qname") String name, @Param("newName") String newName);
}


