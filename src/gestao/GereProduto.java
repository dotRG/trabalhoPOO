package gestao;

import model.Produto;
import persistence.FileManager;

import java.util.ArrayList;
import java.util.List;

public class GereProduto {
    private final FileManager<Produto> fm;
    private final List<Produto> lista;

    public GereProduto(String filepath) {
        this.fm = new FileManager<>(filepath);
        this.lista = fm.ler();
    }

    public void adicionar(Produto p) {
        lista.add(p);
        fm.escrever(lista);
    }

    public List<Produto> listar() {
        return new ArrayList<>(lista);
    }

    public void atualizar(int index, Produto p) {
        if (valido(index)) {
            lista.set(index, p);
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
