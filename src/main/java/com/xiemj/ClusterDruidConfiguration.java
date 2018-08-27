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
    private String clusterdbUrl;
    @Value("${spring.datasource.cluster.username}")
    private String clusterusername;
    @Value("${spring.datasource.cluster.password}")
    private String clusterpassword;
    @Value("${spring.datasource.cluster.driverClassName}")
    private String clusterdriverClassName;
    @Value("${spring.datasource.cluster.initialSize}")
    private int clusterinitialSize;
    @Value("${spring.datasource.cluster.minIdle}")
    private int clusterminIdle;
    @Value("${spring.datasource.cluster.maxActive}")
    private int clustermaxActive;
    @Value("${spring.datasource.cluster.maxWait}")
    private int clustermaxWait;
    @Value("${spring.datasource.cluster.timeBetweenEvictionRunsMillis}")
    private int clustertimeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.cluster.minEvictableIdleTimeMillis}")
    private int clusterminEvictableIdleTimeMillis;
    @Value("${spring.datasource.cluster.validationQuery}")
    private String clustervalidationQuery;
    @Value("${spring.datasource.cluster.testWhileIdle}")
    private boolean clustertestWhileIdle;
    @Value("${spring.datasource.cluster.testOnBorrow}")
    private boolean clustertestOnBorrow;
    @Value("${spring.datasource.cluster.testOnReturn}")
    private boolean clustertestOnReturn;
    @Value("${spring.datasource.cluster.poolPreparedStatements}")
    private boolean clusterpoolPreparedStatements;
    @Value("${spring.datasource.cluster.maxPoolPreparedStatementPerConnectionSize}")
    private int clustermaxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.cluster.filters}")
    private String clusterfilters;
    @Value("${spring.datasource.cluster.connectionProperties}")
    private String clusterconnectionProperties;
    @Value("${spring.datasource.cluster.useGlobalDataSourceStat}")
    private boolean clusteruseGlobalDataSourceStat;
    //声明其为Bean实例
    @Bean(name = "clusterDataSource")
    public DataSource clusterDataSource(){
        DruidDataSource clusterDatasource = new DruidDataSource();
        clusterDatasource.setUrl(this.clusterdbUrl);
        clusterDatasource.setUsername(clusterusername);
        clusterDatasource.setPassword(clusterpassword);
        clusterDatasource.setDriverClassName(clusterdriverClassName);
        //configuration
        clusterDatasource.setInitialSize(clusterinitialSize);
        clusterDatasource.setMinIdle(clusterminIdle);
        clusterDatasource.setMaxActive(clustermaxActive);
        clusterDatasource.setMaxWait(clustermaxWait);
        clusterDatasource.setTimeBetweenEvictionRunsMillis(clustertimeBetweenEvictionRunsMillis);
        clusterDatasource.setMinEvictableIdleTimeMillis(clusterminEvictableIdleTimeMillis);
        clusterDatasource.setValidationQuery(clustervalidationQuery);
        clusterDatasource.setTestWhileIdle(clustertestWhileIdle);
        clusterDatasource.setTestOnBorrow(clustertestOnBorrow);
        clusterDatasource.setTestOnReturn(clustertestOnReturn);
        clusterDatasource.setPoolPreparedStatements(clusterpoolPreparedStatements);
        clusterDatasource.setMaxPoolPreparedStatementPerConnectionSize(clustermaxPoolPreparedStatementPerConnectionSize);
        clusterDatasource.setUseGlobalDataSourceStat(clusteruseGlobalDataSourceStat);
        try {
            clusterDatasource.setFilters(clusterfilters);
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter: "+ e);
        }
        clusterDatasource.setConnectionProperties(clusterconnectionProperties);
        return clusterDatasource;
    }


    @Bean(name = "clusterTransactionManager")
    @Primary
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }


 /*   @Bean
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
    }*/

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
