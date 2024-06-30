Feature: Creating new Trainee

  Scenario: Register as a new trainee
    Given a user wants to register as a trainee
    When the user provides the required details for the trainee
    Then the new trainee should be created

  Scenario: Retrieve an existing trainee by username
    Given trainee with username "juan.perez" is a registered user
    And trainee is authenticated
    When requesting details from username "juan.perez"
    Then existing trainee with username "juan.perez" should be returned

  Scenario: Edit firstName and LastName on existing trainee
    Given trainee with username "juan.perez" is a registered user
    And trainee is authenticated
    When requesting details from username "juan.perez"
    And trainee provides firstName "Jose" and lastName "Gomez"
    Then trainee with new firstName "Jose" and lastName "Gomez" should be returned