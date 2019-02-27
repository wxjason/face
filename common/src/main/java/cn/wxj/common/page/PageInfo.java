package cn.wxj.common.page;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * 分页结果的封装(for Bootstrap Table)
 *
 * @author fengshuonan
 * @Date 2017年1月22日 下午11:06:41
 */
public class PageInfo<T> {

    // 结果集
    private List<T> rows;

    // 总数
    private long total;

    public PageInfo(Page<T> page) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
    }
    public PageInfo(long total, List<T> rows) {
        this.rows = rows;
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "rows=" + rows +
                ", total=" + total +
                '}';
    }
}
