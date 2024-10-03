package org.bank.branch.attish.respositories;

import org.bank.branch.attish.models.UserTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionsRepository extends JpaRepository<UserTransactions, Long> { }
