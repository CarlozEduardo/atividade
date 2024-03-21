package org.crud;

import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ProdutoResourceTest {
//    @Mock
//    private Produto produto;
    @Test
    @Order(1)
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
    @Order(2)
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
    @Order(3)
    void testPostProduto() {
        Produto produtoTest = new Produto();
        produtoTest.setId(10000);
        produtoTest.setNome("Teste");
        produtoTest.setPreco(10.0);

        Response resposta = given()
                .body(produtoTest)
                .contentType(ContentType.JSON)
                .when()
                .post("/produto");

        Assertions.assertEquals(201, resposta.getStatusCode());
        Assertions.assertEquals("Produto cadastrado com sucesso", resposta.jsonPath().getString("descricao"));
    }

//    @Test
//    @Order(4)
//    void testPutProduto() {
//        Produto produtoTest = new Produto();
//        produtoTest.setId(10000);
//        produtoTest.setNome("Teste-B");
//        produtoTest.setPreco(20.0);
//
//        Response resposta = given()
//                .body(produtoTest)
//                .contentType(ContentType.JSON)
//                .when()
//                .put("/produto");
//
//        Assertions.assertEquals(200, resposta.getStatusCode());
//        Assertions.assertEquals("Produto atualizado com sucesso!", resposta.jsonPath().getString("descricao"));
//    }

    @Test
    @Order(5)
    void testDeleteProduto() {
        Produto produtoTest = new Produto();
        produtoTest.setId(10000);

        Response resposta = given()
                .body(produtoTest)
                .contentType(ContentType.JSON)
                .when()
                .delete("/produto");

        Assertions.assertEquals(200, resposta.getStatusCode());
        Assertions.assertEquals("Produto removido com sucesso!", resposta.jsonPath().getString("descricao"));
    }

}