package com.tce.generator;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: GeneratorApplication
 * @Package com.tce.generator
 * @Description:
 * @Author wuxinjian
 * @Date 2018/12/21 16:10
 * @Version V1.0
 */
@SpringBootApplication
public class GeneratorApplication {


    /**
     * 作者
     */
    private static final String AUTHOR = "wxjason";

    /**
     * 数据库驱动类
     */
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

    /**
     * 数据库连接url
     */
    private static final String URL = "jdbc:mysql://192.168.2.207:3306/face?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useSSL=false";

    /**
     * 数据库连接用户名
     */
    private static final String USER = "root";

    /**
     * 数据库连接密码
     */
    private static final String PASSWORD = "Tce123456";


    /**
     * 生成类所在相对路径
     */
    private static final String OUTPUT_DIR = "H:/git/face/face-parent/face-admin/src/main/java";

    /**
     * 生成类的包名
     */
    private static final String PACKAGE_NAME = "cn.wxj.face.admin";

    /**
     * 表前缀
     */
    private static final String[] TABLE_PREFIX = {"ad_"};

    /**
     * 需生成的表名
     */
    private static final String[] TABLES = {"ad_device"};

    public static void main(String[] args) {

        // 数据库连接配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(URL)
                .setUsername(USER)
                .setPassword(PASSWORD)
                .setDriverName(DRIVER_NAME);

        // 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)
                .setEnableCache(false)
                .setAuthor(AUTHOR)
                .setOutputDir(OUTPUT_DIR)
                .setFileOverride(true)
                .setIdType(IdType.UUID)
                .setBaseColumnList(true)
                .setBaseResultMap(true);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setEntityColumnConstant(true)
                .setEntityBuilderModel(true)
                .setLogicDeleteFieldName("del")
                .entityTableFieldAnnotationEnable(true)
                .setRestControllerStyle(true)
                .setRestControllerStyle(true)
                .setSuperControllerClass("cn.wxj.controller.BaseController");
        if (TABLE_PREFIX.length != 0) {
            strategyConfig.setTablePrefix(TABLE_PREFIX);
        }
        if (TABLES.length != 0) {
            strategyConfig.setInclude(TABLES);
        }

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PACKAGE_NAME);
        packageConfig.setController("controller");
        packageConfig.setXml("mapper.mapping");

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
    }
}
