package com.example.accounts.service.mapper

import com.example.accounts.AccountFixtures
import com.example.accounts.api.model.AccountResponse
import org.mapstruct.factory.Mappers
import spock.lang.Specification

class AccountMapperSpec extends Specification implements AccountFixtures {

    def accountMapper = Mappers.getMapper(AccountMapper.class)

    def 'should map list of Account into a list of AccountResponse'() {
        given:
        def accountList = [newDomainAccount()]

        when:
        def response = accountMapper.map(accountList)

        then:
        response
        response[0] instanceof AccountResponse
        response[0].accountName == accountList[0].accountName
        response[0].customerNumber == accountList[0].customerNumber
        response[0].accountNumber == accountList[0].accountNumber
        response[0].accountType == accountList[0].accountType
        response[0].balanceDate == accountList[0].balanceDate
        response[0].openingBalance == accountList[0].openingBalance
    }
}
