package proyecto.liam.SpringBootBiblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.liam.SpringBootBiblioteca.entidad.Autor;
import proyecto.liam.SpringBootBiblioteca.repository.AutorRepository;

@RestController
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
    AutorRepository autorRepository;
 
    @GetMapping("")
    public List<Autor> getAll() {
        try {
            return autorRepository.findAll();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
 
    // Poner los valores en la URL, no parámetros nombrados
    @GetMapping("/{id}")
    public ResponseEntity<Autor> getById(@PathVariable int id) {
        System.out.println(id);
 
        try {
            Autor cat= autorRepository.findById(id).orElse(null);
            if (cat!=null) {
                return new ResponseEntity<>(cat, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    @GetMapping("/titulo/{cadena}")
    public List<Autor> getAutoresByTitulo(@PathVariable String cadena) {
        System.out.println(cadena);
 
        try {
            return autorRepository.findByLibrosTituloContaining(cadena);
             
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
 
 
    @PostMapping("")
    public Autor add(@RequestBody Autor cat) {
        System.out.println(cat);
        try {
            if (cat.getIdautor() == 0 && cat.getNombre() != null) {
                return autorRepository.save(cat);
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
 
    @PutMapping("/{id}")
    public Autor put(@RequestBody Autor cat, @PathVariable int id) {
        System.out.println(cat);
        System.out.println(id);
        try {
            if (cat.getIdautor() == id) {
                return autorRepository.save(cat);
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
            autorRepository.deleteById(id);
            return id;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
 // El primer mapeo es con get para simplemente mostrar la vista
    @GetMapping("/add")
    // Pasamos como parámetro el autor para que la vista lo pueda tener disponible
    public String addAutor(Autor autor) {
    	System.out.println("addauotr");
        // Simplemente mostramos la vista
        return "addautor";
    }
    // Cuando desde la vista nos añaden el autor entramos por 'POST'
    @PostMapping("/add")
    // Con @Validated recuperamos los datos y los metemos dentro de una entidad, spring lo hace solo
    // En result se guardan los datos de la validación, es decir ¿Lo que nos mandan
    // son datos válidos? Si es que sí, no dará error, en caso contrario
    // en result tenemos la lista de errores
    public String addAutorDatos(@Validated Autor autor, BindingResult result) {
        System.out.println(autor);
        System.out.println(result);
        // Si hay algún error volvemos a mostrar la vista y además
        // fields.error tendrá la información de los errores
        if (result.hasErrors()) {
            return "addautor";
        }
        // Si no hay ningún error guardamos el autor
        autorRepository.save(autor);
        // Y en vez de devolver una vista, redirigimos al índice
        return "redirect:/autor";
    }

}
