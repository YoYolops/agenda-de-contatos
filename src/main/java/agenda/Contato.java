package agenda;

public class Contato {
    private String nome;
    private String sobrenome;
    private String telefone;
    private String[] tags = new String[5];

    public Contato(String nome, String sobrenome, String telefone) {
        if (nome == "" || nome == null) { throw new IllegalArgumentException("Nome Inválido"); }
        if (sobrenome == "" || sobrenome == null) { throw new IllegalArgumentException("Sobrenome inválido"); }
        if (telefone == "" || telefone == null) { throw new IllegalArgumentException("Telefone Inválido"); }
    
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
    }

    /** 
     * método que adiciona tags ao array de tags
     * @param posicaoOndeAdicionar index onde deve ser adicionada a tag
     * @param nomeDaTag a string que será armazenada
     * @return void
     */
    public void adicionaTag(int posicaoOndeAdicionar, String nomeDaTag) { //Já que não foi pedido, Contato não se importa em cadastrar
        if (posicaoOndeAdicionar > 5 || posicaoOndeAdicionar < 1) {      // a mesma tag várias vezes
            throw new IllegalArgumentException("Posição Inválida");
        }
        if (nomeDaTag == "" || nomeDaTag == null) { throw new IllegalArgumentException("Nome de Tag Inválido"); }
        this.tags[posicaoOndeAdicionar - 1] = nomeDaTag;
    }

    /**
     * Método que exporta dados de tags
     * @return um clone do array de tags
     */
    public String[] getTags() {
        return this.tags.clone();
    }

    /** 
     * função para acesso de dados internos (nome)
     * @return o nome do objeto usuário
     */
    public String getNome() {
        return this.nome;
    }

    /** 
     * função para acesso de dados internos (sobrenome)
     * @return o sobrenome do obejto usuário
     */
    public String getSobrenome() {
        return this.sobrenome;
    }

    /** 
     * função para acesso de dados internos (telefone)
     * @return o telefone do obejto usuário
     */
    public String getTelefone() {
        return this.telefone;
    }

    /**
     * constrói a representação do objeto em string
     * @return String com informações da instância 
     */
    public String toString() {
        String stringTags = "";

        for(int i = 0; i < tags.length; i++) {
            if(tags[i] == null) { continue; }// pula pra próxima iteração
            stringTags += (tags[i] + " ");
        }

        return this.nome + " " + this.sobrenome + "\n" + this.telefone + "\n" +  stringTags.trim();
    }
}

