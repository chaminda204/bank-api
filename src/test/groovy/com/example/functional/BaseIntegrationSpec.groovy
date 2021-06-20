package com.example.functional

import com.example.accounts.Application
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.runner.RunWith
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ActiveProfiles("test")

abstract class BaseIntegrationSpec extends Specification {

    @LocalServerPort
    int port

    void setup() {
        RestAssured.port = port
    }

    protected httpGet(String uri) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(uri)
                .then()
    }


}
