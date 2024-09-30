package proyecto.liam.SpringBootBiblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
import proyecto.liam.SpringBootBiblioteca.entidad.Genero;
import proyecto.liam.SpringBootBiblioteca.entidad.Autor;
import proyecto.liam.SpringBootBiblioteca.repository.GeneroRepository;
import proyecto.liam.SpringBootBiblioteca.repository.AutorRepository;
import proyecto.liam.SpringBootBiblioteca.repository.LibroRepository;
 
@Controller

public class MainController {
	
	@Autowired
    GeneroRepository generoRepository;
	
	@Autowired
    AutorRepository autorRepository;
	
	@Autowired
    LibroRepository libroRepository;
     
    @GetMapping("/index")
    public String index(Model model) {
        // Para pasar datos a la vista usamos model
         
        model.addAttribute("nombre", "Pepito pérez");
         
        // Lo que devolvemos aquí es el nombre de la vista
        // Spring boot automáticamente buscará una página index.html
        // En la carpeta resources/templates
        return "index";
    }
    // La primera vez que estamos haciendo un MVC como dios manda
    @GetMapping("/Genero/{id}")
    public String getGenero(Model model, @PathVariable int id) {
        // Voy al modelo para obtener los datos del genero
        Genero genero=generoRepository.findById(id).orElse(null);
        // Una vez tengo esos datos los envío a la vista vía model
        model.addAttribute("genero", genero);
         
        // Lo que devolvemos aquí es el nombre de la vista
        // Spring boot automáticamente buscará una página index.html
        // En la carpeta resources/templates
        return "genero";
    }
    
 // La primera vez que estamos haciendo un MVC como dios manda
    @GetMapping("/generos")
    public String getGeneros(Model model) {
        // Voy al modelo para obtener los datos del genero
        List<Genero> generos=generoRepository.findAll();
        // Una vez tengo esos datos los envío a la vista vía model
        model.addAttribute("generos", generos);
         
        // Lo que devolvemos aquí es el nombre de la vista
        // Spring boot automáticamente buscará una página index.html
        // En la carpeta resources/templates
        return "generos";
    }
    
    @GetMapping("/autores")
    public String getAutores(Model model) {
        // Voy al modelo para obtener los datos del genero
        List<Autor> autores=autorRepository.findAll();
        // Una vez tengo esos datos los envío a la vista vía model
        model.addAttribute("autores", autores);
         
        // Lo que devolvemos aquí es el nombre de la vista
        // Spring boot automáticamente buscará una página index.html
        // En la carpeta resources/templates
        return "autores";
    }
    
    @GetMapping("/Autor/{id}")
    public String getAutor(Model model, @PathVariable int id) {
        // Voy al modelo para obtener los datos del genero
        Autor autor=autorRepository.findById(id).orElse(null);
        // Una vez tengo esos datos los envío a la vista vía model
        model.addAttribute("autor", autor);
         
        // Lo que devolvemos aquí es el nombre de la vista
        // Spring boot automáticamente buscará una página index.html
        // En la carpeta resources/templates
        return "autor";
    }
    
    @GetMapping("/autorlibros")
 // Pongo el parámetro Model que nos permita pasar datos a la vista
    public String getAutores2(Model model) {
        try {
            // Obtengo los datos como en la API rest
            List<Autor> autores = autorRepository.findAll();
            // Paso la información a la vista vía model
            // La vista tendrá una variable 'autores' con la lista de autores
            model.addAttribute("autor", autores);
            // Le digo que me cargue la vista 'autores' la buscará en templates
            return "autor";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }
    
 // Mapeo que me pasen un parámetro id
    @GetMapping("/autorlibros/{id}")
    // Tengo el parámetro id que me pasan y el model para pasar datos a la vista
    public String getAutor(Model model, @PathVariable Integer id) {
        try {
            // Recupero el autor
            Autor autor = autorRepository.findById(id).orElse(null);
            if (autor != null) {
                // Lo paso a la vista
                model.addAttribute("autor", autor);
                // Devuelvo la vista
                return "autor";
            } else {
                // Me he creado una vista para mostrar un error
                return "error";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "error";
        }
    }

}
