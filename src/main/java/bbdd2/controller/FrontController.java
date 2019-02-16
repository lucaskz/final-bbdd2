package bbdd2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import bbdd2.dto.CartDTO;
import bbdd2.dto.MotorCycleStockDTO;
import bbdd2.dto.StockDTO;
import bbdd2.service.CartService;
import bbdd2.service.ProductService;
import bbdd2.service.StockService;

@Controller
public class FrontController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	StockService stockService;
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/products")
	public String products(Model model) {
		model.addAttribute("stocks", stockService.getAvailableStock());
		model.addAttribute("carts", cartService.getPaginatedCarts());
		return "products";
	}
	
	@PostMapping("/cart/sell")
	@ResponseBody
	public Object sellProduct(@RequestBody CartDTO cartDTO,
							  @RequestParam(name = "concurrent", required = false, defaultValue = "false") boolean concurrent) throws Exception {
		return this.cartService.sellProduct(cartDTO, concurrent);
		
	}
	
	@PostMapping("/product/motorcycle")
	@ResponseBody
	public Object newMotorCycle(@RequestBody MotorCycleStockDTO stockDTO) {
		return this.stockService.addMotorCycleStock(stockDTO);
	}
	
	@PostMapping("/product/car")
	@ResponseBody
	public Object newCar(@RequestBody StockDTO stockDTO) {
		return this.stockService.addCarStock(stockDTO);
	}
}
