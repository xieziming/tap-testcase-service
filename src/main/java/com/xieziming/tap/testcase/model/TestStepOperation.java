/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;

/**
 * Created by Suny on 5/22/16.
 */
@Data
@Entity
@Table(name="test_step_operation", uniqueConstraints = {@UniqueConstraint(columnNames={"operation", "operator"})})
public class TestStepOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String operation;

    @Column(length = 50)
    private String operator;

    @Column(length=500)
    private String remark;

    @Override
    public String toString() {
        return "TestStepOperation{" +
                "id=" + id +
                ", operation=" + operation +
                ", operator=" + operator +
                ", remark=" + remark +
                "}";
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && com.xieziming.tap.testcase.model.TestStepOperation.class.isAssignableFrom(o.getClass())){
            com.xieziming.tap.testcase.model.TestStepOperation to = (com.xieziming.tap.testcase.model.TestStepOperation) o;
            equals = (new EqualsBuilder()
                    .append(operation, to.operation)
                    .append(operator, to.operator)
                    .append(remark, to.remark).isEquals());
        }
        return equals;
    }
}
