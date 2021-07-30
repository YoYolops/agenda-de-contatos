package agenda;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AgendaTest {
	@Test
	public void testCadastraContato() {
		final Agenda agenda = new Agenda();
		agenda.cadastraContato(100, "Yohan", "Lopes", "(83) 99999-9999"); // o usuário pode ddigitar 100, pois o main trata esse dado, mas a agenda lida com valores reais de index apenas
		Contato contatoCriado = agenda.getContato(100); 
		String nomeContatoObtido = contatoCriado.getNome();
		String sobrenomeContatoObtido = contatoCriado.getSobrenome();
		String telefoneContatoObtido = contatoCriado.getTelefone();

		String nomeContatoEsperado = "Yohan";
		String sobrenomeContatoEsperado = "Lopes";
		String telefoneContatoEsperado = "(83) 99999-9999";

		assertEquals(nomeContatoEsperado, nomeContatoObtido);
		assertEquals(sobrenomeContatoEsperado, sobrenomeContatoObtido);
		assertEquals(telefoneContatoEsperado, telefoneContatoObtido);
	}

	@Test
	public void testCadastroPosicaoLimite() {
		Agenda agenda = new Agenda();
		agenda.cadastraContato(1, "Yohan", "Lopes", "(83) 99999-9999");
		Contato contato = agenda.getContato(1);

		String nomeObtido = contato.getNome();
		String sobrenomeObtido = contato.getSobrenome();
		String telefoneObtido = contato.getTelefone();

		assertEquals("Yohan", nomeObtido);
		assertEquals("Lopes", sobrenomeObtido);
		assertEquals("(83) 99999-9999", telefoneObtido);
	}

	@Test
	public void testCadastroContatoPosicaoPreenchida() {
		final Agenda agenda = new Agenda();
		agenda.cadastraContato(100, "Yohan", "Lopes", "(83) 99999-9999");
		agenda.cadastraContato(100, "João", "Silva", "(83) 40028922");
		Contato contato = agenda.getContato(100);
		String nomeContatoObtido = contato.getNome();
		String sobrenomeContatoObtido = contato.getSobrenome();
		String telefoneContatoObtido = contato.getTelefone();

		assertEquals("João", nomeContatoObtido);
		assertEquals("Silva", sobrenomeContatoObtido);
		assertEquals("(83) 40028922", telefoneContatoObtido);
	}

	@Test
	public void testCadastraContatoExistente() {
		final Agenda agenda = new Agenda();
		agenda.cadastraContato(100, "Yohan", "Lopes", "(83) 99999-9999");
		assertThrows(IllegalArgumentException.class, () -> agenda.cadastraContato(8, "Yohan", "Lopes", "(83) 99999-9999"));
	}

	@Test
	public void testCadastraContatoAcimaLimite() {
		Agenda agenda = new Agenda();
		assertThrows(IllegalArgumentException.class, () -> agenda.cadastraContato(101, "Yohan", "Lopes", "(83) 40028922"));
	}

	@Test
	public void cadastraContatoAbaixoLimite() {
		Agenda agenda = new Agenda();
		assertThrows(IllegalArgumentException.class, () -> agenda.cadastraContato(101, "Yohan", "Lopes", "(83) 40028922"));
	}

	@Test
	public void cadastraContatoSemTelefone() {
		Agenda agenda = new Agenda();
		assertThrows(IllegalArgumentException.class, () -> agenda.cadastraContato(50, "Yohan", "Lopes", ""));
	}

	@Test 
	public void testCadastraContatoSemNome() {
		Agenda agenda = new Agenda();
		assertThrows(IllegalArgumentException.class, () -> agenda.cadastraContato(50, "", "Lopes", "12345678"));
	}

	@Test
	public void testExibirContatoCadastrado() {
		Agenda agenda = new Agenda();
		agenda.cadastraContato(10, "Yohan", "Lopes", "(83) 40028922");
		String representacaoContatoEsperada = "Yohan Lopes\n(83) 40028922\n";
		String representacaoContatoObtida = agenda.getContato(10).toString();
		assertEquals(representacaoContatoEsperada, representacaoContatoObtida);
	}

	@Test
	public void testExibeContatoInexistente() {
		Agenda agenda = new Agenda();
		assertThrows(IllegalArgumentException.class, () -> agenda.getContato(100));
	}

	@Test
	public void testExibeContatoPosicaoInvalidaInferior() {
		Agenda agenda = new Agenda();
		assertThrows(IllegalArgumentException.class, () -> agenda.getContato(0));
	}

	@Test
	public void testExibeContatoPosicaoInvalidaSuperior() {
		Agenda agenda = new Agenda();
		assertThrows(IllegalArgumentException.class, () -> agenda.getContato(101));
	}

	@Test
	public void testCadastrarContatoFavorito() {
		Agenda agenda = new Agenda();
		agenda.cadastraContato(10, "Yohan", "Lopes", "(83) 40028922");
		Contato contato = agenda.getContato(10);
		agenda.cadastraFavorito(contato, 1);

		String nomeFavorito = contato.getNome();
		String sobrenomeFavorito = contato.getSobrenome();

		assertEquals(true, agenda.favoritoExiste(nomeFavorito, sobrenomeFavorito));
	}

	@Test
	public void testExibirContatoComTags() {
		Agenda agenda = new Agenda();
		int[] indexesDeContato = {1};
		agenda.cadastraContato(1, "Yohan", "Lopes", "(83) 40028922");
		agenda.requisitaCadastroDeTag(indexesDeContato, "teste", 1);


		String representacaoContatoEsperada = "Yohan Lopes\n(83) 40028922\nteste";
		String representacaoContatoObtida = agenda.getContato(1).toString();

		assertEquals(representacaoContatoEsperada, representacaoContatoObtida);

	}
 }
