/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.service;

import com.xieziming.tap.testcase.model.TestCase;
import com.xieziming.tap.testcase.repository.TestCaseRepository;
import com.xieziming.tap.testcase.search.SearchCondition;
import com.xieziming.tap.testcase.search.TestCaseSearchUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    @Cacheable(value = "test_case", key = "#uid")
    public TestCase findOne(String uid) {
        return testCaseRepository.findOne(uid);
    }

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
    @Cacheable(value = "test_case_search", key = "'sub_path_'+#path")
    public Set<String> findSubPaths(String path) {
        List<String> allPaths = testCaseRepository.findAllPaths();
        Set<String> subPaths = new HashSet<>();

        if(path.endsWith("/")) path = StringUtils.chop(path);

        for(String thePath : allPaths){
            if(thePath.startsWith(path)){
                String[] matched = thePath.replace(path, "").split("/");
                String sub = matched[0];

                if(matched.length > 1) sub += "/"+matched[1];

                subPaths.add(path+sub);
            }
        }
        return subPaths;
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
