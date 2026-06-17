package gestao;

import model.Promocao;
import persistence.FileManager;

import java.util.ArrayList;
import java.util.List;

public class GerePromocao {
    private final FileManager<Promocao> fm;
    private final List<Promocao> lista;

    public GerePromocao(String filepath) {
        this.fm = new FileManager<>(filepath);
        this.lista = fm.ler();
    }

    public void adicionar(Promocao p) {
        lista.add(p);
        fm.escrever(lista);
    }

    public List<Promocao> listar() {
        return new ArrayList<>(lista);
    }

    public void atualizar(int index, Promocao p) {
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
