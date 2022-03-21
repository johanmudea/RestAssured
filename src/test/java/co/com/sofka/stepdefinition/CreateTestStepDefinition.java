package co.com.sofka.stepdefinition;

import co.com.sofka.stepdefinition.setup.services.ServiceSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;



public class CreateTestStepDefinition extends ServiceSetUp {

    public static final Logger LOGER = Logger.getLogger(CreateTestStepDefinition.class);
    private Response response;
    private RequestSpecification resquest;

    @Given("el usuario esta en la pagina de creacion con el name {string} y job {string}")
    public void elUsuarioEstaEnLaPaginaDeCreacionConElNameYJob(String name, String job) {

        try{
            generalSetUp();
            resquest = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(body(name, job));
        } catch (Exception e){
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }

    }
    @When("el usuario hace la peticion de cracion de usuario")
    public void elUsiarioHaceLaPeticionDeCracionDeUsuario() {

        try{
            response = resquest.when()
                    .post(CREATE_RESOURCE);
        } catch (Exception e){
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }


    }
    @Then("el usuario debe ver un codigo de respuesta exitoso y un token de respuesta")
    public void elUsiarioDebeVerUnCodigoDeRespuestaExitosoYUnTokenDeRespuesta() {

        try{
            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .body("createdAt", notNullValue());
        } catch (Exception e){
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }



    }

    private String body(String name, String job){
        return "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\",\n" +
                "    \"id\": \"676\",\n" +
                "    \"createdAt\": \"2022-03-21T22:38:34.991Z\"\n" +
                "}";
    }


}
