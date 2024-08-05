package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Cliente;


/**
 * interface que define o padrão <b>Strategy</b> no dominio de clientes. Com
 * isso, se necessário, podemos ter múltiplas implementações dessa mesma interface.
 * 
 * @author Marcelo
 */
public interface ClienteService {
	
	Iterable<Cliente> buscarTodos();
	
	Cliente buscarPorId(Long id);
	
	void inserir(Cliente cliente);
	
	void atualizar(Long id, Cliente cliente);
	
	void deletar(Long id);

}
