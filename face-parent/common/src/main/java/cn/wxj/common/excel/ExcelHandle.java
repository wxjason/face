package cn.wxj.common.excel;

import cn.wxj.common.bean.Count;

import java.util.List;

/**
 * @author wxjason
 */
public interface ExcelHandle {
    /**
     * 处理Excel
     * @param count
     * @param i
     * @param row
     */
    void handle(Count count, Integer i, List<Object> row);
}
