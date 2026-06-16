package persistence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextFileManager {
    private final String filepath;

    public TextFileManager(String filepath) {
        this.filepath = filepath;
    }

    public List<String> lerLinhas() {
        try {
            return Files.readAllLines(Paths.get(filepath));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public String lerCompleto() {
        try {
            return new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            return "";
        }
    }

    public void escreverLinha(String linha, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, append))) {
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro de texto: " + filepath + " - " + e.getMessage());
        }
    }

    public void escreverLinhas(List<String> linhas, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, append))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro de texto: " + filepath + " - " + e.getMessage());
        }
    }

    public void limpar() {
        try (FileWriter fw = new FileWriter(filepath, false)) {
            fw.write("");
        } catch (IOException e) {
            System.err.println("Erro ao limpar ficheiro de texto: " + filepath + " - " + e.getMessage());
        }
    }

    public boolean existe() {
        return new File(filepath).exists();
    }
}
