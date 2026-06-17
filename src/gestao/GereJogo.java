package gestao;

import model.Jogo;
import persistence.FileManager;

import java.util.ArrayList;
import java.util.List;

public class GereJogo {
    private final FileManager<Jogo> fm;
    private final List<Jogo> lista;

    public GereJogo(String filepath) {
        this.fm = new FileManager<>(filepath);
        this.lista = fm.ler();
    }

    public void adicionar(Jogo j) {
        lista.add(j);
        fm.escrever(lista);
    }

    public List<Jogo> listar() {
        return new ArrayList<>(lista);
    }

    public void atualizar(int index, Jogo j) {
        if (valido(index)) {
            lista.set(index, j);
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
