-----------------------------------------------------------------------------
RUN THE APP:

It is a spring boot 2 app, so download from GitHub, and from within the directory run spring boot:
./mvnw spring-boot:run

The system loads with 3 dummy customers, with ids: 1, 2, 3

-----------------------------------------------------------------------------
ENDPOINTS:

The endpoints are listed below. Use curl or Postman to test.


1) Get all telephone numbers:
   curl -v localhost:8081/teleapp/telnumbers  [200 - OK]


2) Get all telephone numbers for a particular customer:
   curl -v localhost:8081/teleapp/telnumbers/1  [200 - OK]  
   curl -v localhost:8081/teleapp/telnumbers/9  [404 - INVALID CUSTOMER]

3) Activate a telephone number
   curl -X PUT localhost:8081/teleapp/telnumbers/1/07658678567 -H 'Content-type:application/json' -d '{}'  [200 - OK]
   curl -X PUT localhost:8081/teleapp/telnumbers/9/07658678567 -H 'Content-type:application/json' -d '{}'   [404 - INVALID CUSTOMER]
   curl -X PUT localhost:8081/teleapp/telnumbers/1/07658678567333 -H 'Content-type:application/json' -d '{}' [404 - INVALID TELEPHONE NUMBER]
	
   After activating the number 07658678567 for customer 1 above: 	
   curl -X PUT localhost:8081/teleapp/telnumbers/1/07658678567 -H 'Content-type:application/json' -d '{}' [501 - NUMBER IS ALREADY VALIDATED]

-----------------------------------------------------------------------------
TESTS

There are some unit tests in the telecom.org.dao telecom.org.ctrl packages.

----------------------------------------------------------------------------- 