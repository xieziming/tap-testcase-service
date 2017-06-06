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
@Table(name="test_data")
public class TestData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private TestDataDefinition testDataDefinition;

    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", testDataDefinition=" + testDataDefinition +
                "}";
    }


    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && com.xieziming.tap.testcase.model.TestData.class.isAssignableFrom(o.getClass())){
            com.xieziming.tap.testcase.model.TestData td = (com.xieziming.tap.testcase.model.TestData) o;
            equals = (new EqualsBuilder()
                    .append(testDataDefinition, td.testDataDefinition)
                    .isEquals());
        }
        return equals;
    }
}
