#sekumpulan dari beberapa skenario
Feature: Purchase the order from ecommerce

#state / step yang dijalanin sebelum skenario di running
#mirip before method / precondition
Background: Buyer landed to website
  Given Buyer landing to ecommerce

#seperti Test di testng
# Scenario: Create order positive case
#   Given Buyer logged to website email <email> and password <password>
#   When Buyer add product <product_name> to Cart 
#   And Buyer checkout product
#   And Buyer place order first_name <first_name> last_name <last_name> and zip_code <zip_code>
#   And Buyer will see checkout overview
#   Then Buyer will receive confirmation page Thank you for your order!

#   Examples:
#   | email         | password     | product_name        | first_name | last_name | zip_code |
#   | standard_user | secret_sauce | Sauce Labs Backpack | Ketika     | Cintawa   | 64131    |

# Scenario Outline: Login Negative Case 
#   When Buyer logged to website with wrong email <email> or password <password>
#   Then Buyer will see tag heading error Epic sadface: Username and password do not match any user in this service

#   Examples:
#   |email                         | password        |
#   |standard_user                 | wrong_pw        |
#   |wrong_email                   | secret_sauce    |

# Scenario Outline: Buyer login with acceptable email and password
#   When Buyer logged to website acceptable email <email> and password <password>
#   Then Buyer will see list products Products

#   Examples:
#   | email                         | password        |
#   | standard_user                 | secret_sauce    |
#   | problem_user                  | secret_sauce    |
#   | error_user                    | secret_sauce    |
#   | visual_user                   | secret_sauce    |

Scenario Outline: Blocked buyers cannot log in to the website
  When Blocked Buyer logged to website with their email <email> and password <password>
  Then Buyer will see error Epic sadface: Sorry, this user has been locked out.

  Examples:
  | email                         | password        |
  | locked_out_user               | secret_sauce    |

# Scenario Outline: Filtering Products (4 Options)
#   Given Buyer logged to website email <email> and password <password>
#   When Buyer filter products by Name (A to Z)
#   Then Buyer will see products sorted by Name (A to Z)
#   When Buyer filter products by Name (Z to A)
#   Then Buyer will see products sorted by Name (Z to A)
#   When Buyer filter products by Price (low to high)
#   Then Buyer will see products sorted by Price (low to high)
#   When Buyer filter products by Price (high to low)
#   Then Buyer will see products sorted by Price (high to low)

#   Examples:
#   |email                         | password        |
#   |standard_user                 | secret_sauce    |