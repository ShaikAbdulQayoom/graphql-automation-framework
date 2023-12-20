import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BuildSitesTest extends BaseClass{



    @Test
    public void testGraphQLWithAuthorization() {



        try {
            // Read the GraphQL query from the file
            String query = FileUtils.readFileToString(new File("src/test/resources/buildSite/query.graphql"), "utf-8");

            // Read the variables from the JSON file
            String variables = FileUtils.readFileToString(new File("src/test/resources/buildSite/Variables.json"), "utf-8");
            query=query.replaceAll("[\n\\s]+"," ");
            // Construct the request body as a JSON objec
            String requestBody = String.format("{\"query\":\"%s\", \"variables\": %s}", query, variables);

            // Send the GraphQL request using RestAssured
            RequestSpecification request = RestAssured.given()
                    .header("Accept-Encoding", "gzip,deflate,br")
                    .header("Content-Type", "application/json")
                    //    .header("Accept", "application/json")
                    .header("Authorization",sb_token)
                            .body(requestBody);

            Response response = request.post(Route.BASE_PATH_SB);

            String rawResponse = response.asString();

            String cleanedResponse = rawResponse.replaceAll("[\n\\s]+", " ");

            // Print the response
            System.out.println("cleanedResponse: " + cleanedResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }}








//
//        RestAssured.baseURI = "https://dev-sb-api.advantage-eclinical.com/studyBuilder";
//
//      String query = FileUtils.readFileToString(new File("src/test/resources/Graphql/query.graphql"), "utf-8");
//
//       String variables = FileUtils.readFileToString(new File("src/test/resources/Graphql/Variables.json"), "utf-8");
//String BearerToken="eyJraWQiOiJPanp1UkZlckxFNGlJeFEwY2dwbHVOejd3bWk2VTJYRVFKWUFHUElXTkE4IiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULmJtcVVhSktheW1YbE56blNLRjNJSzJNbEpta3ZzUTFlcG1iNU1tbGxzR2cub2FyMXVjNmRhd0hYdFNDazkyOTciLCJpc3MiOiJodHRwczovL2VtbWVzLm9rdGEuY29tL29hdXRoMi9kZWZhdWx0IiwiYXVkIjoiYXBpOi8vZGVmYXVsdCIsImlhdCI6MTY5NzE4OTAwMCwiZXhwIjoxNjk3MTkyNjAwLCJjaWQiOiIwb2E0dnEyN2EwanFlQ1JoaDI5NyIsInVpZCI6IjAwdWt4ZGpsN2drck9MVVJUMjk3Iiwic2NwIjpbIm9mZmxpbmVfYWNjZXNzIiwiZW1haWwiLCJwcm9maWxlIiwib3BlbmlkIl0sImF1dGhfdGltZSI6MTY5NzE4ODk5Miwic3ViIjoiZS5zcmlrYW50aEBlbW1lcy5jb20ifQ.j5kHrwJDYOESVAUjIr3Sefi3gMErj_bt6feWrthdOs5zoXjLzuk_wB3t__NzS76G7o20DCvmrD1iCtxcfNyfdpVPuSNzWFuzZoWMOEg8xE1eLl0sDHcI7y0LfJMuSG5phXbJ4253y4FHgcgUqFzFaA1QKJ70YSA5Pf7paFdoURMgPrWMG_apn0c-mNmO3OMD-2udFZejOAeQc6aGsYQ1CD0OWrf7Yb1zMzjrM1uCDlBorEHLuL7LjhYnhX3HWOXpY-tbLhue7Bgdc7jqDCgAUb95UGKXpD46aZskCUOGrLZ0O5pdIFmrj26x_MX7r09MNnp_1muZVcdqOpoqyImIKQ";
// String requestBody = String.format("{\"query\":\"%s\", \"variables\": %s}", query, variables);
//       // String graphqlQuery = String.format(query, variables);
//        RequestSpecification requestSpec = given()
//                .header("Accept-Encoding", "gzip,deflate,br")
//                .header("Content-Type", "application/json")
//                .header("Accept", "application/json")
//                .header("Authorization",BearerToken)
//                .body(requestBody).log().all();
//                //.body("{\"operationName\":\"BuildsByStudy\",\"variables\":{\"studyid\":\"af4b763f-03f3-4bc1-b1ac-a8dfd89e1ee0\"},\"query\":\"fragment build_fields on Build {\\n  basedon_buildid\\n  basedon_studyid\\n  buildid\\n  cdash_version\\n  ct_version\\n  date_format\\n  description\\n  econsent_settings\\n  enrollment_settings\\n  epro_settings\\n  in_prod\\n  in_stage\\n  in_test\\n  in_train\\n  is_cloning\\n  is_collect_qualitative_assessments\\n  is_direct_data_capture\\n  is_locked\\n  is_suspended\\n  is_useboxes\\n  is_userefranges\\n  meddra_version\\n  production_timestamp\\n  registration_settings\\n  sdtm_version\\n  studyid\\n  version_number\\n  whodrug_version\\n  __typename\\n}\\n\\nquery BuildsByStudy($studyid: ID!) {\\n  buildsByStudy(studyid: $studyid) {\\n    ...build_fields\\n    __typename\\n  }\\n}\"}");
//              Response response = requestSpec.post("/");
//              response.then().log().all();
//
//       // int statusCode = response.getStatusCode();
//        //String responseBody = response.getBody().asString();
//        //Response response = (Response) RestAssured.given().log().all()
////                .header("Accept-Encoding","gzip,deflate,br")
////                .header("Content-Type","application/json")
////                .header("Accept","application/json")
////                .header("Authorization","eyJraWQiOiJPanp1UkZlckxFNGlJeFEwY2dwbHVOejd3bWk2VTJYRVFKWUFHUElXTkE4IiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULkxTSG9VWE0zRW95Zlp4LWVrSVpHSzhOdnlyNllKTEJzZ1NPak5oSy1BdmMub2FyMXU5aWxscGhBeUpxN1MyOTciLCJpc3MiOiJodHRwczovL2VtbWVzLm9rdGEuY29tL29hdXRoMi9kZWZhdWx0IiwiYXVkIjoiYXBpOi8vZGVmYXVsdCIsImlhdCI6MTY5NzA3NzkyNywiZXhwIjoxNjk3MDgxNTI3LCJjaWQiOiIwb2E0dnEyN2EwanFlQ1JoaDI5NyIsInVpZCI6IjAwdWt4ZGpsN2drck9MVVJUMjk3Iiwic2NwIjpbIm9mZmxpbmVfYWNjZXNzIiwiZW1haWwiLCJwcm9maWxlIiwib3BlbmlkIl0sImF1dGhfdGltZSI6MTY5NzA3NzA3OSwic3ViIjoiZS5zcmlrYW50aEBlbW1lcy5jb20ifQ.g3GPX3ExcSkj-jcmRMrbcTRWdfBtNTnMK-GHk1twnLAFbq3twMOcfbDlfylCUPJLZiN60QsSUhvN6a1pIRDdeHQZ6NbOaQg0TjxxCAQGlbsQNcrMmiOp9AJ6kilAnWWtKPqL_6UB3KgfNNUCNfc7L-Rwntm8rymiL3GagOkWxovh2LlbCoPVrrpjhBThHW5A9v7EBAux0PEjDSqXBR0KqTd15bMb1h3QdRMzv6ZqdMya8OLKgL1ULsHPDfm2h26VtvkyTFJFRAZ0FsMH1V_Nd4acVsUE6WGVxpTpmGrQyyUWhqo_3olbJCNMSS6Ap5EeILVFsPXATMUZnD_rnxdXuw")
////                .body("{\"query\": \"" + query + "\", \"variables\": " + variables + "}").log().all()
//
//
//         Add your validation logic here
//        System.out.println("Status Code: " + statusCode);
//        System.out.println("Response Body: " + responseBody);
//
//
//
//        // Assert or validate the response as needed
//        // For example, using TestNG assertions
//        // Assert.assertEquals(statusCode, 200, "Expected status code 200");
//
//
//
//
//
//
//
//
//
