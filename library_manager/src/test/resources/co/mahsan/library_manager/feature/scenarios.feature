Feature: Books CRUD
  Scenario: client posts a book and gets all books
    Given client posts a book with name "Animal Farm"
    When the client gets all books
    Then check the status code to be OK

  Scenario: client makes call to POST and GET a book
    Given client posts a book with name "Nineteen Eighty-Four"
    When the client calls book by id
    Then check the received book name has an id and its name is "Nineteen Eighty-Four"
    And check the status code to be OK

  Scenario: client makes post request
    When client posts a book with name "Vojood"
    Then check if new book is in database
    And check the status code to be OK

  Scenario: client makes update request
    Given client posts a book with name "Harry Potter and the Deathly Hallows"
    When the client requests to update last book name
    Then the book is updated

  Scenario: client makes delete request
    Given client posts a book with name "Leili va Majnoon"
    When the client requests to delete last book
    Then the book must not be in GET request
