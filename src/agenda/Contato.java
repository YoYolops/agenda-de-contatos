package agenda;

public class Contato {
    private String nome;
    private String sobrenome;
    private String telefone;
    private String[] tags = new String[5];

    public Contato(String nome, String sobrenome, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
    }

    /** 
     * @return o nome do obejto usuário
     */
    public String getNome() {
        return this.nome;
    }

    /** 
     * @return o sobrenome do obejto usuário
     */
    public String getSobrenome() {
        return this.sobrenome;
    }

    /** 
     * @return o telefone do obejto usuário
     */
    public String getTelefone() {
        return this.telefone;
    }

    public String toString() {
        return this.nome + " " + this.sobrenome + "\n" + this.telefone;
    }
}

