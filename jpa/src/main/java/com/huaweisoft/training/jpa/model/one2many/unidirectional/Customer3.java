package com.huaweisoft.training.jpa.model.one2many.unidirectional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//Entity，必选，表示这是一个JPA实体类
@Entity

//Table定义一些可选属性,e.g.:表明，索引等
//@Table(name="t_customer", indexes={@Index(name="idx_name_1",columnList = "col_name_1,..."),...})
public class Customer3 {
    
    //@Id,根据实际需要声明，声明了，则此字段为primary key
    @Id
    //Id的自动生成规则, @GeneratedValue的默认值是GenerationType.AUTO
    @GeneratedValue(strategy=GenerationType.AUTO)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    
    //@Column是可选的，jpa根据一定的规则决定是不是能够将此Field映射为数据表字段
    //@Column()
    private String name;
    
    //transient标记的Field不会成为数据表字段
    private transient String hashCode;
    
    //Customer没有对LoginLog的引用，因此这个关系是单向关系
    //@OneToMany(mappedBy="customer")
    //private List<LoginLog> log2s;
    
    
}
