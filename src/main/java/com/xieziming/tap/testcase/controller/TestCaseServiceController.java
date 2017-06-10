/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.controller;

import com.xieziming.tap.testcase.model.TestCase;
import com.xieziming.tap.testcase.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Suny on 5/31/17.
 */
@RestController
@RequestMapping("/testCases")
public class TestCaseServiceController {
    @Autowired
    TestCaseService testCaseService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<TestCase> findAll(){
        return testCaseService.findAll();
    }

    @RequestMapping(value = "", method = {RequestMethod.PUT, RequestMethod.POST})
    public TestCase save(@RequestBody TestCase testCase){
        return testCaseService.save(testCase);
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public TestCase find(@PathVariable String uid){
        return testCaseService.findOne(uid);
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String uid){
        testCaseService.delete(uid);
    }

    @RequestMapping(value = "search/path", method = RequestMethod.POST)
    public List<TestCase> withPath(String path){
        return testCaseService.withPath(path);
    }

    @RequestMapping(value = "search/name", method = RequestMethod.POST)
    public List<TestCase> withName(String name){
        return testCaseService.withName(name);
    }

    @RequestMapping(value = "search/status/{status}", method = RequestMethod.GET)
    public List<TestCase> withStatus(@PathVariable String status){
        return testCaseService.withStatus(status);
    }

    @RequestMapping(value = "search/name/contains", method = RequestMethod.POST)
    public List<TestCase> containsName(String name){
        return testCaseService.containsName(name);
    }

    @RequestMapping(value = "search/description/contains", method = RequestMethod.POST)
    public List<TestCase> containsDescription(String description){
        return testCaseService.containsDescription(description);
    }

    @RequestMapping(value = "/subPaths", method = RequestMethod.POST)
    public List<String> findSubPaths(@RequestParam String path){
        return testCaseService.findSubPaths(path);
    }
}
