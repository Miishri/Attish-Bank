package org.bank.branch.attish.respositories;

import org.bank.branch.attish.models.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long> {}
