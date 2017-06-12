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


/**
 * Created by Suny on 5/31/17.
 */
@RestController
@RequestMapping("/testcase")
public class TestCaseServiceController {
    @Autowired
    TestCaseService testCaseService;

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
}
