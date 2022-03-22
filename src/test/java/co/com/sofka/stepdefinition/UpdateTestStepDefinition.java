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

public class UpdateTestStepDefinition extends ServiceSetUp{

    public static final Logger LOGGER = Logger.getLogger(UpdateTestStepDefinition.class);
    private Response response;
    private RequestSpecification request;

    @Given("el usuario esta en la pagina de actualizacion con name {string} y el job {string}")
    public void elUsuarioEstlaEnLaPaginaDeActualizacionConNaneYElJob(String name, String job) {
        try{
            generalSetUp();
            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(bodyU(name, job));
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @When("cuando el usuario hace una peticion para actualizar el job")
    public void cuandoElUsuarioHaceUnaPeticionParaActualizarElJob() {

        try{
            response = request.when()
                    .put(UPDATE_RESOURCE);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }

    }

    @Then("el usuario debera ver un codigo de respuesta exitoso y un token de respuesta.")
    public void elUsuarioDeberaVerUnCodigoDeRespuestaExitosoYUnTokenDeRespuesta() {
        try{
            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("updatedAt", notNullValue());
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    private String bodyU(String name, String job){
        return "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\",\n" +
                "    \"updatedAt\": \"2022-03-21T23:27:22.414Z\"\n" +
                "}";
    }

}
