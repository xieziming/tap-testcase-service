/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Suny on 5/22/16.
 */
@Data
@Entity
@Table(name="test_case")
public class TestCase {
    @Id
    @Column(length = 50)
    private String uid;

    @Column(length=100, nullable = false)
    private String path = "unclassified";

    @Column(length = 100)
    private String name;

    @Column(length=500)
    private String description;

    @Column(length=50)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreationTimestamp
    private Date createdTime;

    @OneToMany(targetEntity = TestCaseMeta.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "testCase")
    private List<TestCaseMeta> testCaseMetas;

    @OneToMany(targetEntity = TestStep.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "testCase")
    private List<TestStep> testSteps;

    @OneToMany(targetEntity = TestData.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "testCase")
    private List<TestData> testDatas;

    @Override
    public String toString() {
        return "TestCase{" +
                "uid=" + uid +
                ", path=" + path +
                ", name=" + name +
                ", description=" + description +
                ", status=" + status +
                ", createdTime=" + createdTime +
                "}";
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && com.xieziming.tap.testcase.model.TestCase.class.isAssignableFrom(o.getClass())){
            com.xieziming.tap.testcase.model.TestCase tc = (com.xieziming.tap.testcase.model.TestCase) o;
            equals = (new EqualsBuilder()
                    .append(path, tc.getPath())
                    .append(name, tc.name)
                    .append(status, tc.getStatus())
                    .append(description, tc.description)
                    .append(testCaseMetas, tc.testCaseMetas)
                    .append(testDatas, tc.testDatas)
                    .append(testSteps, tc.testSteps).isEquals());
        }
        return equals;
    }
}
