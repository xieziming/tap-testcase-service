/*
 * Copyright (c) 2016. SUNY XIE, All rights reserved.
 * Inbox@xieziming.com
 */

package com.xieziming.tap.testcase.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;

/**
 * Created by Suny on 5/8/16.
 */
@Data
@Entity
@Table(name="test_case_meta")
public class TestCaseMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    private TestCase testCase;

    @Column(length = 50, nullable = false)
    private String metaType;

    @Column(length = 50, nullable = false)
    private String metaKey;

    @Column(length=500)
    private String metaValue;

    @Override
    public String toString() {
        return "TestCaseMeta{" +
                "id=" + id +
                ", testCase=" + testCase.getUid() +
                ", metaType='" + metaType + '\'' +
                ", metaKey='" + metaKey + '\'' +
                ", metaValue='" + metaValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && TestCaseMeta.class.isAssignableFrom(o.getClass())){
            TestCaseMeta tcm = (TestCaseMeta) o;
            equals = (new EqualsBuilder()
                    .append(testCase.getUid(), tcm.getTestCase().getUid())
                    .append(metaType, tcm.metaType)
                    .append(metaKey, tcm.metaKey)
                    .append(metaValue, tcm.metaValue).isEquals());
        }
        return equals;
    }
}
