/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */

package com.xieziming.tap.testcase.search;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Created by Suny on 6/8/17.
 */
@Data
public class SearchCondition {
    private String condition;
    private String value;

    private String andOr;
    private SearchCondition otherCondition;

    @Override
    public String toString() {
        return "SearchCondition{" +
                "condition=" + condition +
                ", value=" + value +
                ", andOr=" + andOr +
                ", otherCondition=" + otherCondition +
                "}";
    }

    @Override
    public boolean equals(Object o){
        boolean equals = false;
        if(o != null && SearchCondition.class.isAssignableFrom(o.getClass())){
            SearchCondition sc = (SearchCondition) o;
            equals = (new EqualsBuilder()
                    .append(condition, sc.getCondition())
                    .append(value, sc.getValue())
                    .append(andOr, sc.getAndOr())
                    .append(otherCondition, sc.getOtherCondition())
                    .isEquals());
        }
        return equals;
    }
}
