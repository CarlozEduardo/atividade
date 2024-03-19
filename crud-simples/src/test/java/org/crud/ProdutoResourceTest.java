package org.crud;

import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ProdutoResourceTest {
//    @Mock
//    private Produto produto;

    @Test
    void testGetProduto() {
        Response resposta = given()
                .when()
                .get("/produto")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        List<Produto> listaGet = resposta.jsonPath().get();
        Assertions.assertFalse(listaGet.isEmpty());
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

        Assertions.assertTrue(listaTeste.size() > 0);
    }

    @Test
    void testPostProduto() {
//        Produto produtoTest = new Produto();
//        produtoTest.setId(1);
//        produtoTest.setNome("Teste");
//        produtoTest.setPreco(10.0);

        Response resposta = given()
//                .body(produtoTest)
//                .contentType(ContentType.JSON)
                .when()
                .post("/produto")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().response();

        System.out.println(resposta.getStatusCode());

        Assertions.assertEquals("Produto cadastrado com sucesso", resposta.jsonPath().getString("descricao"));
    }

//    void testPutProduto() {
//    }

//    void testDeleteProduto() {
//    }

}