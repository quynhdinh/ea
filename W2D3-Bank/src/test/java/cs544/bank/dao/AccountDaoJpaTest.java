package cs544.bank.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import cs544.bank.TestJpaConfig;
import cs544.bank.domain.Account;
import cs544.bank.domain.Customer;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;  

@DataJpaTest
@ContextConfiguration(classes = TestJpaConfig.class)
public class AccountDaoJpaTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AccountDaoJpa accountDao;

    // @Test
    public void testFindByCustomerName() {
        Customer customer1 = new Customer("John Doe");
        Account account1 = new Account(12345);
        Account account2 = new Account(67890);
        account1.setCustomer(customer1);
        entityManager.persist(account1);
        entityManager.persist(account2);
        Account byCustomerName = accountDao.findByCustomerName("John Doe");
        assertThat(byCustomerName, is(notNullValue()));
        assertThat(byCustomerName.getCustomer().getName(), is("John Doe"));
    }
}
