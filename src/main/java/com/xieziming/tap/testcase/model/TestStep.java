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
@Table(name="test_step")
public class TestStep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length=10)
    private Integer stepOrder;

    @OneToOne(cascade = CascadeType.PERSIST)
    private TestStepOperation testStepOperation;

    @Column(length=500)
    private String parameter;

    @Override
    public String toString() {
        return "TestStep{" +
                "id=" + id +
                ", stepOrder=" + stepOrder +
                ", testOperation=" + testStepOperation +
                ", parameter=" + parameter +
                "}";
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && com.xieziming.tap.testcase.model.TestStep.class.isAssignableFrom(o.getClass())){
            com.xieziming.tap.testcase.model.TestStep ts = (com.xieziming.tap.testcase.model.TestStep) o;
            equals = (new EqualsBuilder()
                    .append(stepOrder, ts.stepOrder)
                    .append(testStepOperation, ts.testStepOperation)
                    .append(parameter, ts.parameter).isEquals());
        }
        return equals;
    }
}
