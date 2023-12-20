import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;

public class CrfByBuild extends BaseClass {
    private String buildsiteid;

    @Test(priority =2)
    public void CrfsByBuildTest() {
        try {
            // Read the GraphQL query from the file
            String query = FileUtils.readFileToString(new File("src/test/resources/CrfsByBuild/query.graphql"), "utf-8");

            // Read the variables from the JSON file
            String variables = FileUtils.readFileToString(new File("src/test/resources/CrfsByBuild/variables.json"), "utf-8");
            query = query.replaceAll("[\n\\s]+", " ");
            // Construct the request body as a JSON objec
            String requestBody = String.format("{\"query\":\"%s\", \"variables\": %s}", query, variables);

            // Send the GraphQL request using RestAssured
            RequestSpecification request = RestAssured.given()
                    .header("Accept-Encoding", "gzip,deflate,br")
                    .header("Content-Type", "application/json")
                    //    .header("Accept", "application/json")
                    .header("Authorization", sb_token)
                    .body(requestBody);
            Response createResponse = request.post(Route.BASE_PATH_SB);
            createResponse.then().log().all();
            buildsiteid = extractBuildIdFromGraphQLResponse(createResponse);
            System.out.println("The buildsiteid  from crfsbybuild is " + buildsiteid);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBuildId() {
        return buildsiteid;
    }
    public String extractBuildIdFromGraphQLResponse(Response response) {
        try {
            String responseBody = response.getBody().asString();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            // Navigate through the nested structure to find "buildid"
            if (jsonNode.has("data") && jsonNode.get("data").has("crfsByBuild")) {
                JsonNode crfsNode = jsonNode.get("data").get("crfsByBuild");
                if (crfsNode.isArray() && crfsNode.size() > 0) {
                    // Assuming "buildid" is a top-level field in the first entry
                    String buildId = crfsNode.get(0).path("crfid").asText();
                    return buildId;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test(priority = 1)
    public void CreateCrfTest() {
        try {
            // Read the GraphQL query from the file
            String query = FileUtils.readFileToString(new File("src/test/resources/createCRf/query.graphql"), "utf-8");

            // Read the variables from the JSON file
            String variables = FileUtils.readFileToString(new File("src/test/resources/createCRf/variables.json"), "utf-8");
            query = query.replaceAll("[\n\\s]+", " ");
            // Construct the request body as a JSON objec
            String requestBody = String.format("{\"query\":\"%s\", \"variables\": %s}", query, variables);

            // Send the GraphQL request using RestAssured
            RequestSpecification request = RestAssured.given()
                    .header("Accept-Encoding", "gzip,deflate,br")
                    .header("Content-Type", "application/json")
                    //    .header("Accept", "application/json")
                    .header("Authorization", sb_token)
                    .body(requestBody);
            Response createResponse = request.post(Route.BASE_PATH_SB);
            createResponse.then().log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(dataProvider = "idToUpdateProvider",priority = 3)
    public void UpdateCrfTest(String idToUpdate) {
        try {
            // Read the GraphQL query from the file
            String query = FileUtils.readFileToString(new File("src/test/resources/Updatecrf/query.graphql"), "utf-8");

            // Read the variables from the JSON file
            String variablesJsonStr = FileUtils.readFileToString(new File("src/test/resources/Updatecrf/variables.json"), "utf-8");
            query = query.replaceAll("[\n\\s]+", " ");

            JSONObject variablesJson = new JSONObject(variablesJsonStr);

            // Update the value of the idToUpdate variable
            variablesJson.put("idToUpdate", idToUpdate);

            // Convert the updated JSON back to a string
            String updatedVariables = variablesJson.toString();

            // Write the updated JSON back to the variables.json file
            FileUtils.writeStringToFile(new File("src/test/resources/Updatecrf/variables.json"), updatedVariables, "utf-8");

            // Construct the request body as a JSON object
            String requestBody = String.format("{\"query\":\"%s\", \"variables\": %s}", query, updatedVariables);

            // Send the GraphQL request using RestAssured
            RequestSpecification request = RestAssured.given()
                    .header("Accept-Encoding", "gzip,deflate,br")
                    .header("Content-Type", "application/json")
                    .header("Authorization", sb_token)
                    .body(requestBody);
            Response createResponse = request.post(Route.BASE_PATH_SB);
            createResponse.then().log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "idToUpdateProvider")
    public Object[][] idToUpdateProvider() {
        // You can fetch the idToUpdate values from CrfByBuild or any other source
        String idToUpdate1 = buildsiteid;
        // Provide another id if needed
        return new Object[][]{
                {idToUpdate1},
        };
    }

    @Test(dataProvider = "idToDeleteProvider",priority = 4)
    public void DeleteCrfTest(String idToDelete) {
        try {
            // Read the GraphQL query from the file
            String query = FileUtils.readFileToString(new File("src/test/resources/DeleteCrf/query.graphql"), "utf-8");

            // Read the variables from the JSON file
            String variablesJsonStr = FileUtils.readFileToString(new File("src/test/resources/DeleteCrf/variables.json"), "utf-8");
            query = query.replaceAll("[\n\\s]+", " ");

            JSONObject variablesJson = new JSONObject(variablesJsonStr);

            // Update the value of the idToUpdate variable
            variablesJson.put("idToDelete", idToDelete);

            // Convert the updated JSON back to a string
            String updatedVariables = variablesJson.toString();

            // Write the updated JSON back to the variables.json file
            FileUtils.writeStringToFile(new File("src/test/resources/DeleteCrf/variables.json"), updatedVariables, "utf-8");

            // Construct the request body as a JSON object
            String requestBody = String.format("{\"query\":\"%s\", \"variables\": %s}", query, updatedVariables);

            // Send the GraphQL request using RestAssured
            RequestSpecification request = RestAssured.given()
                    .header("Accept-Encoding", "gzip,deflate,br")
                    .header("Content-Type", "application/json")
                    .header("Authorization", sb_token)
                    .body(requestBody);
            Response createResponse = request.post(Route.BASE_PATH_SB);
            createResponse.then().log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "idToDeleteProvider")
    public Object[][] idToDeleteProvider() {
        // You can fetch the idToUpdate values from CrfByBuild or any other source
        String idToDelete = buildsiteid;
        // Provide another id if needed
        return new Object[][]{
                {idToDelete},
        };
    }
}
