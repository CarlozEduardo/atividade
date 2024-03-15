package org.crud;

import io.quarkus.test.junit.QuarkusTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ProdutoResourceTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    void testGetProduto() {
        given()
                .when()
                .get("/produto")
                .then()
                .statusCode(200);
//                .contentType(ContentType.JSON)
//                .extract().response();

//        List<Produto> listaTeste = resposta.jsonPath().get();
//
//        boolean teste = !listaTeste.isEmpty();
//        Assertions.assertTrue(teste);
    }
    @Test
    void testGetProdutoPeloPreco() {
        Response resposta = given()
                .pathParam("preco",2).contentType(ContentType.JSON)
                .when()
                .get("/produto/{preco}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        List<Produto> listaTeste = resposta.jsonPath().get();
        Assertions.assertEquals(2, listaTeste.size()); ;
    }

//    void testPostProduto() {
//    }
//    void testPutProduto() {
//    }
//    void testDeleteProduto() {
//    }

}