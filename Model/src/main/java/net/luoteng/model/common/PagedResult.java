/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.luoteng.model.common;

import java.util.List;
import net.luoteng.model.AbstractObject;

/**
 * 分页模型
 * 
 * @author suetming <suetming.ma at gmail.com>
 * Copyright(c) @2016 Luoteng Company, Inc.  All Rights Reserved.
 * @param <T> 
 */
public class PagedResult<T> extends AbstractObject {

    private List<T> results;

    private long totalSize;

    public PagedResult() {
    }

    public PagedResult(List<T> results, long totalSize) {
        this.results = results;
        this.totalSize = totalSize;
    }

    public List<T> getResults() {
        return results;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }
    
}
