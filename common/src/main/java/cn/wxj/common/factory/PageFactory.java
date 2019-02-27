package cn.wxj.common.factory;

import cn.wxj.common.constant.NumberConstants;
import cn.wxj.common.enumeration.Order;
import cn.wxj.common.util.HttpUtils;
import cn.wxj.common.util.RegexUtils;
import cn.wxj.common.util.StringUtils;
import com.baomidou.mybatisplus.plugins.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author fengshuonan
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    private static final int DEFAULT_LIMIT = 10;

    private static final int DEFAULT_OFFSET = 0;

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpUtils.getRequest();
        String limitS = request.getParameter("limit");
        String offsetS = request.getParameter("offset");
        //每页多少条数据
        int limit = DEFAULT_LIMIT;
        //每页的偏移量(本页当前有多少条)
        int offset = DEFAULT_OFFSET;
        if (RegexUtils.matchNumber(limitS) && Integer.parseInt(limitS) != NumberConstants.ZERO) {
            limit = Integer.parseInt(limitS);
        }
        if (RegexUtils.matchNumber(offsetS)) {
            offset = Integer.parseInt(offsetS);
        }
        //排序字段名称
        String sort = request.getParameter("sort");
        //asc或desc(升序或降序)
        String order = request.getParameter("order");
        if (StringUtils.isEmpty(sort)) {
            Page<T> page = new Page<>((offset / limit + 1), limit);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>((offset / limit + 1), limit, sort);
            if (Order.ASC.getDes().equals(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }
}
