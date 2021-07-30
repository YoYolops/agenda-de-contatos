package agenda;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos. 
 * 
 * @author nazareno
 * @author Yohan Lopes (https://github.com/YoYolops)
 *
 */
public class Agenda {
	
	private static final int TAMANHO_AGENDA = 100;
	private static final int TAMANHO_FAVORITOS = 5;
	
	private Contato[] contatos;
	private Contato[] favoritos;

	/**
	 * Cria uma agenda.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
		this.favoritos = new Contato[TAMANHO_FAVORITOS];
	}

	/**
	 * Função que exporta os favoritos
	 * @return clone do array de favoritos
	 */
	public Contato[] getFavoritos() {
		return this.favoritos.clone();
	}

	
	/**
	 * Acessa e retorna a lista de contatos mantida.
	 * @return Um clone do array de contatos.
	 */
	public Contato[] getContatos() {
		return this.contatos.clone();
	}

	/** 
	 * Verifica se o contato já existe
	 * @param nomeContato nome do contato a ser procurado
	 * @param sobrenomeContato sobrenome do contato a ser procurado
	 * @return Booleano true se o contato já existe, false caso não exista ainda
	*/
	public boolean contatoExiste(String nomeContato,
								 String sobrenomeContato)
	{
		for(Contato contato : this.contatos) {
			if(contato != null) {
				if((nomeContato.equals(contato.getNome())) && sobrenomeContato.equals(contato.getSobrenome())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Verifica se o favorito já existe no array de favoritos
	 * @param nomeContato nome do contato que se quer verificar
	 * @param sobrenomeContato sobrenome do contato que se quer verificar
	 * @return booleano true caso o contato já esteja nos favoritos, false caso não esteja
	 */
	public boolean favoritoExiste(String nomeContato,
								  String sobrenomeContato)
	{
		for(Contato favorito : this.favoritos) {
			if(favorito != null) {
				if((nomeContato.equals(favorito.getNome())) && sobrenomeContato.equals(favorito.getSobrenome())) {
					return true;
				}
			}
		}
		return false;
	}

	/** 
	 * Verifica se a posição informada está preenchida na agenda
	 * @param index posição da lista que se quer verificar
	 * @return booleano true, caso esteja vazia, false caso esteja preenchida
	 */ 
	public boolean isEmpty(int index) {
		if(this.contatos[index - 1] == null) {
			return true;
		}
		return false;
	}

	/**
	 * Exporta os dados de um contato específico.
	 * @param posicao Posição do contato na agenda.
	 * @return Dados do contato. Null se não há contato na posição.
	 */
	public Contato getContato(int posicao) {
		if (posicao > 100 || posicao < 1 || this.isEmpty(posicao)) { 
			throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
		}
		return this.contatos[posicao - 1];
	}

	/**
	 * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior. 
	 * @param posicao Posição do contato.
	 * @param nome Nome do contato.
	 * @param sobrenome Sobrenome do contato.
	 * @param telefone Telefone do contato.
	 */
	public void cadastraContato(int posicao,
								String nome,
								String sobrenome,
								String telefone)
	{
		if (posicao > 100 || posicao < 1) { throw new IllegalArgumentException("POSIÇÃO INVÁLIDA"); }
		if (nome == "") { throw new IllegalArgumentException("CONTATO INVÁLIDO"); }
		if (telefone == "") { throw new IllegalArgumentException("TELEFONE INVÁLIDO"); }
		if (this.contatoExiste(nome, sobrenome)) { throw new IllegalArgumentException("CONTATO JÁ CADASTRADO"); }

		Contato novoContato = new Contato(nome, sobrenome, telefone); 
		this.contatos[posicao - 1] = novoContato;
	}

	/**
	 * Adiciona contato no array de favoritos
	 * @param contato contato a ser favoritado
	 * @param indexFavoritosEmQueSeraArmazenado index do array favoritos em que será armazenado
	 */
	public void cadastraFavorito(Contato contato, int indexFavoritosEmQueSeraArmazenado) {
		String nome = contato.getNome();
		String sobrenome = contato.getSobrenome();

		if(this.favoritoExiste(nome, sobrenome)) { throw new IllegalArgumentException("FAVORITO JÁ EXISTE"); }
		this.favoritos[indexFavoritosEmQueSeraArmazenado - 1] = contato;
	}

	/** 
	 * Direciona as requisições para cadastro de tags aos contatos especificados pelo usuário
	 * 
	 * @param indexDeContato array com os indexes dos contatos que devem adicionar tags aos seus dados internos
	 * @param tag a tag que sera cadastrada pelo(s) contatos
	 * @param posicaoDaTag em que indexes do array de contatos a tag deve ser armazenada
	 * 
	 * @return void
	 */
	public void requisitaCadastroDeTag(int[] indexesDeContato, // os index já vêm tratados, ou seja, o dado de input subtraído em 1
									   String tag,
									   int posicaoDaTag)
	{
		for(int index : indexesDeContato) {
			this.contatos[index - 1].adicionaTag(posicaoDaTag, tag);
		}
	}
}