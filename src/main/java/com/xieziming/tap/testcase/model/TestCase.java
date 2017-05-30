/*
 * Copyright (c) 2016. SUNY XIE, All rights reserved.
 * Inbox@xieziming.com
 */

package com.xieziming.tap.testcase.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;

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
    private String uid;

    @Column(length=100, nullable = false)
    private String path = "unclassified";

    @Column(length = 100, unique = true)
    private String name;

    @Column(length=500)
    private String description;

    @Column(length=50)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Date createdTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testCase")
    private List<TestCaseRelation> testCaseRelations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testCase")
    private List<TestCaseMeta> testCaseMetas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testCase")
    private List<TestStep> testSteps;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testCase")
    private List<TestData> testDatas;

    @Override
    public String toString() {
        return "TestCase{" +
                "uid=" + uid +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && TestCase.class.isAssignableFrom(o.getClass())){
            TestCase tc = (TestCase) o;
            equals = (new EqualsBuilder()
                    .append(path, tc.getPath())
                    .append(name, tc.name)
                    .append(status, tc.getStatus())
                    .append(description, tc.description)
                    .append(testCaseRelations, tc.testCaseRelations)
                    .append(testCaseMetas, tc.testCaseMetas)
                    .append(testDatas, tc.testDatas)
                    .append(testSteps, tc.testSteps).isEquals());
        }
        return equals;
    }
}
