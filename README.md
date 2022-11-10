# notification-system-challenge

## Setup

Go to the root folder and run next commands:
* mvn -f NotificationService clean compile package
* docker-compose up 

RabbitMQ will be accessible on http://localhost:15672

## REST API
Creation of message:

POST to http://localhost:8082/api/v1/notification/create

With sample body:

{
"channelType": "SMS",
"content": "content of message"
}

Possible values for channelType are:
* SMS
* EMail
* Slack

## Implementation notes
Current implementation of NotifierService only receives messages from the queues and logs them.
It demonstrates how consumers for different queues could be multiplied, achieving kind of horizontal scalability.  