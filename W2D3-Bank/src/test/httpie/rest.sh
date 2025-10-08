# Create an account
http POST :8080/accounts accountNumber=12345 customerName=Alice

# Get All Accounts
http GET :8080/accounts

# Get a Single Account
http GET :8080/accounts/12345

# Deposit USD
http POST :8080/accounts/12345/deposit amount=5000

# Withdraw USD
http POST :8080/accounts/12345/withdraw amount=2000

# Deposit Euros
http POST :8080/accounts/12345/deposit-eur amount=1000

# Withdraw Euros
http POST :8080/accounts/12345/withdraw-eur amount=500

# Transfer funds
http POST :8080/accounts/transfer \
  fromAccountNumber=12345 \
  toAccountNumber=4253892 \
  amount=1000 \
  description="Rent payment"

# Ivalid Request (Validation test), should return 400
http POST :8080/accounts accountNumber:=0 customerName=""
