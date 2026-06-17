package gestao;

import model.Diretor;
import persistence.FileManager;

import java.util.ArrayList;
import java.util.List;

public class GereDiretor {
    private final FileManager<Diretor> fm;
    private final List<Diretor> lista;

    public GereDiretor(String filepath) {
        this.fm = new FileManager<>(filepath);
        this.lista = fm.ler();
    }

    public void adicionar(Diretor d) {
        lista.add(d);
        fm.escrever(lista);
    }

    public List<Diretor> listar() {
        return new ArrayList<>(lista);
    }

    public void atualizar(int index, Diretor d) {
        if (valido(index)) {
            lista.set(index, d);
            fm.escrever(lista);
        }
    }

    public void remover(int index) {
        if (valido(index)) {
            lista.remove(index);
            fm.escrever(lista);
        }
    }

    public void guardar() {
        fm.escrever(lista);
    }

    private boolean valido(int index) {
        return index >= 0 && index < lista.size();
    }
}
