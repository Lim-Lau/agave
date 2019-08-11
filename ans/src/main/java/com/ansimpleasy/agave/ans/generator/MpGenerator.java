package com.ansimpleasy.agave.ans.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author LiuCan
 * @date 2019/8/6 16:52
 */
public class MpGenerator {

    /**
     * @author LiuCan
     * @date 2019/8/6 16:57
     * @param serviceNameStartWithImpl
     * @param packageName
     * @param tableNames
     * @return void
     */
    public void generatorByTables(Boolean serviceNameStartWithImpl,String packageName ,String... tableNames) {

        GlobalConfig globalConfig = new GlobalConfig();
        String dbUrl = "jdbc:mysql://localhost:3306/ans?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        dataSourceConfig.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                String t = fieldType.toLowerCase();
                //如果是datetime类型，转换成Date字段类型
                if(t.contains("datetime")){
                    return DbColumnType.DATE;
                }
                return super.processTypeConvert(globalConfig,fieldType);
            }
        });
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("Pwd123")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        globalConfig.setActiveRecord(false)
                .setAuthor("liucan")
                .setOutputDir("d:\\TestCodeGen")
                .setFileOverride(true);
        if (!serviceNameStartWithImpl) {
            globalConfig.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();

    }

    /**
     * @author LiuCan
     * @date 2019/8/7 8:40
     * @param packageName
     * @param tableNames
     * @return void
     */
    public void generateByTables(String packageName, String... tableNames) {
        generatorByTables(true, packageName, tableNames);
    }

    public static void main(String[] args) {
        MpGenerator mpGenerator = new MpGenerator();
        String packageName = "com.ansimpleasy.agave.ans";
        mpGenerator.generateByTables(packageName,"user","user_role","role","role_permission","user_permission","permission");
    }
}
