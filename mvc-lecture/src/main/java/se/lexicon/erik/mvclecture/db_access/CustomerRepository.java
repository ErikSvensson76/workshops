package se.lexicon.erik.mvclecture.db_access;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.erik.mvclecture.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
