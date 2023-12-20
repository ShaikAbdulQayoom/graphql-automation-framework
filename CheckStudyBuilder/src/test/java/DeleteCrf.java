import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class DeleteCrf extends BaseClass {

    @Test(dataProvider = "idToDeleteProvider")
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
        String idToUpdate1 = "";
        // Provide another id if needed

        return new Object[][]{
                {idToUpdate1},

        };

//        CrfByBuild crfsByBuild = new CrfByBuild();
//        crfsByBuild.CrfsByBuildTest();
//        String buildsiteid = crfsByBuild.getBuildId();
//
//        return new Object[][]{
//                {buildsiteid},
//                // You can provide additional data if needed
//        };
    }
}