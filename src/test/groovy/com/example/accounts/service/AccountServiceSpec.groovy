package com.example.accounts.service

import com.example.accounts.AccountFixtures
import com.example.accounts.exceptions.ResourceNotFoundException
import com.example.accounts.repository.AccountRepository
import com.example.accounts.service.mapper.AccountMapper
import spock.lang.Specification
import spock.lang.Unroll

import static org.assertj.core.api.Assertions.assertThat

class AccountServiceSpec extends Specification implements AccountFixtures {

    def accountRepository = Mock(AccountRepository)

    def accountMapper = Mock(AccountMapper)

    def accountsService = new AccountService(accountRepository, accountMapper)

    def 'should return list of accounts for given valid customer id'() {
        given:
        def customerNumber = '123456789';

        when:
        def accountResponse = accountsService.findAccountsByCustomerNumber(customerNumber)

        then:
        assertThat(accountResponse).isNotNull()
        assertThat(accountResponse.isEmpty()).isFalse()

        and:
        interaction {
            accountRepository.findByCustomerNumber(Long.valueOf(customerNumber)) >> listOfDomainAccounts()
            accountMapper.map(_ as List) >> listOfAccountResponses()
        }

    }

    @Unroll('should throw ResourceNotFoundException when database returns #scenario')
    def 'should throw ResourceNotFoundException for non existing customer ids'() {
        given:
        def nonExistingCustomerId = '123456789';

        when:
        accountsService.findAccountsByCustomerNumber(nonExistingCustomerId)

        then:
        thrown(ResourceNotFoundException)

        and:
        interaction {
            accountRepository.findByCustomerNumber(Long.valueOf(nonExistingCustomerId)) >> returnedvalue
            0 * accountMapper.map(_ as List)
        }
        where:
        scenario      | returnedvalue
        'empty array' | []
        'null value'  | null
    }
}