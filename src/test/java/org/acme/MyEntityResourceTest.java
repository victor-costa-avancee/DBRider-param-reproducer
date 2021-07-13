package org.acme;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


@DBRider
@DataSet(value = "datasets/import.yml")
@QuarkusTest
@TestHTTPEndpoint(MyEntityResource.class)
class MyEntityResourceTest {

    private void callEndpoint() {
        assertEquals(1, MyEntity.count());

        given()
                .when()
                .post()
                .then()
                .statusCode(200);

        assertEquals(2, MyEntity.count());
    }

    @Test
    void nonParameterizedTest() {
        callEndpoint();
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2})
    void parameterizedTestWithoutMethodDataset(long unusedParameter) {
        callEndpoint();
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2})
    @DataSet(value = "datasets/import.yml")
    void parameterizedTestWithMethodDataset(long unusedParameter) {
        callEndpoint();
    }
}