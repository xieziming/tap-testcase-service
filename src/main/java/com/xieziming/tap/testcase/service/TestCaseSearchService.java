package com.xieziming.tap.testcase.service;

import com.xieziming.tap.testcase.model.TestCase;
import com.xieziming.tap.testcase.search.SearchCondition;

import java.util.List;
import java.util.Set;

/**
 * Created by Suny on 6/11/17.
 */
public interface TestCaseSearchService {
    TestCase findOne(String uid);
    Iterable<TestCase> findAll();
    List<TestCase> findByConditions(SearchCondition searchCondition) throws Exception;
    Set<String> findSubPaths(String path);
}
