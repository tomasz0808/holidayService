# HolidayAPI
## Overview
This is a Holiday Information web service build with dropwizard v1.3.8 available on [GitHub](https://github.com/dropwizard/dropwizard/tree/release/1.3.x).  
This web service consumes [Holiday API](https://holidayapi.com/) and it's purpose is to provide names of holidays that occurs at the same day in 2 different countries.  
It allows to get historical data up to the end of 2018.   

## Requirements
 - Maven
 - Java JRE 1.8 or higher
 - Holiday API key (**not added to this repository**), free key can be acquired [here](https://holidayapi.com/).

## Specification
Service method:
 - POST /searchHoliday - returns next holiday names and date that occured at the same day in two different countries after the date provided in the request.

Service consumes and produces JSON format. 
## Sample request  
```
{
	"name1": "PL",
	"name2": "US",
	"date": "2016-12-31"
}
```
Parameters:  
 - name1 - first country code
 - name2 - second country code
 - date  - date after which common hoilday is being searched
 
This request will search for holidays that occured at the same day in Poland an United States after 2016-12-31.   
Date parameter must be provided in "yyyy-mm-dd" format.  
List of supported country codes can be found on Holiday API [website](https://holidayapi.com/).

## Sample response:
```
{
  "date": "2017-01-01",
  "name1": "Nowy Rok",
  "name2": "Last Day of Kwanzaa, New Year's Day"
}
```
Parameters:  
 - date  - next holiday date common for both countries
 - name1 - first country holidays names
 - name2 - second country holidays names
 
## Config.yaml file
Configuration file config.yaml can be found in the root folder of the project.  
It contains couple of important things (i.e. Holiday API key, service port selection, logging settings).  
You can adjust it to your needs before running the service. 

**Please replace `<your-holiday-api-key>` with your actual Holiday API key**  

### Sample config file:
```
#data mapping
#api key
apiKey: <your-holiday-api-key>
#holiday api url
apiUrl: https://holidayapi.com/v1/holidays

server:
  applicationConnectors:
  - type: http
    port: 8090
  adminConnectors:
  - type: http
    port: 8091

# logging settings
logging:
   level: INFO
   appenders:
      - type: console
```

## How to run
 - clone: https://github.com/tomcio0808/holidayService.git
 - put your Holiday API key to config.yaml and save file
 - build the project with **$mvn clean package** which will generate fat jar 
 - run generated fat jar with command: **java -jar target/holiday-1.0-SNAPSHOT.jar server config.yml** 
 - use Postman or any other tool to communicate with service
 
 ## Assumptions
 - Service is configured to provide historical data up to the end of 2018 with free API key. Any request containing date 2018-12-31 or later will get response informing about free api key restrictions.
 - Service was not tested with premium holiday API key but as per design there should not be any issues to get response for future holidays with premium api key.
 - Service can return multiple holiday names for specific country as it is possible that two or more holidays occurs the same day in one country.
In that scenario each holiday name will be seperated by a comma. Sample response with more than one holiday in United States (parameter ***name2***):
 ```
 {
    "date": "2018-01-01",
    "name1": "Nowy Rok",
    "name2": "Last Day of Kwanzaa, New Year's Day"
}
```
- In case when no common holiday for coutries can be found appropriate response will be send back by the service.

## Additional info
- Service has two additional health check register using dropwizard. They can be triggered with:
 	- GET /healthcheck - request send to the port mapped for admin connection.
 
 













