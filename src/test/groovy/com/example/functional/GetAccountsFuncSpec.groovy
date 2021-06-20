package com.example.functional

import com.example.accounts.AccountFixtures
import com.example.accounts.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Unroll

import static org.hamcrest.core.IsEqual.equalTo
import static org.hamcrest.core.IsNull.notNullValue

class GetAccountsFuncSpec extends BaseIntegrationSpec implements AccountFixtures {

    public static final int CUSTOMER_ID = 234777457

    @Autowired
    private final AccountRepository accountRepository

    def 'should return http 200 status with customer accounts for given valid customer id'() {
        given:
        def accountList = [newDomainAccount([customerNumber: CUSTOMER_ID]),
                           newDomainAccount([customerNumber: CUSTOMER_ID, accountName: 'Savings345AU'])
        ]

        and:
        accountRepository.saveAll(accountList)

        when:
        def response = httpGet("/customers/$CUSTOMER_ID/accounts")

        then:
        response.statusCode(200)
                .body('data.size', equalTo(2))
        and:
        response.body('data[0].customerNumber', equalTo(CUSTOMER_ID))
                .body('data[0].accountNumber', notNullValue())
                .body('data[0].accountName', equalTo('Savings123AU'))
                .body('data[0].accountType', equalTo('Savings'))
                .body('data[0].currency', equalTo('AUD'))
                .body('data[0].balanceDate', equalTo('19/06/2021'))
                .body('data[0].openingBalance', equalTo(10250.00F))
        and:
        response.body('data[1].customerNumber', equalTo(CUSTOMER_ID))
                .body('data[1].accountNumber', notNullValue())
                .body('data[1].accountName', equalTo('Savings345AU'))
                .body('data[1].accountType', equalTo('Savings'))
                .body('data[1].currency', equalTo('AUD'))
                .body('data[1].balanceDate', equalTo('19/06/2021'))
                .body('data[1].openingBalance', equalTo(10250.00F))

    }

    def 'should return http 404 if the customer record does not exists'() {
        given:
        def nonExistingCustomerId = 129678902

        when:
        def response = httpGet("/customers/$nonExistingCustomerId/accounts")

        then:
        response.statusCode(404)
        response.body('errors[0].errorCode', equalTo('NOT_FOUND'))
        response.body('errors[0].description', equalTo('Resource Not Found'))
    }


    @Unroll('should return http 400 when #scenario')
    def 'should return http 400 if the customer id format is invalid'() {

        when:
        def response = httpGet("/customers/$customerId/accounts")
        then:
        response.statusCode(400)
                .body("errors[0].errorCode", equalTo('BAD_REQUEST'))
                .body("errors[0].description", equalTo('Invalid customerNumber'))

        where:
        scenario                              | customerId
        'customerId is less than 9 digits'    | 123456
        'customerId is greater than 9 digits' | 1234567890
        'customerId is non numeric'           | '3we467890'
    }
}
