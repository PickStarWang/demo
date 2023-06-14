package com.yht.page;

public class Page {

    public enum Sort {

        asc("asc"),
        desc("desc");

        Sort(String code) {
            this.code = code;
        }

        private String code;

        public String getCode() {
            return code;
        }

    }

    public enum Trend {

        prev("prev"),
        next("next");

        Trend(String code) {
            this.code = code;
        }

        private String code;

        public String getCode() {
            return code;
        }

    }

    //页码
    private int pageNo = 1;

    //分页ID
    private long pageLastId = 0;

    //每页数量
    private int pageSize = 20;

    //排序
    private String pageSort = Sort.desc.getCode();

    //方向
    private String pageTrend = Trend.next.getCode();

    //默认构造
    public Page() {
    }

    //页码构造
    public Page(final int pageNo, final int pageSize) {
        if (pageNo < 1) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }
        if (pageSize < 0) {
            this.pageSize = 0;
        } else {
            this.pageSize = pageSize;
        }
    }

    public Page(final int pageNo, final int pageSize, final Sort sort) {
        if (pageNo < 1) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }
        if (pageSize < 0) {
            this.pageSize = 0;
        } else {
            this.pageSize = pageSize;
        }
        this.pageSort = sort.getCode();
    }

    //分页ID构造
    public Page(final long pageLastId, final int pageSize) {
        this.pageLastId = pageLastId;
        if (pageSize < 0) {
            this.pageSize = 0;
        } else {
            this.pageSize = pageSize;
        }
    }

    public Page(final long pageLastId, final int pageSize, final Trend trend) {
        this.pageLastId = pageLastId;
        if (pageSize < 0) {
            this.pageSize = 0;
        } else {
            this.pageSize = pageSize;
        }
        this.pageTrend = trend.getCode();
    }

    //获得当前页的页号,序号从1开始,默认为1.
    public int getPageNo() {
        return pageNo;
    }

    //获得每页的记录数量
    public int getPageSize() {
        return pageSize;
    }

    public long getPageLastId() {
        return pageLastId;
    }

    //根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
    public int getStart() {
        return ((pageNo - 1) * pageSize);
    }

    //获取每页结果集的数量.
    public int getLimit() {
        return pageSize;
    }

    //获取排序方式
    public String getSort() {
        return pageSort;
    }

    //获取方向
    public String getTrend() {
        return pageTrend;
    }

}
