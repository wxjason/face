package cn.wxj.system.service;

import cn.wxj.system.entity.ExcelTextTemp;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * Excel导入过渡表 服务类
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-16
 */
public interface IExcelTextTempService extends IService<ExcelTextTemp> {

    /**
     * 根据导入Excel的id获取json数据
     * @param id
     * @return String
     */
    String getExcelText(String id);

    /**
     * 清除数据库缓存的前天以前Excel导入的数据
     */
    void clearExcelText();

    /**
     * 存Excel缓存信息
     * @param text
     * @return
     */
    String saveExcelText(String text);
}
