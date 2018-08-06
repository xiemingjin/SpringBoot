package com.xiemj;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "com.xiemj.dao1",sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDruidConfiguration {


    static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

    @Value("${spring.datasource.cluster.url}")
    private String dbUrl;
    @Value("${spring.datasource.cluster.username}")
    private String username;
    @Value("${spring.datasource.cluster.password}")
    private String password;
    @Value("${spring.datasource.cluster.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.cluster.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.cluster.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.cluster.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.cluster.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.cluster.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.cluster.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.cluster.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.cluster.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.cluster.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.cluster.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.cluster.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.cluster.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.cluster.filters}")
    private String filters;
    @Value("${spring.datasource.cluster.connectionProperties}")
    private String connectionProperties;
    @Value("${spring.datasource.cluster.useGlobalDataSourceStat}")
    private boolean useGlobalDataSourceStat;
    //声明其为Bean实例
    @Bean(name = "clusterDataSource")
    public DataSource clusterDataSource(){
        DruidDataSource clusterDatasource = new DruidDataSource();
        clusterDatasource.setUrl(this.dbUrl);
        clusterDatasource.setUsername(username);
        clusterDatasource.setPassword(password);
        clusterDatasource.setDriverClassName(driverClassName);
        //configuration
        clusterDatasource.setInitialSize(initialSize);
        clusterDatasource.setMinIdle(minIdle);
        clusterDatasource.setMaxActive(maxActive);
        clusterDatasource.setMaxWait(maxWait);
        clusterDatasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        clusterDatasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        clusterDatasource.setValidationQuery(validationQuery);
        clusterDatasource.setTestWhileIdle(testWhileIdle);
        clusterDatasource.setTestOnBorrow(testOnBorrow);
        clusterDatasource.setTestOnReturn(testOnReturn);
        clusterDatasource.setPoolPreparedStatements(poolPreparedStatements);
        clusterDatasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        clusterDatasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        try {
            clusterDatasource.setFilters(filters);
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter: "+ e);
        }
        clusterDatasource.setConnectionProperties(connectionProperties);
        return clusterDatasource;
    }


    @Bean(name = "clusterTransactionManager")
    @Primary
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }


    @Bean
    public ServletRegistrationBean statViewServlet(){
        //创建servlet注册实体
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //设置ip白名单
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
        servletRegistrationBean.addInitParameter("deny","192.168.0.19");
        //设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername","druid");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
        //创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ClusterDruidConfiguration.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "clusterSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("clusterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
