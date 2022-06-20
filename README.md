### Ongoing project
# PeriodScheduler
Full stack web application for scheduling Todo list and monitoring monthly period information.

Built with Java spring boot,React js and MySQL


## Features
* User registration and login system with email validation.
* Adding and showing period information in a calendar.
* Adding and showing schedule list.
* Payments/Donations.
* Auto removing outdated information.
* Google Login authentication (not included in front-end currently).
* Password resetting (not included in front-end currently).


## Technical Information
* REST API
* Java mail sender for sending email.
* mailDev for receiving emails.
* Confirmation token for validating user.
* Secure password validation.
* JWT token for authentication.
* Custom hashing method for hashing passwords.
* Stripe API for payments.
* React js for user-friendly front-end.

## Installation - Server
1. You must have java 18.0.1
2. Download the repository
3. Download and setup Mysql
4. Download and configure mailDev tool  https://github.com/maildev/maildev
5. Configure application.properties to work with mysql on the local machine
6. Ready to run the server

## Installation - Front-end
1. You need Node.js installed.Run
`
npm install
`
To install node and react
2. Ready to run the server.
`
npm start
`
To run the server

Once both the front-end and back-end servers are running the application is ready for using

