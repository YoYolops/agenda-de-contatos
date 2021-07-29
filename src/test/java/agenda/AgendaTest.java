package agenda;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class AgendaTest {
	@Test
	public void testCadastraContato() {
		final Agenda agenda = new Agenda();
		//testando cadastro em posição vazia:
		agenda.cadastraContato(99, "Yohan", "Lopes", "(83) 99999-9999"); // o usuário pode ddigitar 100, pois o main trata esse dado, mas a agenda lida com valores reais de index apenas
		Contato contatoCriado = agenda.getContato(99); 
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
}
