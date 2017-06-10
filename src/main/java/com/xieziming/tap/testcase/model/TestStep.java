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
@Table(name="test_step", uniqueConstraints = {@UniqueConstraint(columnNames={"test_case_uid", "stepOrder"})})
public class TestStep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length=10)
    private Integer stepOrder;

    @ManyToOne(cascade = CascadeType.PERSIST)
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
        if(o != null && TestStep.class.isAssignableFrom(o.getClass())){
            TestStep ts = (TestStep) o;
            equals = (new EqualsBuilder()
                    .append(stepOrder, ts.getStepOrder())
                    .append(testStepOperation, ts.getTestStepOperation())
                    .append(parameter, ts.getParameter()).isEquals());
        }
        return equals;
    }
}
