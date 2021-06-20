package com.example.accounts.api

import com.example.accounts.TransactionFixtures
import com.example.accounts.service.TransactionService
import spock.lang.Specification

class TransactionControllerSpec extends Specification implements TransactionFixtures {

    def transactionService = Mock(TransactionService)

    def transactionController = new TransactionController(transactionService)

    def 'should return transactions for given filter values'() {
        given:
        def filter = newTransactionFilter([customerNumber: 777777777, accountNumber: 123456789])

        when:
        def response = transactionController.getCustomerTransactions(filter)

        then:
        response

        interaction {
            transactionService.findTransactions(filter) >> [newTransactionResponse()]
        }

    }
}
