package com.xieziming.tap.testcase.service;

import com.xieziming.tap.testcase.dto.TestCaseFolder;
import com.xieziming.tap.testcase.model.TestCase;
import com.xieziming.tap.testcase.search.SearchCondition;

import java.util.List;

/**
 * Created by Suny on 6/11/17.
 */
public interface TestCaseSearchService {
    Iterable<TestCase> findAll();
    List<TestCase> findByConditions(SearchCondition searchCondition) throws Exception;
    TestCaseFolder getTestCaseFolder(String path);
}
