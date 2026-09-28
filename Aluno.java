import java.util.UUID;

/**
 * Representa um aluno cadastrado no sistema.
 */
public class Aluno {
    private String endereco;
    private int idade;
    private String nome;
    private UUID uuid;

    public Aluno() {
        this.uuid = UUID.randomUUID();
    }

    public Aluno(String nome, int idade, String endereco) {
        this.uuid = UUID.randomUUID();
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
