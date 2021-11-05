# Expense Reimbursement System

## Project Description

This expense reimbursement System manages the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Technologies Used

* PostgreSQL
* Java and Java Servlets
* HTML5, CSS3 and JavaScript 
* Jackson API
* Apache Tomcat Server v9.0
* AWS Relational Database System
* JUnit and Mockito

## Features

List of features ready and TODOs for future development
* All users can login and logout properly
* Employees are able to view their past Reimbursement requests as well as submit new requests
* Managers are able to list all employees, view reimbursement requests by status and Approve/ Reject Reimbursement Requests

To-do list:
* Encrypt passwords within the database
* Send email containing a randomly-generated temporary password to newly registered user 

## Getting Started

Clone this repository with by using the following command in Git Bash:

`git clone https://github.com/CharlesSmith98/Project001-ERS.git`

- Create a database that uses postgresql as the database vendor
- Within your local repository navigate to "src/main/resources" and make these modification to the file "jdbc.properties":
    - url should be `jdbc:postgresql://<server-endpoint-url>:<port>/<database-name>`
    - username should be the username you've configured for your database
    - password should be the password you've configured for your database user
- In the newly created folder there is a folder "sqlScripts" containing the following scripts
    - Project01-CreateTables.sql which creates the Database Tables
    - Project01-PopulateLookUps.sql which populates the lookup tables (Tables that contain values that other tables use as reference)
- Inside your created database, run the scripts mentioned above in that exact order
- Import the project in your prefered IDE as a maven project
- Select the project in your IDE and run it on an apache tomcat server.

## Usage

You may run the project and view it in a web browser. In your web browser, you can open the registration page at: `http://localhost:<port>/Project001/resources/html/register.html` 

