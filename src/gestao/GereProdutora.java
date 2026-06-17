package gestao;

import model.Produtora;
import persistence.FileManager;

import java.util.ArrayList;
import java.util.List;

public class GereProdutora {
    private final FileManager<Produtora> fm;
    private final List<Produtora> lista;

    public GereProdutora(String filepath) {
        this.fm = new FileManager<>(filepath);
        this.lista = fm.ler();
    }

    public void adicionar(Produtora p) {
        lista.add(p);
        fm.escrever(lista);
    }

    public List<Produtora> listar() {
        return new ArrayList<>(lista);
    }

    public void atualizar(int index, Produtora p) {
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
