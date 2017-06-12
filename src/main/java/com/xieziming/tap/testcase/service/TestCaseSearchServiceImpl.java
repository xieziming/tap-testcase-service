/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.service;

import com.xieziming.tap.testcase.dto.TestCasePath;
import com.xieziming.tap.testcase.dto.TestCasePathSearchResult;
import com.xieziming.tap.testcase.model.TestCase;
import com.xieziming.tap.testcase.repository.TestCaseRepository;
import com.xieziming.tap.testcase.search.SearchCondition;
import com.xieziming.tap.testcase.search.TestCaseSearchUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Suny on 6/8/17.
 */
@Service
public class TestCaseSearchServiceImpl implements TestCaseSearchService {
    @Autowired
    TestCaseRepository testCaseRepository;

    @Override
    @Cacheable(value = "test_case_search", key = "'find_all'")
    public Iterable<TestCase> findAll() {
        return testCaseRepository.findAll();
    }


    @Override
    @Cacheable(value = "test_case_search")
    public List<TestCase> findByConditions(SearchCondition searchCondition) throws Exception {
        List<TestCase> testCases = findByCondition(searchCondition);

        if(searchCondition.getOtherCondition() != null){
            List<TestCase> otherTestCases = findByConditions(searchCondition.getOtherCondition());
            if("And".equalsIgnoreCase(searchCondition.getAndOr())){
                return TestCaseSearchUtil.testCasesAnd(testCases, otherTestCases);
            }else if("Or".equalsIgnoreCase(searchCondition.getAndOr())){
                return TestCaseSearchUtil.testCasesOr(testCases, otherTestCases);
            }else {
                throw new Exception("bad condition relation, only 'And' or 'Or' allowed!");
            }
        }

        return testCases;
    }

    @Override
    @Cacheable(value = "test_case_search", key = "'dir_path_'+#path")
    public TestCasePathSearchResult retrievePath(String path) {
        if(path.endsWith("/")) path = StringUtils.chop(path);
        TestCasePathSearchResult testCasePathSearchResult = new TestCasePathSearchResult();
        testCasePathSearchResult.setParentTestCasePath(getParentPath(path));
        testCasePathSearchResult.setTestCasePaths(getSubPaths(path));
        testCasePathSearchResult.setTestCases(testCaseRepository.findByPath(path));
        return testCasePathSearchResult;
    }

    private String getParentPath(String path){
        if(path.endsWith("/")) path = StringUtils.chop(path);
        int lastFound = path.lastIndexOf("/");
        if(lastFound == -1) return null;
        if(lastFound == 0) return "/";
        return path.substring(0, lastFound);
    }

    @Cacheable(value = "test_case_search", key = "'dir_sub_path_'+#path")
    private List<TestCasePath> getSubPaths(String searchPath){
        if(searchPath.endsWith("/")) searchPath = StringUtils.chop(searchPath);
        List<String> allPaths = testCaseRepository.findAllPaths();
        List<TestCasePath> testCasePaths = new ArrayList<>();

        Set<String> uniquePaths = new HashSet<>();
        for(String thePath : allPaths){
            if(thePath.startsWith(searchPath)){
                String[] subPaths = thePath.replace(searchPath, "").split("/");
                if(subPaths.length > 1) {
                    String realPath = searchPath + "/" + subPaths[1];
                    uniquePaths.add(realPath);
                }
            }
        }

        for(String path : uniquePaths){
            Integer testCaseCount = totalCountUnderPath(path);
            testCasePaths.add(new TestCasePath(path, testCaseCount));
        }
        return testCasePaths;
    }

    @Override
    @Cacheable(value = "test_case_search", key = "'count_by_path_'+#path")
    public Integer totalCountUnderPath(String path) {
        return testCaseRepository.countByPathStartingWith(path);
    }

    private List<TestCase> findByCondition(SearchCondition searchCondition){
        Assert.notNull(searchCondition, "search condition should not null");

        String condition = searchCondition.getCondition();
        String value = searchCondition.getValue();

        if(condition == null || condition.isEmpty()) return null;
        if("Status".equalsIgnoreCase(condition)){
            return testCaseRepository.findByStatus(value);
        }else if("Name".equalsIgnoreCase(condition)){
            return testCaseRepository.findByName(value);
        }else if("Name_Contains".equalsIgnoreCase(condition)){
            return testCaseRepository.findByNameContaining(value);
        }else if("Path".equalsIgnoreCase(condition)){
            return testCaseRepository.findByPath(value);
        }else if("Path_Starting_With".equalsIgnoreCase(condition)){
            return testCaseRepository.findByPathStartingWith(value);
        }else if("Description_Contains".equalsIgnoreCase(condition)){
            return testCaseRepository.findByDescriptionContaining(value);
        }
        return null;
    }
}
