Feature: Books can be retrieved
  Scenario: client makes call to GET /books
    When the client calls books

  Scenario: client makes call to GET /books/id
    When the client calls book "630f2dd1f3642a6671c9cfcf"
