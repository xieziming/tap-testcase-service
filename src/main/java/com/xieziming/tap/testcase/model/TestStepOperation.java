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
@Table(name="test_step_operation", uniqueConstraints = {@UniqueConstraint(columnNames={"operation"})})
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
        if(o != null && TestStepOperation.class.isAssignableFrom(o.getClass())){
            TestStepOperation to = (TestStepOperation) o;
            equals = (new EqualsBuilder()
                    .append(operation, to.getOperation())
                    .append(operator, to.getOperator())
                    .append(remark, to.getRemark()).isEquals());
        }
        return equals;
    }
}
