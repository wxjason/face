package cn.wxj.system.service.impl;

import cn.wxj.common.constant.NumberConstants;
import cn.wxj.common.util.DateUtils;
import cn.wxj.common.util.ExcelUtils;
import cn.wxj.common.util.StringUtils;
import cn.wxj.common.util.UUIDUtils;
import cn.wxj.system.entity.ExcelTextTemp;
import cn.wxj.system.mapper.ExcelTextTempMapper;
import cn.wxj.system.service.IExcelTextTempService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Excel导入过渡表 服务实现类
 * </p>
 *
 * @author wxjason123
 * @since 2019-01-16
 */
@Service
public class ExcelTextTempServiceImpl extends ServiceImpl<ExcelTextTempMapper, ExcelTextTemp> implements IExcelTextTempService {

    @Override
    public String getExcelText(String id) {
        ExcelTextTemp excelTextTemp = this.selectById(id);
        String text = StringUtils.EMPTY;
        if (Objects.nonNull(excelTextTemp)) {
            text = excelTextTemp.getText();
        }
        return text;
    }

    @Override
    public void clearExcelText() {
        String now = DateUtils.getDateTimeAsString(DateUtils.lastDay(NumberConstants.ONE));
        List<ExcelTextTemp> temps = this.baseMapper.selectBeforeDate(now);
        this.deleteBatchIds(temps);
    }

    @Override
    public String saveExcelText(String text) {
        String id = UUIDUtils.create() + ExcelUtils.OFFICE_EXCEL_2007_POSTFIX;
        ExcelTextTemp excelTextTemp = new ExcelTextTemp();
        excelTextTemp.setId(id);
        excelTextTemp.setText(text);
        excelTextTemp.setCreateTime(DateUtils.localDateTime());
        this.insert(excelTextTemp);
        return id;
    }
}
