/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.service;

import com.xieziming.tap.testcase.model.TestCase;
import com.xieziming.tap.testcase.repository.TestCaseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by Suny on 5/31/17.
 */
@Service
public class TestCaseServiceImpl implements TestCaseService {
    @Autowired
    TestCaseRepository testCaseRepository;

    @Override
    @Cacheable(value = "test_case", key = "#uid")
    public TestCase findOne(String uid) {
        return testCaseRepository.findOne(uid);
    }

    @Override
    @CachePut(value = "test_case", key = "#testCase.uid")
    public TestCase save(TestCase testCase) {

        String path = testCase.getPath();
        if(path.endsWith("/")) testCase.setPath(StringUtils.chop(path));

        TestCase executionSaved = testCaseRepository.save(testCase);
        return executionSaved;
    }

    @Override
    @CacheEvict(value = "test_case", key = "#uid")
    public void delete(String uid){
        testCaseRepository.delete(uid);
    }
}
