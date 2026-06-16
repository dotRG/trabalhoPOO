package persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager<T> {
    private final String filepath;

    public FileManager(String filepath) {
        this.filepath = filepath;
    }

    @SuppressWarnings("unchecked")
    public List<T> ler() {
        File f = new File(filepath);
        if (!f.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<T>) ois.readObject();
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler ficheiro de objetos: " + filepath + " - " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void escrever(List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Erro ao escrever ficheiro de objetos: " + filepath + " - " + e.getMessage());
        }
    }
}
