# currency-exchange-rate-

A Java-based application that retrieves and displays current currency exchange rates.

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 8 or higher**: [Download JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Apache Maven**: [Download Maven](https://maven.apache.org/download.cgi)

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/Hina-ashraf/Currency-Exchange-Rate.git
   cd Currency-Exchange-Rate
   
2. **Build the Application**:
   Use Maven to build the project and resolve dependencies
   mvn clean install
3.**Running the Application**:
   After building, you can run the application using the following command:
   java -jar target/Currency-Exchange-Rate-
4.**Usage**:
   Once the application is running, it will fetch and display the latest currency exchange rates. Ensure you have an active internet connection, as the application retrieves data from external APIs.
   Due to no subscription in open exchange rates can only access USD.
   API_URL = "https://openexchangerates.org/api/latest.json?app_id=64f128ce2c71431b9d61678da6afeae7&base=USD"
5. **Dependencies**:
    This Application Uses Following Dependencies:
   1. Spring Boot:For building the application.
   2. Rest Template:To make HTTP request to external APIs.
   3. Spring Web:To enable features for building web applications and RESTful APIs.
   4. Lombok:It helps you avoid writing repetitive getter/setter methods, constructors and other utility methods.
      these are specified in the pom.xml file and will be managed by maven.
