package cat.barcelonactiva.itacademy.models.entity.services;

import java.util.List;

import cat.barcelonactiva.itacademy.model.entity.Empleat;

public interface IEmpleatService {
	
	public List<Empleat> listarTodos();
	public void guardar(Empleat empl);
	public Empleat buscarPorId(Long id);
	public void eliminar(Long id);

}
