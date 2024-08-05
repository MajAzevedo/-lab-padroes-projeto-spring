package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.repository.ClienteRepository;
import one.digitalinnovation.gof.repository.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser injetada 
 * pelo Spring (via {@link Autowired}). Com isso, como essa classe é uma {@link Service},
 * ela será tratada como uma <b>Singleton</b>
 * 
 */
@Service
public class ClienteServiceImpl implements ClienteService {
	
	//Singleton: Injetar todos os componentes do Spring com @Autowired.
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ViaCepService viaCepService;

	
	//TODO Strategy: Implementar os métodos definidos na interface.
	//TODO Facade: Abstrair integrações com subsistemas, provendo uma interface simples.
	
	@Override
	public Iterable<Cliente> buscarTodos() {
		return repository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> optCliente = repository.findById(id);
		return optCliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarClientecomCep(cliente);

	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteAtualizado = repository.findById(id);
		if(clienteAtualizado.isPresent()) {
			salvarClientecomCep(cliente);
		}
	}

	@Override
	public void deletar(Long id) {
		repository.deleteById(id);

	}

	private void salvarClientecomCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco enderecoNovo = viaCepService.consultarCep(cep);
			enderecoRepository.save(enderecoNovo);
			return enderecoNovo;
	});
		cliente.setEndereco(endereco);
		repository.save(cliente);
	}

	
}
