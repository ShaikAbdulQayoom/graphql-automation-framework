import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;

public class CreateCrf extends BaseClass {
    @Test
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
}
