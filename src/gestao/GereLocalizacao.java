package gestao;

import model.Localizacao;
import persistence.FileManager;

import java.util.ArrayList;
import java.util.List;

public class GereLocalizacao {
    private final FileManager<Localizacao> fm;
    private final List<Localizacao> lista;

    public GereLocalizacao(String filepath) {
        this.fm = new FileManager<>(filepath);
        this.lista = fm.ler();
    }

    public void adicionar(Localizacao l) {
        lista.add(l);
        fm.escrever(lista);
    }

    public List<Localizacao> listar() {
        return new ArrayList<>(lista);
    }

    public void atualizar(int index, Localizacao l) {
        if (valido(index)) {
            lista.set(index, l);
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
