package cs544.bank;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "cs544.bank.dao")
@EntityScan(basePackages = "cs544.bank.domain")
public class TestJpaConfig {
    
}