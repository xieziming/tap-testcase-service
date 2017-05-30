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
@Table(name="test_data_definition")
public class TestDataDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50, nullable=false)
    private String type;

    @Column(length = 50, nullable=false)
    private String field;

    @Column(length=500)
    private String value;

    @Column(length=500)
    private String remark;

    @Override
    public String toString() {
        return "TestDataDefinition{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", field='" + field + '\'' +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && TestDataDefinition.class.isAssignableFrom(o.getClass())){
            TestDataDefinition tdf = (TestDataDefinition) o;
            equals = (new EqualsBuilder()
                    .append(type, tdf.type)
                    .append(field, tdf.field)
                    .append(value, tdf.value)
                    .append(remark, tdf.remark).isEquals());
        }
        return equals;
    }
}
