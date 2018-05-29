Feature:          Vehicle Registration Details
Narrative:
In order to
As a              user
I want to         Check vehicle registration details


Scenario: Check the client vehicle registration details in DVLA site

Given I am on DVLA page
When I go to vehicle enquiry service page
Then I should see the vehicle details displayed should match with the data in below files:
  |FileName|
  |Vehicle Registration.xls|