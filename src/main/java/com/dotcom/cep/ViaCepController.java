package com.dotcom.cep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins="*")
public class ViaCepController {
	
	private static final Logger log = LoggerFactory.getLogger(ViaCepController.class);
	
	@Autowired
	//private ViaCepService vs;
	private ViaCep viacep;

	@GetMapping("/viacep/{cep}")
	public ModelAndView viaCep(@PathVariable("cep") String cep) {
		log.info("GET ViaCep:"+cep);
		return null;
	}
	
}
