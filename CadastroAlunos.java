import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Gael Felix CB3038912

/**
 * Formulario grafico para cadastro de alunos.
 */
public class CadastroAlunos extends JFrame {
    private final JTextField txtNome;
    private final JTextField txtIdade;
    private final JTextField txtEndereco;

    // A List e a ArrayList sao usadas para manter os alunos em memoria.
    private final List<Aluno> alunos;

    public CadastroAlunos() {
        super("TP02 - LP24");

        alunos = new ArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 180);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel superior: GridLayout 3x2 com hgap e vgap de 10.
        JPanel painelSuperior = new JPanel(new GridLayout(3, 2, 10, 10));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblIdade = new JLabel("Idade:");
        JLabel lblEndereco = new JLabel("Endereco:");

        txtNome = new JTextField();
        txtIdade = new JTextField();
        txtEndereco = new JTextField();

        painelSuperior.add(lblNome);
        painelSuperior.add(txtNome);
        painelSuperior.add(lblIdade);
        painelSuperior.add(txtIdade);
        painelSuperior.add(lblEndereco);
        painelSuperior.add(txtEndereco);

        // Painel inferior: quatro botoes em GridLayout.
        JPanel painelInferior = new JPanel(new GridLayout(1, 4, 10, 0));
        painelInferior.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        JButton btnOk = new JButton("Ok");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnSair = new JButton("Sair");

        // Aumenta um pouco a area interna dos botoes.
        btnOk.setMargin(new Insets(2, 8, 2, 8));
        btnLimpar.setMargin(new Insets(2, 8, 2, 8));
        btnMostrar.setMargin(new Insets(2, 8, 2, 8));
        btnSair.setMargin(new Insets(2, 8, 2, 8));

        painelInferior.add(btnOk);
        painelInferior.add(btnLimpar);
        painelInferior.add(btnMostrar);
        painelInferior.add(btnSair);

        // BorderLayout e usado para posicionar os dois paineis.
        setLayout(new BorderLayout());
        add(painelSuperior, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        // Botao Ok: cria um Aluno e armazena na lista em memoria.
        btnOk.addActionListener(e -> cadastrarAluno());

        // Botao Limpar: apaga o conteudo dos campos do formulario.
        btnLimpar.addActionListener(e -> limparCampos());

        // Botao Mostrar: exibe o popup com todos os IDs e nomes cadastrados.
        btnMostrar.addActionListener(e -> mostrarAlunos());

        // Botao Sair: encerra a aplicacao.
        btnSair.addActionListener(e -> System.exit(0));
    }

    private void cadastrarAluno() {
        String nome = txtNome.getText().trim();
        String idadeTexto = txtIdade.getText().trim();
        String endereco = txtEndereco.getText().trim();

        if (nome.isEmpty() || idadeTexto.isEmpty() || endereco.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Preencha Nome, Idade e Endereco.",
                "Dados incompletos",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(idadeTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "A idade deve ser um numero inteiro.",
                "Idade invalida",
                JOptionPane.ERROR_MESSAGE
            );
            txtIdade.requestFocus();
            return;
        }

        if (idade < 0) {
            JOptionPane.showMessageDialog(
                this,
                "A idade nao pode ser negativa.",
                "Idade invalida",
                JOptionPane.ERROR_MESSAGE
            );
            txtIdade.requestFocus();
            return;
        }

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        aluno.setEndereco(endereco);

        alunos.add(aluno);

    }

    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        txtEndereco.setText("");
        txtNome.requestFocus();
    }

    private void mostrarAlunos() {
        String mensagem;

        if (alunos.isEmpty()) {
            mensagem = "Resultado\nNenhum aluno cadastrado nesta execucao.";
        } else {
            StringBuilder sb = new StringBuilder("Resultado\n");

            for (Aluno aluno : alunos) {
                sb.append("Id: ")
                  .append(aluno.getUuid())
                  .append("  Nome: ")
                  .append(aluno.getNome())
                  .append("\n");
            }

            mensagem = sb.toString();
        }

        // Formato pedido no enunciado.
        JOptionPane.showMessageDialog(this, mensagem);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            CadastroAlunos tela = new CadastroAlunos();
            tela.setVisible(true);
        });
    }
}
