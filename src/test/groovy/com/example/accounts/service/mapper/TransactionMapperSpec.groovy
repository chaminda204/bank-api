package com.example.accounts.service.mapper

import com.example.accounts.TransactionFixtures
import com.example.accounts.api.model.TransactionResponse
import org.mapstruct.factory.Mappers
import spock.lang.Specification

class TransactionMapperSpec extends Specification implements TransactionFixtures {

    def transactionMapper = Mappers.getMapper(TransactionMapper.class)

    def 'should map list of Transactions into a list of TransactionResponse'() {
        given:
        def transactionList = [newDomainTransaction()]

        when:
        def response = transactionMapper.map(transactionList)

        then:
        response
        response[0] instanceof TransactionResponse
        response[0].customerNumber == transactionList[0].customerNumber
        response[0].accountNumber == transactionList[0].accountNumber
        response[0].transactionId == transactionList[0].transactionId
        response[0].date == transactionList[0].date
        response[0].credit == transactionList[0].credit
        response[0].debit == transactionList[0].debit
        response[0].operation == transactionList[0].operation
        response[0].transactionNarrative == transactionList[0].transactionNarrative
    }
}
