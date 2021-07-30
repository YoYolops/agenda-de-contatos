package agenda;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContatoTest {
	@Test
	public void testCriaContato() {
		Contato contato = new Contato("Yohan", "Yoyo", "123456789");
		String nomeContato = contato.getNome();
		String sobrenomeContato = contato.getSobrenome();
		String telefoneContato = contato.getTelefone();

		assertEquals("Yohan", nomeContato);
		assertEquals("Yoyo", sobrenomeContato);
		assertEquals("123456789", telefoneContato);
	}

	@Test
	public void testCriaContatoSemNome() {
		assertThrows(IllegalArgumentException.class, () -> new Contato("", "yoyo", "1234"));
	}

	@Test
	public void testCriaContatoSemSobrenome() {
		assertThrows(IllegalArgumentException.class, () -> new Contato("Yohan", "", "1234"));
	}

	@Test
	public void testCriaContatoSemTelefone() {
		assertThrows(IllegalArgumentException.class, () -> new Contato("Yohan", "yoyo", ""));
	}

	@Test
	public void testAdicionaTagSemNome() {
		Contato contato = new Contato("Yohan", "Yoyo", "123456789");
		assertThrows(IllegalArgumentException.class, () -> contato.adicionaTag(1, ""));
	}

	@Test
	public void testAdicionaTagPosicaoInvalidaInferior() {
		Contato contato = new Contato("Yohan", "Yoyo", "123456789");
		assertThrows(IllegalArgumentException.class, () -> contato.adicionaTag(0, "yoyo"));
	}

	@Test
	public void testAdicionaTagPosicaoInvalidaSuperior() {
		Contato contato = new Contato("Yohan", "Yoyo", "123456789");
		assertThrows(IllegalArgumentException.class, () -> contato.adicionaTag(6, "yoyo"));
	}

	@Test
	public void testExibeContato() {
		Contato contato = new Contato("Yohan", "Yoyo", "123456789");
		String contatoToStringObtido = contato.toString();
		String contatoToStringEsperado = "Yohan Yoyo\n123456789\n";

		assertEquals(contatoToStringEsperado, contatoToStringObtido);
	}

	@Test
	public void testExibeContatoComTags() {
		Contato contato = new Contato("Yohan", "Yoyo", "123456789");
		contato.adicionaTag(1, "yoyo");
		contato.adicionaTag(5, "yoyo2");

		String contatoToStringObtido = contato.toString();
		String contatoToStringEsperado = "Yohan Yoyo\n123456789\nyoyo yoyo2";

		assertEquals(contatoToStringEsperado, contatoToStringObtido);
	}
}
