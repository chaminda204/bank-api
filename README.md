# Account-Transaction-Read API

This api supports read operations of customer accounts and transactions. Logged-in users can view account details of
their transactions.

## System Requirements

Following software is required to run this project:

- Git
- JDK 8
- Gradle - wrapper included
- Docker - not mandatory

## Run the application

#### Running the application with gradle

go to the project base dir and run the following command

````shell
./gradlew bootRun
````

#### Running the application with docker

go to the project base dir and run the following command

````shell
docker-compose up
````

## Notes / Assumptions

- This application supports read only operations and data creation is out of scope
- Customer information is managed in Customer microservice and only customer id is used in this service.
- Maintaining account balance is part of create operation and that will be updated when new transaction is inserted into
  the transaction table (out of scope).
- Application security and scopes are managed by the API gateway. API gateway injects x-customer-id header for logged-in
  users.
- Sample data is inserted through a sql script for demonstration purpose only.

## Request / Response information

#### Get Customer Accounts

Returns all accounts belong to the logged-in user.

Request HTTP GET

  ````
curl http://localhost:8080/customers/150034523/accounts
  ````

Response

  ```json
{
  "data": [
    {
      "accountNumber": 1234563445,
      "customerNumber": 150034523,
      "accountName": "Savings123US",
      "accountType": "Savings",
      "currency": "USD",
      "balanceDate": "21/06/2021",
      "openingBalance": 12335.67
    },
    {
      "accountNumber": 1234597922,
      "customerNumber": 150034523,
      "accountName": "Savings566GB",
      "accountType": "Savings",
      "currency": "GBP",
      "balanceDate": "21/06/2021",
      "openingBalance": 344.33
    }
  ]
}
  ```

#### Get Account Transactions

Returns all transactions for given account.

Request HTTP GET

  ````
curl http://localhost:8080/customers/150034523/accounts/1234563445/transactions
````

Response

```json
{
  "data": [
    {
      "transactionId": 10000000000,
      "customerNumber": 150034523,
      "accountNumber": 1234563445,
      "date": "20/06/2021",
      "credit": 4250.00,
      "operation": "credit",
      "transactionNarrative": "Salary"
    },
    {
      "transactionId": 10000000050,
      "customerNumber": 150034523,
      "accountNumber": 1234563445,
      "date": "20/06/2021",
      "debit": 300.00,
      "operation": "debit",
      "transactionNarrative": "ATM withdrawal"
    }
  ]
}
````

### Product Backlog

````
1   View customer account details - Logged in customers should be able to view their accounts information
2   View customer account transactions - Logged in user should be able to view transactions related to account.
3   Authorise x-customer-id header againsed the http GET requests customer id (not done)
4   Swagger/ Open api specification (not done)
````



