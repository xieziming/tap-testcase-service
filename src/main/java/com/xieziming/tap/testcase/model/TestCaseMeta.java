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
 * Created by Suny on 5/8/16.
 */
@Data
@Entity
@Table(name="test_case_meta", uniqueConstraints = {@UniqueConstraint(columnNames={"test_case_uid", "metaKey"})})
public class TestCaseMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String metaKey;

    @Column(length=500)
    private String metaValue;

    @Override
    public String toString() {
        return "TestCaseMeta{" +
                "id=" + id +
                ", metaKey=" + metaKey + "'" +
                ", metaValue=" + metaValue + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && TestCaseMeta.class.isAssignableFrom(o.getClass())){
            TestCaseMeta tcm = (TestCaseMeta) o;
            equals = (new EqualsBuilder()
                    .append(metaKey, tcm.getMetaKey())
                    .append(metaValue, tcm.getMetaValue()).isEquals());
        }
        return equals;
    }
}
