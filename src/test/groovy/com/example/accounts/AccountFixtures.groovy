package com.example.accounts

import com.example.accounts.api.model.AccountResponse
import com.example.accounts.service.domain.Account

import java.time.LocalDate

trait AccountFixtures {

    def newAccount(Map options = [:]) {

        def data = [
                accountName   : 'Savings123AU',
                customerNumber: 123456789,
                accountType   : 'Savings',
                currency      : 'AUD',
                balanceDate   : LocalDate.of(2021, 06, 19),
                openingBalance: 10250.00
        ] << options
    }

    Account newDomainAccount(Map options = [:]) {
        def data = newAccount() << options
        new Account(data)
    }

    AccountResponse newAccountResponse(Map options = [:]) {
        def data = newAccount() << options
        new AccountResponse(data)
    }

    def listOfDomainAccounts() {
        def accounts = [newDomainAccount(),
                        newDomainAccount([accountName: 'Caption America']),
                        newDomainAccount([accountName: 'Black Widow', accountType: 'Cheque']),
                        newDomainAccount([accountName: 'Black Panther', accountType: 'Cheque', currency: 'USD']),
                        newDomainAccount([accountName: 'Thor', currency: 'SGD'])]
    }

    def listOfAccountResponses() {
        def accounts = [newAccountResponse(),
                        newAccountResponse([accountName: 'Caption America']),
                        newAccountResponse([accountName: 'Black Widow', accountType: 'Cheque']),
                        newAccountResponse([accountName: 'Black Panther', accountType: 'Cheque', currency: 'USD']),
                        newAccountResponse([accountName: 'Thor', currency: 'SGD'])]
    }

}