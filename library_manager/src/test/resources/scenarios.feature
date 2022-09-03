Feature: Books can be retrieved
  Scenario: client makes call to GET /books
    When the client calls /books
    Then the client receives status code of 200