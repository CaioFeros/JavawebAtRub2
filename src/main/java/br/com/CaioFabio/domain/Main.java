package br.com.CaioFabio.domain;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.Map;

public class Main {
    private Javalin app;

    public Javalin initJavalinApp() {
        app = Javalin.create();
        configureRoutes();
        return app;
    }

    private void configureRoutes() {
        app.get("/hello", ctx -> ctx.html(Repository.HELLO));
        app.get("/mensalistas", ctx -> ctx.json(Repository.getMensalistas()));
        app.get("/mensalistas/{matricula}", this::buscarMensalistaPorMatricula);
        app.post("/mensalistas", this::criarMensalista);
}

private void criarMensalista(Context ctx) {
    Mensalista mensalista = ctx.bodyAsClass(Mensalista.class);
    mensalista.setTipo("MENSALISTA");
    Repository.criarMensalista(mensalista);
    ctx.status(HttpStatus.CREATED).json(mensalista);
}

    private void buscarMensalistaPorMatricula(Context ctx) {
        long matricula = Long.parseLong(ctx.pathParam("matricula"));
        Mensalista mensalista = Repository.getMensalistaPorMatricula(matricula);

        if (mensalista != null) {
            ctx.json(mensalista);
        } else {
            ctx.status(HttpStatus.NOT_FOUND);
        }
    }

    public static void main(String[] args) {
        new Main().initJavalinApp().start(7000);
    }


}