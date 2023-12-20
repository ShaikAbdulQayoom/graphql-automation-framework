Dependencies
The class has the following external dependencies:

TestNG: A testing framework for Java.
RestAssured: A Java library for testing RESTful web services.
Apache Commons IO: A library for common I/O operations.
Gson: A library for JSON processing in Java.
Make sure to include these dependencies in your project to successfully run the tests.

Test Execution
To execute the tests in the Test class:

Set up your project with the required dependencies.
Replace "YOUR_AUTHORIZATION_TOKEN_HERE" in the testGraphQLWithAuthorization method with the actual authorization token.
Run the test class using your preferred test runner or build tool.
Test Logic
The test logic is encapsulated in the testGraphQLWithAuthorization method. Here's a breakdown of the key steps:

Read GraphQL Query and Variables: Read the GraphQL query and variables from external files.
Construct Request Body: Build the request body as a JSON object by combining the query and variables.
Send GraphQL Request: Use RestAssured to send a POST request to the specified GraphQL endpoint with the constructed request body and necessary headers, including the authorization token.
Handle Response: Extract and clean the response, printing the cleaned response to the console.
Exception Handling: Catch and print any exceptions that may occur during the test execution.

Important Notes
Ensure that the GraphQL endpoint URL and file paths for the query and variables are correctly specified.
Replace the placeholder authorization token with a valid token for your testing environment.
Feel free to customize the class and methods according to your specific testing requirements and environment.
