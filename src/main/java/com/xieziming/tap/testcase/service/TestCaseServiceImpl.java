/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.service;

import com.xieziming.tap.testcase.model.TestCase;
import com.xieziming.tap.testcase.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 5/31/17.
 */
@Service
public class TestCaseServiceImpl implements TestCaseService {
    @Autowired
    TestCaseRepository testCaseRepository;

    @Override
    @CachePut(value = "test_case", key = "#testCase.uid")
    public TestCase save(TestCase testCase) {
        TestCase executionSaved = testCaseRepository.save(testCase);
        return executionSaved;
    }

    @Override
    @CacheEvict(value = "test_case", key = "#uid")
    public void delete(String uid){
        testCaseRepository.delete(uid);
    }
}
