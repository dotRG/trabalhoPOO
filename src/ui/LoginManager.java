package ui;

import utils.CryptoUtils;
import persistence.TextFileManager;

public class LoginManager {
    private static final String PASS_FILE = "data/pass.txt";
    private final TextFileManager tfm;

    public LoginManager() {
        this.tfm = new TextFileManager(PASS_FILE);
    }

    public boolean existePassword() {
        return tfm.existe() && !tfm.lerCompleto().trim().isEmpty();
    }

    public void definirPasswordDefault(String password) {
        if (!existePassword()) {
            tfm.escreverLinha(password, false);
        }
    }

    public boolean autenticarAdmin(String password) {
        if (!existePassword()) {
            return false;
        }
        String conteudo = tfm.lerCompleto().trim();

        // Se a password ainda não estiver encriptada, encripta agora
        if (conteudo.length() < 44) { // SHA-256 Base64 tem ~44 chars
            if (conteudo.equals(password)) {
                String hash = CryptoUtils.hashPassword(password);
                tfm.limpar();
                tfm.escreverLinha(hash, false);
                return true;
            }
            return false;
        }

        return CryptoUtils.verifyPassword(password, conteudo);
    }

    public void alterarPassword(String novaPassword) {
        String hash = CryptoUtils.hashPassword(novaPassword);
        tfm.limpar();
        tfm.escreverLinha(hash, false);
    }
}
