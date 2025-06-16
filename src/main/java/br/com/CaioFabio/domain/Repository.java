package br.com.CaioFabio.domain;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    public static final String HELLO = "Hello, Javalin!";

    private static final List<Mensalista> mensalistas = new ArrayList<>();

    static {
        criarMensalistaPadrao();
    }

    private static void criarMensalistaPadrao() {
        Mensalista anaFeijoli = new Mensalista(
                1002L,
                "Ana Feijoli",
                "Desenvolvedora Senior",
                9500.00
        );
        anaFeijoli.setTipo("MENSALISTA");
        mensalistas.add(anaFeijoli);


        Mensalista joaoSilva = new Mensalista(
                12345L,
                "João Silva",
                "Desenvolvedor",
                5000.00
        );
        joaoSilva.setTipo("MENSALISTA");
        mensalistas.add(joaoSilva);
    }

    public static void criarMensalista(Mensalista mensalista) {
        mensalistas.add(mensalista);
    }

    public static List<Mensalista> getMensalistas() {
        return new ArrayList<>(mensalistas);
    }

    public static Mensalista getAnaFeijoli() {
        return mensalistas.stream()
                .filter(m -> m.getNome().equals("Ana Feijoli"))
                .findFirst()
                .orElseThrow();
    }

    public static Mensalista getJoaoSilva() {
        return mensalistas.stream()
                .filter(m -> m.getNome().equals("João Silva"))
                .findFirst()
                .orElseThrow();
    }

    public static Mensalista getMensalistaPorMatricula(long matricula) {
        return mensalistas.stream()
                .filter(m -> m.getMatricula() == matricula)
                .findFirst()
                .orElse(null);
    }
}