package com.example.functional

import com.example.accounts.TransactionFixtures
import com.example.accounts.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Unroll

import static org.hamcrest.core.IsEqual.equalTo
import static org.hamcrest.core.IsNull.notNullValue

class GetTransactionsFuncSpec extends BaseIntegrationSpec implements TransactionFixtures {

    @Autowired
    private TransactionRepository transactionRepository

    def 'should return existing transactions for given customerId and accountId'() {
        given:
        def customerNumber = 123456789
        def accountNumber = 2334674571

        and:
        def transactions = [
                newDomainTransaction([credit: 1450.00, debit: null, operation: 'credit', transactionNarrative: 'deposit',
                                      accountNumber: accountNumber, customerNumber: customerNumber]),
                newDomainTransaction([credit: null, debit: 34.56, operation: 'debit', transactionNarrative: 'purchase',
                                      accountNumber: accountNumber, customerNumber: customerNumber])
        ]
        transactionRepository.saveAll(transactions)

        when:
        def response = httpGet("/customers/$customerNumber/accounts/$accountNumber/transactions")

        then:
        response.statusCode(200)
                .body('data.size', equalTo(2))

        and:
        response.body('data[0].transactionId', notNullValue())
        response.body('data[0].accountNumber', equalTo(accountNumber))
        response.body('data[0].date', equalTo('19/06/2020'))
        response.body('data[0].credit', equalTo(1450.00F))
        response.body('data[0].operation', equalTo('credit'))
        response.body('data[0].transactionNarrative', equalTo('deposit'))

        and:
        response.body('data[1].transactionId', notNullValue())
        response.body('data[1].accountNumber', equalTo(accountNumber))
        response.body('data[1].date', equalTo('19/06/2020'))
        response.body('data[1].debit', equalTo(34.56F))
        response.body('data[1].operation', equalTo('debit'))
        response.body('data[1].transactionNarrative', equalTo('purchase'))
    }

    def 'should return http 404 if transactions does not exists under the given accountNumber'() {
        given:
        def customerNumber = 123456789
        def nonExistingAcNumber = 2334674578

        when:
        def response = httpGet("/customers/$customerNumber/accounts/$nonExistingAcNumber/transactions")

        then:
        response.statusCode(404)
        response.body('errors[0].errorCode', equalTo('NOT_FOUND'))
        response.body('errors[0].description', equalTo('Resource Not Found'))
    }

    @Unroll('should return http 400 when #scenario')
    def 'should return http 400 for http get request validation errors'() {
        when:
        def response = httpGet("/customers/$customerNumber/accounts/$accountNumber/transactions")

        then:
        response.statusCode(400)
                .body("errors[0].errorCode", equalTo('BAD_REQUEST'))
                .body("errors[0].description", equalTo(errorMessage))

        where:
        scenario                                  | customerNumber | accountNumber | errorMessage
        'customerNumber is less than 9 digits'    | 33333333       | 7777777777    | 'Invalid customerNumber'
        'customerNumber is greater than 9 digits' | 3333333333     | 7777777777    | 'Invalid customerNumber'
        'customerNumber is non numeric'           | '3we4678902'   | 7777777777    | 'Invalid customerNumber'
        // invalid account number
        'accountNumber is less than 10 digits'    | 333333333      | 777777777     | 'Invalid accountNumber'
        'accountNumber is greater than 10 digits' | 333333333      | 77777777777   | 'Invalid accountNumber'
        'accountNumber is non numeric'            | 333333333      | '777777erty7' | 'Invalid accountNumber'
    }
}
