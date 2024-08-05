package one.digitalinnovation.gof.repository;

import org.springframework.data.repository.CrudRepository;

import one.digitalinnovation.gof.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
