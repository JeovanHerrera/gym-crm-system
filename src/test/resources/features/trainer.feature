Feature: Creating new Trainer

  Scenario: Register as a new trainer
    Given a user wants to register as a trainer
    When the user provides the required details for the trainer
    Then the new trainer should be created

  Scenario: Retrieve an existing trainer by username
    Given trainer with username "jose.gomez" is a registered user
    And trainer with username "jose.gomez" is authenticated
    When requesting details from trainer with username "jose.gomez"
    Then existing trainer with given username should be returned


  Scenario: Edit firstName and LastName on existing trainer
    Given trainer with username "jose.gomez" is a registered user
    And trainer with username "jose.gomez" is authenticated
    When trainer provides firstName "Luis" and lastName "Gamez"
    Then trainer with new details should be returned