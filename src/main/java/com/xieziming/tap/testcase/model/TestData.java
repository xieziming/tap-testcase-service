/*
 * Copyright (c) 2016. SUNY XIE, All rights reserved.
 * Inbox@xieziming.com
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
@Table(name="test_data")
public class TestData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    private TestCase testCase;

    @OneToOne(cascade = CascadeType.PERSIST)
    private TestDataDefinition testDataDefinition;

    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", testCase=" + testCase.getUid() +
                ", testDataDefinition=" + testDataDefinition +
                '}';
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && TestData.class.isAssignableFrom(o.getClass())){
            TestData td = (TestData) o;
            equals = (new EqualsBuilder()
                    .append(testCase.getUid(), td.getTestCase().getUid())
                    .append(testDataDefinition, td.testDataDefinition)
                    .isEquals());
        }
        return equals;
    }
}
