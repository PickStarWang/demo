package com.yht.page;

import java.util.ArrayList;
import java.util.Collection;

public class PageResult<T> {

    private Collection<T> results = new ArrayList<>();

    private long total;

    public PageResult() {
    }

    public PageResult(Collection<T> results, long total) {
        this.results = results;
        this.total = total;
    }

    public Collection<T> getResults() {
        return results;
    }

    public void setResults(Collection<T> results) {
        this.results = results;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
