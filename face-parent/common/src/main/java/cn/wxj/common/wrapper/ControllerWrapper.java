package cn.wxj.common.wrapper;

import cn.wxj.common.bean.BaseVo;
import cn.wxj.common.enumeration.ExceptionType;
import cn.wxj.common.exception.TceException;
import cn.wxj.common.util.CollectionUtils;
import com.baomidou.mybatisplus.activerecord.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * VO 转换类的控制器
 *
 * @ClassName ControllerWrapper
 * @author wxjason
 * @Date: 2018/8/16
 * @Description:
 **/
@Component
public class ControllerWrapper implements InitializingBean, ApplicationContextAware {
    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(ControllerWrapper.class);

    private static final Map<String, BaseWrapper> WRAPPERS = new ConcurrentHashMap<>();

    private ApplicationContext ctx;

    @Override
    public void afterPropertiesSet() {
        final Map<String, BaseWrapper> handlerMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(ctx, BaseWrapper.class, true, true);
        handlerMap.values().forEach(p -> WRAPPERS.put(generateKey(p.getModelClass(), p.getDataClass()), p));
        logger.info("加载 VO 转化器完成,共有{}个", WRAPPERS.size());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public <T extends Model, F extends BaseVo> List<F> warp(List<T> model, Class<F> dataClass) {
        List<F> data = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(model)) {
            model.forEach(t -> {
                try {
                    data.add(warp(t, dataClass));
                } catch (IOException e) {
                    throw new TceException(ExceptionType.SERVER_ERROR);
                }
            });
        }
        return data;
    }

    public <T extends Model, F extends BaseVo> F warp(T model, Class<F> dataClass) throws IOException {
        BaseWrapper baseWrapper = WRAPPERS.get(generateKey(model.getClass(), dataClass));
        if (baseWrapper == null) {
            throw new RuntimeException("没有找到 " + model.getClass().getName() + " 的 VO 转化器" + generateKey(model.getClass(), dataClass));
        }
        return (F) baseWrapper.warp(model);
    }

    private String generateKey(Class<?> modelClass, Class<?> dataClass) {
        return modelClass.getName() + "_" + dataClass.getName();
    }
}
