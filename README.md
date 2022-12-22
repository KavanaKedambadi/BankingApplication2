/********* Application Details : A Java application to run basic statement enquiry request using microservices for a priviliged user and a normal user. Author : Kavana K K Date : 04/12/2022 - 06/12/2022 Platform/Tools/Languages/Framework : Java, Springboot, jdbc, logback, jquery, html, css, MS Access db. Browsers tested : Chrome and Edge *********/

Steps to run the application:

Extract the project and import to eclipse IDE.
Change the local path for db access in application.properties file. Parameter - spring.datasource.url to the corresponding local path where the db file is placed.
Run the application(Banking Application) as java application. It should run on port 8080
Open the browser(tested on chrome and Edge) and enter "http://localhost:8080/login. User sign-in page will open.
Login using below credentials. Normal user : user/user Priviliged user : admin/admin
Salutation with the login name is shown as soon as soon as user logs in.
Logout button to log out of the application - the sesssion gets terminated.
admin login - To fetch the account statements using paramters - From/To Date and From/To Amount Range.
user login - To fetch last three months account statement from current date without any other parameters. Any other parameter used for statment fetch will result in unauthorised access response.
Search on the account id returns corresponding masked account number along with the account type.
Any invalid data input - shows the error message to the user for the user to rectify and re-search with valid data.
Session timeout of 15 minutes is maintained.
Log folder will be created in in your local C:\tmp to access the logs.
