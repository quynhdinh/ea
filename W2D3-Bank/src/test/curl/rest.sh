# Create an account
curl -X POST http://localhost:8080/accounts \
  -H "Content-Type: application/json" \
  -d '{"accountNumber":12345,"customerName":"Alice"}'

# Get All Accounts
curl -X GET http://localhost:8080/accounts \
  -H "Accept: application/json"

# Get a Single Account
curl -X GET http://localhost:8080/accounts/12345 \
  -H "Accept: application/json"

# Deposit USD
curl -X POST http://localhost:8080/accounts/12345/deposit \
  -H "Content-Type: application/json" \
  -d '{"amount":5000}'

# Withdraw USD
curl -X POST http://localhost:8080/accounts/12345/withdraw \
  -H "Content-Type: application/json" \
  -d '{"amount":2000}'

# Deposit Euros
curl -X POST http://localhost:8080/accounts/12345/deposit-eur \
  -H "Content-Type: application/json" \
  -d '{"amount":1000}'

# Withdraw Euros
curl -X POST http://localhost:8080/accounts/12345/withdraw-eur \
  -H "Content-Type: application/json" \
  -d '{"amount":500}'

# Transfer funds
curl -X POST http://localhost:8080/accounts/transfer \
  -H "Content-Type: application/json" \
  -d '{
        "fromAccountNumber":12345,
        "toAccountNumber":4253892,
        "amount":1000,
        "description":"Rent payment"
      }'

# Ivalid Request (Validation test), should return 400
curl -X POST http://localhost:8080/accounts \
  -H "Content-Type: application/json" \
  -d '{"accountNumber":0,"customerName":""}'
