Feature: Books CRUD
  Scenario: client makes call to GET /books
    Given execute posting a new book
    When the client calls books
    Then check the status code to be OK

  Scenario: client makes call to POST and GET book
    Given execute posting a new book
    When the client calls book by id
    Then check the status code to be OK

  Scenario: client makes post request
    When execute posting a new book
    Then check if new book is in database
    And check the status code to be OK

  Scenario: client makes update request
    Given execute posting a new book
    When the client requests to update last book name
    Then check the status code to be OK

  Scenario: client makes delete request
    Given execute posting a new book
    When the client requests to delete last book
    Then check the status code to be OK

  #todo comment: matn-e scenario ha khoob nist. bia baham sohbat konim
  #todo comment: step ha bayad tarjihan usable bashan ke alan nistan. bia baham sohbat konim
