package cn.wxj.common.util;

/**
 * @ClassName ThreadUtils
 * @Author: WangJinbo
 * @Date: 2018/11/9 15:44
 * @Description:
 **/
public class ThreadUtils {
    public static void sleep(Integer mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
