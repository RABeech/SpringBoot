##### **Author: Adam Beech**

##### **Date: 1/11/2018**

##### **Description:**

Spring Boot application that returns the latitude and longitude for a given
street address using a Google Maps API.

#### **Features:**

This application uses a simple address controller built in the Spring Boot framework to render a set of views that house a bootstrap form for user input. The user enters a standard street address that is validated by a regular express. The address is parsed into an object and then sent to a GET API for geocoding to return the latitude and longitude of the given address.
