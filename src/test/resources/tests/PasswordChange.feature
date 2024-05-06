Feature: The user can change their own password
  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.

    Background:
      Given the user is logged in with username "existingUser" and password "oldPassword123"

    Scenario: Successful password change
      When the user navigates to the "Change Password" page
      And the user enters their current password "oldPassword123"
      And the user enters a new password "NewPassword!456"
      And the user confirms the new password "NewPassword!456"
      Then the password is changed successfully
      And the user receives a confirmation message "Your password has been changed."

    Scenario: Unsuccessful password change due to weak password
      When the user navigates to the "Change Password" page
      And the user enters their current password "oldPassword123"
      And the user enters a new password "weak"
      And the user confirms the new password "weak"
      Then the password change is unsuccessful
      And the user receives an error message "Your new password is too weak."

    Scenario: Unsuccessful password change due to non-matching passwords
      When the user navigates to the "Change Password" page
      And the user enters their current password "oldPassword123"
      And the user enters a new password "NewPassword!456"
      And the user confirms the new password "DifferentPassword!789"
      Then the password change is unsuccessful
      And the user receives an error message "The new passwords do not match."

    Scenario: Unsuccessful password change due to incorrect current password
      When the user navigates to the "Change Password" page
      And the user enters their current password "incorrectOldPassword"
      And the user enters a new password "NewPassword!456"
      And the user confirms the new password "NewPassword!456"
      Then the password change is unsuccessful
      And the user receives an error message "The current password is incorrect."

    Scenario: Unsuccessful password change due to reuse of old password
      When the user navigates to the "Change Password" page
      And the user enters their current password "oldPassword123"
      And the user enters a new password "oldPassword123"
      And the user confirms the new password "oldPassword123"
      Then the password change is unsuccessful
      And the user receives an error message "You cannot reuse an old password."