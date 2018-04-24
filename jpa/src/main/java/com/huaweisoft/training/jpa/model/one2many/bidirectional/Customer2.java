package com.huaweisoft.training.jpa.model.one2many.bidirectional;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;


//Entity，必选，表示这是一个JPA实体类
@Entity

//Table定义一些可选属性,e.g.:表明，索引等
//@Table(name="t_customer", indexes={@Index(name="idx_name_1",columnList = "col_name_1,..."),...})
public class Customer2 {
    
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
    
    
    @OneToMany(mappedBy="customer")
    //Customer、LoginLog2有了互相引用，成为了双向关系
    @OrderBy("loginTime desc")
    //OrderBy可以定义关联对象的排序规则
    private List<LoginLog2> log2s;
    
}
