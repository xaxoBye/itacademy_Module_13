package cat.barcelonactiva.itacademy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cat.barcelonactiva.itacademy.model.entity.Empleat;

@Repository
public interface IEmpleatRepository extends CrudRepository<Empleat, Long>{

}
