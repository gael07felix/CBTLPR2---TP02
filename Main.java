import javax.swing.SwingUtilities;

/**
 * Classe de entrada da aplicacao.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroAlunos tela = new CadastroAlunos();
            tela.setVisible(true);
        });
    }
}
