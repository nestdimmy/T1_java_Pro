package user_management.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

//@Configuration
public class AppConfiguration {

  //  @Bean
    public DataSource dataSource(DbProps dbProps) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbProps.getHost());
        config.setUsername(dbProps.getUser());
        config.setPassword(dbProps.getPassword());
        config.setMaximumPoolSize(10);
        return new HikariDataSource(config);
    }
}
