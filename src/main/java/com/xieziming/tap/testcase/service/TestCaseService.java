/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.service;

import com.xieziming.tap.testcase.model.TestCase;

import java.util.List;

/**
 * Created by Suny on 5/31/17.
 */
public interface TestCaseService {
    TestCase save(TestCase testCase);
    void delete(String uid);
    TestCase findOne(String uid);
    Iterable<TestCase> findAll();
    List<TestCase> withName(String name);
    List<TestCase> withPath(String path);
    List<TestCase> withStatus(String status);
    List<TestCase> containsName(String name);
    List<TestCase> containsDescription(String description);
    List<String> findSubPaths(String path);
}
