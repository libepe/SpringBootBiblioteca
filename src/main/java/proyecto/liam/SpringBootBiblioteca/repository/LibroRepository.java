package proyecto.liam.SpringBootBiblioteca.repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.liam.SpringBootBiblioteca.entidad.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer>{
	List<Libro> findByPaginasBetweenOrderByPaginasAsc(Integer a, Integer b);

	List<Libro> findByAutoresIdautor(Integer id);

	List<Libro> findByTituloContaining(String cadena);

	
	

}
