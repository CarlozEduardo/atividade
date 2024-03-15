package org.crud;

import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ProdutoResourceTest {


    @Test
    void testGetProduto() {
        given()
                .when()
                .get("/produto")
                .then()
                .statusCode(200);
    }
    @Test
    void testGetProdutoPeloPreco() {
        Response resposta = given()
                .pathParam("preco",0).contentType(ContentType.JSON)
                .when()
                .get("/produto/{preco}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        List<Produto> listaTeste = resposta.jsonPath().get();
        Assertions.assertFalse(listaTeste.isEmpty());
    }

//    void testPostProduto() {
//    }
//    void testPutProduto() {
//    }
//    void testDeleteProduto() {
//    }

}