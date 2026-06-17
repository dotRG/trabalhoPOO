package gestao;

import model.ClienteEspecial;
import persistence.FileManager;

import java.util.ArrayList;
import java.util.List;

public class GereCliente {
    private final FileManager<ClienteEspecial> fm;
    private final List<ClienteEspecial> lista;

    public GereCliente(String filepath) {
        this.fm = new FileManager<>(filepath);
        this.lista = fm.ler();
    }

    public void adicionar(ClienteEspecial c) {
        lista.add(c);
        fm.escrever(lista);
    }

    public List<ClienteEspecial> listar() {
        return new ArrayList<>(lista);
    }

    public void atualizar(int index, ClienteEspecial c) {
        if (valido(index)) {
            lista.set(index, c);
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
