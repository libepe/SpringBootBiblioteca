package proyecto.liam.SpringBootBiblioteca.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import proyecto.liam.SpringBootBiblioteca.entidad.Autor;
import proyecto.liam.SpringBootBiblioteca.entidad.Genero;
import proyecto.liam.SpringBootBiblioteca.entidad.Libro;
import proyecto.liam.SpringBootBiblioteca.repository.AutorRepository;
import proyecto.liam.SpringBootBiblioteca.repository.GeneroRepository;
import proyecto.liam.SpringBootBiblioteca.repository.LibroRepository;

@RestController
@RequestMapping("/libro")
public class LibroController {
	
	@Autowired
    LibroRepository libroRepository;
	
	@Autowired
	GeneroRepository generoRepository;
	
	@Autowired
	AutorRepository autorRepository;
    
    @GetMapping("")
    public List<Libro> getAll(@RequestParam(required=false) Integer min,@RequestParam(required=false) Integer max) {
        try {
            if (min==null || max==null) {
                return libroRepository.findAll();
            }else {
                return libroRepository.findByPaginasBetweenOrderByPaginasAsc(min, max);
            }
             
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
 
    // Poner los valores en la URL, no parámetros nombrados
    @GetMapping("/autor/{id}")
    public List<Libro> getByIdAutor(@PathVariable int id) {
        System.out.println(id);
 
        try {
            return libroRepository.findByAutoresIdautor(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    
    
    @GetMapping("/{id}/libros")
    public Set<Libro> getLibrosByIdGenero(@PathVariable int id) {
        System.out.println(id);
 
        try {
            Genero cat = generoRepository.findById(id).orElse(null);
            if (cat != null) {
                return cat.getLibros();
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    @GetMapping("/titulo/{cadena}")
    public List<Libro> getByTitle(@PathVariable String cadena) {
        System.out.println(cadena);
 
        try {
            return libroRepository.findByTituloContaining(cadena);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
 // Gestión de libros y autores
    @PostMapping("{idlibro}/autor/{idautor}")
    public Libro addLibroAutor(@PathVariable int idlibro, @PathVariable int idautor) {
        System.out.println(idlibro);
        System.out.println(idautor);
        try {
            Libro libro = libroRepository.findById(idlibro).orElse(null);
            Autor autor = autorRepository.findById(idautor).orElse(null);
            if (libro != null && autor != null) {
                libro.getAutores().add(autor);
                return libroRepository.save(libro);
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    
    @PostMapping("")
    public Libro add(@RequestBody Libro cat) {
        System.out.println(cat);
        try {
            if (cat.getIdlibro() == 0 && cat.getGenero() != null && cat.getTitulo() != null && cat.getPaginas() == 0) {
                return libroRepository.save(cat);
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
 
    @PutMapping("/{id}")
    public Libro put(@RequestBody Libro cat, @PathVariable int id) {
        System.out.println(cat);
        System.out.println(id);
        try {
            if (cat.getIdlibro() == id) {
                return libroRepository.save(cat);
            } else {
                return null;
            }
 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
 
    @DeleteMapping("/{id}")
    public int delete(@PathVariable int id) {
        try {
            System.out.println(id);
            libroRepository.deleteById(id);
            return id;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

}
