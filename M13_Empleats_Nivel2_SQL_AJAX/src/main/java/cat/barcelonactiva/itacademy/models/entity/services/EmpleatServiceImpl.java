package cat.barcelonactiva.itacademy.models.entity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.barcelonactiva.itacademy.model.entity.Empleat;
import cat.barcelonactiva.itacademy.repository.IEmpleatRepository;

@Service
public class EmpleatServiceImpl implements IEmpleatService {
	
	@Autowired
	private IEmpleatRepository repo;

	@Override
	public List<Empleat> listarTodos() {
		return (List<Empleat>) repo.findAll();
	}

	@Override
	public void guardar(Empleat empl) {
		repo.save(empl);
		
	}

	@Override
	public Empleat buscarPorId(Long id) {
		Optional<Empleat> opt = repo.findById(id);
		
		return opt.isPresent() ? opt.get() : new Empleat();
	}

	@Override
	public void eliminar(Long id) {
		repo.deleteById(id);
		
	}
}
