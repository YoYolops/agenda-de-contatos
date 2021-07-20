package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author nazarenoandrade
 *
 */
public class MainAgenda {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.println(
				"\n---\nMENU\n" + 
						"(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" + 
						"(E)xibir Contato\n" + 
						"(F)avoritos\n" +
						"(A)dicionar Favorito\n" +
						"(S)air\n" +
						"\n" + 
						"Opção> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			cadastraContato(agenda, scanner);
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			exibeContato(agenda, scanner);
			break;
		case "F":
			exibeFavoritos(agenda);
			break;
		case "A":
			cadastraFavorito(agenda, scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}

	/** 
	 * Imprime todos os contatos cadastrados como favoritos
	 * @param agenda agenda de contatos que deve ser analisada
	 */
	private static void exibeFavoritos(Agenda agenda) {
		Contato[] favoritos = agenda.getFavoritos();

		for(int i = 0; i < favoritos.length; i++) {
			if(favoritos[i] != null) {
				System.out.println(formataContato((i + 1), favoritos[i]));
			}
		}
	}

	/**
	 * Imprime lista de contatos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 * @param listaFavoritos define se deve listar os favoritos (true) ou os contatos normais (false)
	 */
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		Contato[] contatos =agenda.getContatos();

		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] != null) {
				System.out.println(formataContato(i, contatos[i]));
			}
		}
	}

	/**
	 * Imprime os detalhes de um dos contatos da agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual contato> ");
		int posicao = scanner.nextInt();

		if(posicao > 100 || posicao < 1 || agenda.isEmpty(posicao)) {
			System.out.println("POSIÇÃO INVÁLIDA");
		} else {
			Contato contato = agenda.getContato(posicao - 1);

			String nomeContato = contato.getNome();
			String sobrenomeContato = contato.getSobrenome();

			if(agenda.favoritoExiste(nomeContato, sobrenomeContato)) { //verifica se o contato é um favorito
				System.out.println("❤️ " + contato);
			} else {
				System.out.println(contato);
			}
		}

	}

	/**
	 * Formata um contato para impressão na interface. 
	 * 
	 * @param posicao A posição do contato (que é exibida)/
	 * @param contato O contato a ser impresso.
	 * @return A String formatada.
	 */
	private static String formataContato(int posicao, Contato contato) {
		String nome = contato.getNome();
		String sobrenome = contato.getSobrenome();

		return posicao + " - " + nome + " " + sobrenome;
	}

	/**
	 * Cadastra um contato na agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 * @return void
	 */
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosição> ");
		int posicao = scanner.nextInt();
		System.out.print("\nNome> ");
		String nome = scanner.next();
		System.out.print("\nSobrenome> ");
		String sobrenome = scanner.next();
		System.out.print("\nTelefone> ");
		String telefone = scanner.next();

		if(posicao < 1 || posicao > 100) { System.out.println("POSIÇÃO INVÁLIDA"); return; }
		if(agenda.contatoExiste(nome, sobrenome)) { System.out.println("CONTATO JA CADASTRADO"); return; }
		if(nome == "") { System.out.println("CONTATO INVALIDO"); return; }
		if(telefone == "") { System.out.println("CONTATO INVALIDO"); return; }

		agenda.cadastraContato((posicao - 1), nome, sobrenome, telefone);
	}

	/**
	 * Cadastra um contato já existente no array contatos como favorito
	 * @param agenda a agenda em que o contato está cadastrado
	 * @param scanner Scanner para entrada de dados a serem cadastrados
	 * @return void
	 */
	public static void cadastraFavorito(Agenda agenda, Scanner scanner) {
		System.out.println("Contato> ");
		int posicaoDoContato = scanner.nextInt();
		System.out.println("Posicao> ");
		int posicaoEmQueSeraCadastrado = scanner.nextInt();

		Contato[] contatos = agenda.getContatos();
		String nomeContatoSendoCadastrado = contatos[posicaoDoContato - 1].getNome();
		String sobrenomeContatoSendoCadastrado = contatos[posicaoDoContato - 1].getSobrenome();

		// verifica se as informações fornecidas estão dentro dos padrões
		if(posicaoDoContato > 100 || posicaoDoContato < 1) { System.out.println("CONTATO INVALIDO"); return; }
		if(posicaoEmQueSeraCadastrado > 5 || posicaoEmQueSeraCadastrado < 1) { System.out.println("POSIÇÃO INVÁLIDA"); return; }
		if(agenda.favoritoExiste(nomeContatoSendoCadastrado, sobrenomeContatoSendoCadastrado)) { System.out.println("CONTATO JA CADASTRADO"); return; }

		agenda.cadastraFavorito(contatos[posicaoDoContato - 1], posicaoEmQueSeraCadastrado);
	}

	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Lê uma agenda de um arquivo csv. 
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda A agenda que deve ser populada com os dados. 
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}
}
