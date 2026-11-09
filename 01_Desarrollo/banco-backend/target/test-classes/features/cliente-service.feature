Feature: Cliente Service Integration Tests

  Scenario: Save a new cliente
    Given a new cliente with name "Carlos"
    Then the cliente should be saved with name "Carlos"

  Scenario: Update an existing cliente
    Given a new cliente with name "Carla"
    When I save the cliente
    Then the cliente should be saved with name "Carla"
    When I update the cliente name to "Carla Actualizada"
    Then the cliente should be updated with the name "Carla Actualizada"

  Scenario: Read all clientes
    Given there are clientes in the system
    Then I should see a non-empty list of clientes

  Scenario: Delete an existing cliente
    Given a new cliente with name "Carmen"
    When I save the cliente
    Then the cliente should be saved with name "Carmen"
    When I delete the cliente
    Then the cliente should no longer exist in the system

