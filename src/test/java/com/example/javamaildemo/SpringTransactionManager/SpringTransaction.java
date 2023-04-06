package com.example.javamaildemo.SpringTransactionManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@SpringBootTest
public class SpringTransaction {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Test
    public void d() {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);

        dataSourceTransactionManager.commit(transaction);// try
        dataSourceTransactionManager.rollback(transaction);// catch
    }
}
