/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.service;

import com.xieziming.tap.testcase.model.TestCase;

/**
 * Created by Suny on 5/31/17.
 */
public interface TestCaseService {
    TestCase save(TestCase testCase);
    void delete(String uid);
}
