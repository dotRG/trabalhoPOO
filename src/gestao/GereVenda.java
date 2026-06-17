package gestao;

import model.Venda;
import persistence.FileManager;

import java.util.ArrayList;
import java.util.List;

public class GereVenda {
    private final FileManager<Venda> fm;
    private final List<Venda> lista;

    public GereVenda(String filepath) {
        this.fm = new FileManager<>(filepath);
        this.lista = fm.ler();
    }

    public void adicionar(Venda v) {
        lista.add(v);
        fm.escrever(lista);
    }

    public List<Venda> listar() {
        return new ArrayList<>(lista);
    }

    public void guardar() {
        fm.escrever(lista);
    }
}
