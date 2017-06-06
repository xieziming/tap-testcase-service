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

    @Override
    @Cacheable(value = "test_case", key = "#uid")
    public TestCase findOne(String uid) {
        return testCaseRepository.findOne(uid);
    }

    @Override
    @Cacheable(value = "test_case_search", key = "'findAll'")
    public Iterable<TestCase> findAll() {
        return testCaseRepository.findAll();
    }

    @Override
    @Cacheable(value = "test_case_search", key = "'name_'+#name")
    public List<TestCase> withName(String name) {
        return testCaseRepository.findByName(name);
    }

    @Override
    @Cacheable(value = "test_case_search", key = "'path_'+#path")
    public List<TestCase> withPath(String path) {
        return testCaseRepository.findByPath(path);
    }

    @Override
    @Cacheable(value = "test_case_search", key = "'status_'+#status")
    public List<TestCase> withStatus(String status) {
        return testCaseRepository.findByStatus(status);
    }

    @Override
    @Cacheable(value = "test_case_search", key = "'name_contains_'+#name")
    public List<TestCase> containsName(String name) {
        return testCaseRepository.findByNameContaining(name);
    }

    @Override
    @Cacheable(value = "test_case_search", key = "'description_contains_'+#status")
    public List<TestCase> containsDescription(String description) {
        return testCaseRepository.findByDescriptionContaining(description);
    }
}
