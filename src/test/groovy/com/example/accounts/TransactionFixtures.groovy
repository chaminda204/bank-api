package com.example.accounts

import com.example.accounts.api.model.TransactionFilter
import com.example.accounts.api.model.TransactionResponse
import com.example.accounts.service.domain.Transaction

import static java.time.LocalDate.of

trait TransactionFixtures {

    def newTransaction(Map options = [:]) {

        def data = [
                customerNumber      : 76545654,
                accountNumber       : 1123456789,
                date                : of(2020, 06, 19),
                credit              : 1300.00,
                debit               : null,
                operation           : 'credit',
                transactionNarrative: 'Deposit'
        ] << options
    }

    Transaction newDomainTransaction(Map options = [:]) {
        def data = newTransaction() << options
        new Transaction(data)
    }

    List<Transaction> newDomainTransactions() {
        return [newDomainTransaction(),
                newDomainTransaction([credit: null, debit: 55.64, operation: 'debit', transactionNarrative: 'Purchases'])]
    }

    TransactionResponse newTransactionResponse(Map options = [:]) {
        def data = newTransaction() << options
        new TransactionResponse(data)
    }

    List<TransactionResponse> newTransactionResponses() {
        return [newTransactionResponse(),
                newTransactionResponse([credit: null, debit: 55.64, operation: 'debit', transactionNarrative: 'Purchases'])]
    }

    TransactionFilter newTransactionFilter(Map options = [:]) {
        def data = [customerNumber: 777777777,
                    accountNumber : 123456789] << options
        new TransactionFilter(data)
    }


}