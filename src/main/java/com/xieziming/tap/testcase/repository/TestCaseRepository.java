/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xieziming.tap.testcase.repository;

import com.xieziming.tap.testcase.model.TestCase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * A repository to manage {@link TestCase} instances.
 *
 * @author Suny Xie
 */
public interface TestCaseRepository extends PagingAndSortingRepository<TestCase, String>, CrudRepository<TestCase, String> {
    List<TestCase> findByName(String name);
    List<TestCase> findByPath(String path);
    List<TestCase> findByPathStartingWith(String path);
    List<TestCase> findByStatus(String status);
    List<TestCase> findByNameContaining(String name);
    List<TestCase> findByDescriptionContaining(String description);
    Integer countByPath(String path);
    Integer countByPathStartingWith(String path);

    @Query("SELECT DISTINCT path FROM TestCase WHERE path LIKE ?1%")
    List<String> findPathStartingWith(String path);
}
