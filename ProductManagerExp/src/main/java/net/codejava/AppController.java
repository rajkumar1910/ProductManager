package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private ProductService service; 
	
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "index";
	}
	
	@RequestMapping("/isbuilk")
	public String showViewProductPart(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "product_part";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("Product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("Product") Product product) {
		service.save(product);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/savePart", method = RequestMethod.POST)
	public String savePartProduct(@ModelAttribute("PartNumber") PartNumber product) {
		service.updatePart(1,product.getPart_number(),product.getPart_classification(),product.getPart_quantity(),product.getNotes());
		
		return "redirect:/viewPart";
	}
	
	@RequestMapping("/viewPart")
	public String viewHomePagePart(Model model) {
		
		List<PartNumber> listProducts = service.listAllParts();
		model.addAttribute("listProducts", listProducts);
		
		return "index2";
	}
	
	@RequestMapping("/product/{id}")
	public ModelAndView showProductPage(@PathVariable(name = "id") int product_item_id) {
		ModelAndView mav = new ModelAndView("Product");
		Product product = service.get(product_item_id);
		mav.addObject("Product", product);
		
		return mav;
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int product_item_id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(product_item_id);
		mav.addObject("Product", product);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int product_item_id) {
		service.delete(product_item_id);
		return "redirect:/";		
	}
	
	
}
