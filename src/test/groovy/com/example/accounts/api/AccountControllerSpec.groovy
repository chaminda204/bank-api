package com.example.accounts.api

import com.example.accounts.AccountFixtures
import com.example.accounts.service.AccountService
import spock.lang.Specification

class AccountControllerSpec extends Specification implements AccountFixtures {

    def accountService = Mock(AccountService)

    def accountController = new AccountController(accountService)

    def 'should return list of accounts for given customer'() {
        given:
        def accountId = '12345678'
        when:
        def response = accountController.getByAccountId(accountId)

        then:
        response

        interaction {
            accountService.findAccountsByCustomerNumber(accountId) >> [newAccountResponse()]
        }

    }
}
