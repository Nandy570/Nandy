package starter.steps;

import static io.restassured.RestAssured.when;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import static net.serenitybdd.rest.SerenityRest.rest;


/**
 *  This class has the steps that we will need to execute.
 */
public class PetStoreSteps {

    Pet pet;
//    @Step("#When I retrieve all available pets and check for a specific one")
//    public void retrieveAllAvailablePets() {
//        Response response =
//                when().
//                        get("\"https://petstore.swagger.io/v2/pet/findByStatus?status={status}\", \"available\"").
//                        then().log().all().
//                        contentType(ContentType.JSON).  // check that the content type return from the API is JSON
//                        extract().response(); // extract the response
//
//    }

    @Step("#When I add a pet to the pet store")
    public void addPet(Pet pet) {
        this.pet = pet;
        int id = 899999;
        String jsonPet = "{\"id\": " + id + " , \"name\": \""
                + pet.getName() + "\", \"photoUrls\": [], \"status\": \""
                + pet.getStatus() + "\"}";

        rest().given().contentType("application/json").log().all()
                .body(jsonPet).post("https://petstore.swagger.io/v2/pet");

        pet.setId(id);
        pet.setName(this.pet.getName());
    }

    @Step("#The pet should be available")
    public void checkPetAvailability() {
        rest().get("https://petstore.swagger.io/v2/pet/{id}", pet.getId())
                .then().statusCode(SC_OK)
                .log().all()
                .and()
                .body("name", equalTo(this.pet.getName()));
    }



}
