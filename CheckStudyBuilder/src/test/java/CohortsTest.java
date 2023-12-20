import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CohortsTest extends BaseClass{

    @Test
    public void cohortsT() throws IOException {
        String query = FileUtils.readFileToString(new File("src/test/resources/cohorts/query.graphql"), "utf-8");
        String variables = FileUtils.readFileToString(new File("src/test/resources/cohorts/variables.json"), "utf-8");
        query = query.replaceAll("[\n\\s]+", " ");
        String requestBody = String.format("{\"query\":\"%s\", \"variables\": %s}", query, variables);
        RequestSpecification requestSpec = given()
                .header("Content-Type", "application/json")
                .header("Authorization",sb_token)
                .body(requestBody);
        Response response = requestSpec.post(Route.BASE_PATH_SB);
        response.then().log().all();
    }
}
