# OnlineResumeBuilder-BE
Resume builder rest service
INTRODUCTION :
===============================================================
Online Resume Builder helps to create resume in proper format and alignment as per you are need. which saves time. 

MODULES INVOLVED:
==================================================================
->Admin module
->Fresher module
->Experienced module
->Fresher resume download
->Experienced resume download

SOFTWARE USED
==================================================================
Back-end ---> Spring Boot Suit(STS)
Front-end ---> VS Code

TECHNOLOGIES USED
===================================================================
Back-end ---> Spring Boot
Front-end ---> React Js
Database  ---->PostgreSQL database

TOOL USED
====================================================================
Postman -- API testing tool.
Swagger -- UI Documentation Generation Tool
Logger -- Displays logging information with date and time.


STEPS TO WORK WITH BACK - END
=====================================================================
STEPS TO WORK WITH STS -->>
1)Open  Spring Tool Suit create new spring starter project. Choose the dependencies
2)Inside src/main/resources filled application.properties with information related to driver class name, url, username and password
3)Install and Enable Tomcat Server.
4)Run class (which includes main method) as Spring Boot App.
5)Check output through pgadmin, like tables created or not.

STEPS TO WORK WITH POSTMAN TOOL -->>
1) Open Postman application in your system.
2) Make sure that the Tomcat Server is running.
3) Choose any operation that you want to perform such as GET, POST, PUT, PATCH and DELETE from the drop down.
4) Enter the proper URI for that operation according to your REST controller and click Send. 
5) If it is a get operation data will be displayed in below terminal.
6)For POST operation just select the content-type of the body to be sent to controller in the Body section and select raw to enter the data to post.

STEPS TO USE SWAGGER API:
1) Configure Swagger in your system properly to run it in the web browser.
2) After configuring enter the URL http://localhost:8080/swagger-ui.html#/ into the browser address bar.
3) This will open up the API Documentation which is generated automatically by the Swagger API.
4) There you can see the different controllers which can be further examined by expanding that pane by clicking the downwards arrow.
5) All the database operations can be seen which are used in the controllers such as GET, POST, PUT, PATCH along with the description of each operation.
6) You can try out each and every opearation by clicking on the Try It Out button and entering the required data.

STEPS TO USE LOGGER API:
1) Configure logger in your system
2) Used for log application data such as what operation was performed on what date and time. Logs can be in the form of text files or can be displayed in console.
