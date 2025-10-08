package cs544.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.bank.domain.Account;

public interface AccountDaoJpa extends JpaRepository<Account, Long>  {
    Account findByCustomerName(String name);
}
