package com.example.accounts.service

import com.example.accounts.TransactionFixtures
import com.example.accounts.exceptions.ResourceNotFoundException
import com.example.accounts.repository.TransactionRepository
import com.example.accounts.service.mapper.TransactionMapper
import spock.lang.Specification
import spock.lang.Unroll

class TransactionServiceSpec extends Specification implements TransactionFixtures {

    def transactionRepository = Mock(TransactionRepository)

    def transactionMapper = Mock(TransactionMapper)

    def transactionService = new TransactionService(transactionRepository, transactionMapper)

    def 'should return transactions for given customerNumber and accountNumber '() {
        given:
        def filter = newTransactionFilter()

        when:
        def response = transactionService.findTransactions(filter)

        then:
        !response.isEmpty()
        response.size() == 2

        interaction {
            transactionRepository.findByCustomerNumberAndAccountNumber(_, _) >> newDomainTransactions()
            transactionMapper.map(_ as List) >> newTransactionResponses()
        }
    }

    @Unroll('should throw ResourceNotFoundException when database responds #scenario')
    def 'should throw ResourceNotFoundException if there are no records'() {
        given:
        def filter = newTransactionFilter()

        when:
        transactionService.findTransactions(filter)

        then:
        thrown(ResourceNotFoundException)

        interaction {
            transactionRepository.findByCustomerNumberAndAccountNumber(_, _) >> databaseResponse
            0 * transactionMapper.map(_ as List)
        }

        where:
        scenario      | databaseResponse
        'empty array' | []
        'null value'  | null
    }


}
