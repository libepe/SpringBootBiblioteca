package proyecto.liam.SpringBootBiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.liam.SpringBootBiblioteca.entidad.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
	// Dentro del repositorio podemos crear consultas de una manera 'mágica'
    List<Genero> findByNombreContaining(String nombre);
	

}
