package proyecto.liam.SpringBootBiblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import proyecto.liam.SpringBootBiblioteca.entidad.Genero;
import proyecto.liam.SpringBootBiblioteca.repository.GeneroRepository;
 
@RestController
@RequestMapping("/genero")

public class GeneroController {
	
	@Autowired
    GeneroRepository generoRepository;
    
    @GetMapping("")
    public List<Genero> getAll(@RequestParam(required=false) String nombre) {
        try {
            if (nombre==null) {
                return generoRepository.findAll();
            }else {
                return generoRepository.findByNombreContaining(nombre);
            }
             
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
 
    // Poner los valores en la URL, no parámetros nombrados
    @GetMapping("/{id}")
    public ResponseEntity<Genero> getById(@PathVariable int id) {
        System.out.println(id);
 
        try {
            Genero cat= generoRepository.findById(id).orElse(null);
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
 
    @PostMapping("")
    public Genero add(@RequestBody Genero cat) {
        System.out.println(cat);
        try {
            if (cat.getIdgenero() == 0 && cat.getNombre() != null) {
                return generoRepository.save(cat);
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    @PostMapping("/nuevo/{nombre_del_genero}")
    public Genero addNombre(@PathVariable String nombre_del_genero) {
        System.out.println(nombre_del_genero);
        try {
        	Genero n=new Genero();
            n.setNombre(nombre_del_genero);
            return generoRepository.save(n);
           
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
 
    @PutMapping("/{id}")
    public Genero put(@RequestBody Genero cat, @PathVariable int id) {
        System.out.println(cat);
        System.out.println(id);
        try {
            if (cat.getIdgenero() == id) {
                return generoRepository.save(cat);
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
            generoRepository.deleteById(id);
            return id;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

}
