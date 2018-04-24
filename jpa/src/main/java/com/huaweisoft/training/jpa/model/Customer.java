package com.huaweisoft.training.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

//Entity，必选，表示这是一个JPA实体类
@Entity

// Table定义一些可选属性,e.g.:表明，索引等
// @Table(name="t_customer", indexes={@Index(name="idx_name_1",columnList =
// "col_name_1,..."),...})

// NamedQueries
// JPA的命名查询，可以在@NamedQueries中写多个@NamedQuery，也可以单独使用@@NamedQuery
//
@NamedQueries({@NamedQuery(name = "Customer.findByName", query = "select c from Customer c where c.name = ?1"),
                @NamedQuery(name = "Customer.findById", query = "select c from Customer c where c.id = ?1")})
public class Customer {

    // @Id,根据实际需要声明，声明了，则此字段为primary key
    @Id
    // Id的自动生成规则，@GeneratedValue表示GenerationType.AUTO
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // @Column是可选的，jpa根据一定的规则决定是不是能够将此Field映射为数据表字段
    @Column(length=32)
    private String name;

    private String firstName;

    private String lastName;

    // transient标记的Field不会成为数据表字段
    @Transient
    private String noneField;

    public Customer() {
        super();
    }
    public Customer(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    // getter setter 方法不是O/R映射必须，但是为了符合java
    // bean规范，也方便使用（例如构造Customer）那么还是设置setter，getter方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
