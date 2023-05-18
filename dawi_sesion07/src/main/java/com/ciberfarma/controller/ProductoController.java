package com.ciberfarma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ciberfarma.model.Producto;
import com.ciberfarma.repository.ICategoriaRepository;
import com.ciberfarma.repository.IProductoRepository;

@Controller
public class ProductoController {

	// crear obj de repository
	@Autowired
	private ICategoriaRepository repoCat;
	
	// crea los controladores
	// controlador para abrir la pág de prod
	@GetMapping("/cargar")
	public String abrirPagProd(Model model) {
		// enviar un "listado" para el combo
		// model.addAttribute("mensaje", "Éxito");
		model.addAttribute("lstCategorias", repoCat.findAll());
		model.addAttribute("producto", new Producto());
		
		return "crudproductos";
	}
	
	@Autowired
	private IProductoRepository repoProd;
	
	@GetMapping("/listado")
	public String muestraListado(Model model) {
		model.addAttribute("lstProductos", repoProd.findAll());
		model.addAttribute("lstCategorias", repoCat.findAll());
		model.addAttribute("producto", new Producto());
		
		
		return "crudproductos";
	}
	
	// controlador para abrir principal
	@GetMapping("/")
	public String abrirPagPricipal() {
		return "principal";
	}
	
	
	
	@PostMapping("/producto/grabar")
	public String grabarCrudProducto(@ModelAttribute Producto producto, Model model) {
		//model.addAttribute("lstProductos", repoProd.findAll());
		//model.addAttribute("lstCategorias", repoCat.findAll());
		//model.addAttribute("producto", new Producto());
		
		try {
			model.addAttribute("producto", new Producto());
			repoProd.save(producto);
			System.out.println(producto);
			model.addAttribute("Registro OK");
			model.addAttribute("clase", "alert alert-success");
			
		} catch (Exception e) {
			model.addAttribute("mensaje", "Error al registrar");
			model.addAttribute("clase", "alert alert-danger");
		}
		
		return "crudproductos";		
	}
	
	
}
