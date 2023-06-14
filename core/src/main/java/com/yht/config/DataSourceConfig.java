package com.yht.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;
import jakarta.servlet.Filter;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.yht.dao", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfig {
    @Value("${spring.datasource.type}")
    private String dbType;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String passWord;
    @Value("${spring.datasource.initialSize}")
    private Integer initialSize;
    @Value("${spring.datasource.minIdle}")
    private Integer minIdle;
    @Value("${spring.datasource.maxActive}")
    private Integer maxActive;
    @Value("${spring.datasource.maxWait}")
    private Integer maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.filters}")
    private String filters;
    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;
    @Value("${pagehelper.helperDialect}")
    private String helperDialect;
    @Value("${pagehelper.reasonable}")
    private String reasonable;
    @Value("${pagehelper.supportMethodsArguments}")
    private String supportMethodsArguments;
    @Value("${pagehelper.params}")
    private String params;
    @Value("${spring.datasource.removeAbandoned}")
    private boolean removeAbandoned;
    @Value("${spring.datasource.removeAbandonedTimeout}")
    private Integer removeAbandonedTimeout;
    @Value("${spring.datasource.logAbandoned}")
    private boolean logAbandoned;
//    @Value("${spring.datasource.connectionInitSqls}")
//    private String connectionInitSqls;


    @Bean(name = "druidDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {

        DruidDataSource druidDataSource = new DruidDataSource();
        try{
            druidDataSource.setUrl(this.dbUrl);
            druidDataSource.setUsername(this.userName);
            druidDataSource.setPassword(this.passWord);
            druidDataSource.setConnectionProperties(connectionProperties);
            druidDataSource.setDriverClassName(this.driverClassName);
            druidDataSource.setInitialSize(this.initialSize);
            druidDataSource.setMinIdle(this.minIdle);
            druidDataSource.setMaxActive(this.maxActive);
            druidDataSource.setMaxWait(this.maxWait);
            druidDataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
            druidDataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
            druidDataSource.setValidationQuery(this.validationQuery);
            druidDataSource.setTestWhileIdle(this.testWhileIdle);
            druidDataSource.setTestOnBorrow(this.testOnBorrow);
            druidDataSource.setTestOnReturn(this.testOnReturn);
            druidDataSource.setPoolPreparedStatements(this.poolPreparedStatements);
            druidDataSource.setRemoveAbandoned(this.removeAbandoned);
            druidDataSource.setRemoveAbandonedTimeout(this.removeAbandonedTimeout);
            druidDataSource.setLogAbandoned(this.logAbandoned);
            druidDataSource.setFilters(filters);
//            StringTokenizer tokenizer = new StringTokenizer(connectionInitSqls, ";");
//            druidDataSource.setConnectionInitSqls(Collections.list(tokenizer));
            druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
        }catch (Exception e){
        }
        return druidDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("druidDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", reasonable);
        properties.setProperty("supportMethodsArguments", supportMethodsArguments);
        properties.setProperty("params", params);
        properties.setProperty("helperDialect",helperDialect);
        pageHelper.setProperties(properties);
        bean.setPlugins(new Interceptor[]{(Interceptor) pageHelper});
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapping/*.xml"));
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
        return bean.getObject();
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("druidDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * Druid监控
     */
//    @Bean
//    public ServletRegistrationBean statViewServlet(){
//        //创建servlet注册实体
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
//        //设置ip白名单
//        //servletRegistrationBean.addInitParameter("allow","127.0.0.1");
//        //设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
//        //servletRegistrationBean.addInitParameter("deny","192.168.0.19");
//        //设置控制台管理用户
//        servletRegistrationBean.addInitParameter("loginUsername","honeytrip");
//        servletRegistrationBean.addInitParameter("loginPassword","honeytrip");
//        //是否可以重置数据
//        servletRegistrationBean.addInitParameter("resetEnable","false");
//        return servletRegistrationBean;
//    }

    /**
     * druid 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean statFilter(){
        //创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean((Filter) new WebStatFilter());
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

//    @Bean
//    public DruidStatInterceptor druidStatInterceptor() {
//        DruidStatInterceptor dsInterceptor = new DruidStatInterceptor();
//        return dsInterceptor;
//    }
//    @Bean
//    @Scope("prototype")
//    public JdkRegexpMethodPointcut druidStatPointcut() {
//        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
//        pointcut.setPatterns("com.honey.trip.mybatis.mapping.*","com.honey.trip.service.*");
//        return pointcut;
//    }
//    @Bean
//    public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
//        DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
//        defaultPointAdvisor.setPointcut(druidStatPointcut);
//        defaultPointAdvisor.setAdvice(druidStatInterceptor);
//        return defaultPointAdvisor;
//    }
}
