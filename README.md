
[![Compile, test, deploy](https://github.com/jessiexem/PAFWorkshop21/actions/workflows/main.yaml/badge.svg)](https://github.com/jessiexem/PAFWorkshop21/actions/workflows/main.yaml)


![Coverage](https://jgclass.sgp1.digitaloceanspaces.com/coverage/PAFWorkshop21/jacoco.svg)

##Northwind Database
https://github.com/dalers/mywind.git

##Get a list of all customers
http://localhost:8095/api/customers?limit=20&offset=2

##Get the details of a customer with the customer's id
http://localhost:8095/api/customer/<cust_id>

- will return 404 and error object if customer does not exist

##Get all orders for a customer
http://localhost:8095/api/customer/<cust_id>/orders

- will return an empty Json array if the customer does not have any orders
- will return 404 and error object if customer does not exist