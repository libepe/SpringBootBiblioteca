package proyecto.liam.SpringBootBiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.liam.SpringBootBiblioteca.entidad.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

	List<Autor> findByLibrosTituloContaining(String cadena);

}
