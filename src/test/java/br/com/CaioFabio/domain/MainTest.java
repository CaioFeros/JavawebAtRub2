package br.com.CaioFabio.domain;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class HelloEndpointTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testHelloEndpoint() {
        Javalin app = new Main().initJavalinApp();

        JavalinTest.test(app, (server, client) -> {
            // 1. Faz a requisição GET
            Response response = client.get("/hello");

            // 2. Validações
            assertAll(
                    () -> assertEquals("Hello, Javalin!", response.body().string(), "Mensagem deve ser exata")
            );
            assertEquals(200, response.code());
        });
    }

    @Test
    void testAnaFeijoliExisteNoRepositorio() {
        Mensalista ana = Repository.getAnaFeijoli();

        assertAll(
                () -> assertEquals("Ana Feijoli", ana.getNome()),
                () -> assertEquals(1002L, ana.getMatricula()),
                () -> assertEquals("Desenvolvedora Senior", ana.getCargo()),
                () -> assertEquals(9500.00, ana.getSalario()),
                () -> assertEquals("MENSALISTA", ana.getTipo())
        );
    }

    @Test
    void testCreateMensalista() throws Exception {
        Javalin app = new Main().initJavalinApp();

        String jsonMensalista = """
                {
                    "matricula": 13519251,
                    "nome": "Caio Fabio",
                    "cargo": "Analista Pleno",
                    "salario": 8500.00
                }
                """;

        JavalinTest.test(app, (server, client) -> {
            // 1. Faz a requisição POST
            Response response = client.post("/mensalistas", jsonMensalista);

            // 2. Validações
            assertAll(
                    () -> assertEquals(201, response.code(), "Deve retornar status 201"),
                    () -> assertEquals("application/json", response.header("Content-Type"), "Content-Type deve ser JSON"),
                    () -> assertTrue(response.body().string().contains("Caio Fabio"), "Resposta deve conter o nome do mensalista")
            );
        });
    }

    @Test
    void testBuscarMensalistaPorMatricula() throws Exception {
        Javalin app = new Main().initJavalinApp();
        Mensalista joaoSilva = Repository.getJoaoSilva();

        JavalinTest.test(app, (server, client) -> {
            // Busca pela matrícula
            Response response = client.get("/mensalistas/" + joaoSilva.getMatricula());

            // Validações
            assertEquals(200, response.code());
            assertTrue(response.body().string().contains(joaoSilva.getNome()));
        });
    }

    @Test
    void testListarTodosMensalistas() throws Exception {
        Javalin app = new Main().initJavalinApp();

        JavalinTest.test(app, (server, client) -> {
            // Busca todos os mensalistas
            Response response = client.get("/mensalistas");
            String responseBody = response.body().string();

            // Validações
            assertEquals(200, response.code());
            assertTrue(responseBody.startsWith("[") && responseBody.endsWith("]"));
            assertTrue(responseBody.contains("Ana Feijoli"));
            assertTrue(responseBody.contains("João Silva"));
        });
    }

}