package com.example.postservice.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.ReactiveTransactionManager;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

import com.example.postservice.utils.R2DBCURLSplitter;



@Configuration
@EnableR2dbcRepositories
class DatabaseConfiguration extends AbstractR2dbcConfiguration {

    @Value("${spring.r2dbc.username}")
    String username;

    @Value("${spring.r2dbc.password}")
    String password;

    @Value("${spring.r2dbc.max-size}")
    String maxSize;

    @Value("${spring.r2dbc.url}")
    String url;

    private static final String DB_DRIVER = "pool";

    @Bean
    public ConnectionFactory connectionFactory() {
        R2DBCURLSplitter myUrl = new R2DBCURLSplitter(url);
        System.out.println(myUrl.toString());
        String driverName = myUrl.getDriverName();
        String host = myUrl.getHost();
        Integer port = myUrl.getPort();
        String database = myUrl.getDatabase();

        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, DB_DRIVER)
                .option(PROTOCOL, driverName)
                .option(MAX_SIZE, Integer.valueOf(maxSize))
                .option(HOST, host)
                .option(PORT, port)
                .option(USER, username)
                .option(PASSWORD, password)
                .option(DATABASE, database)
                .build());
    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource("schema.sql"));
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}