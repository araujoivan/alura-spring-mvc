/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.controller;

import br.com.system.model.PaymentData;
import br.com.system.model.ShopCart;
import java.math.BigDecimal;
import java.util.concurrent.Callable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author eder
 */
@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private ShopCart shopCar;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/closePayment", method = RequestMethod.POST)
    public Callable<ModelAndView> closePayment(RedirectAttributes attributes) {

        return () -> {
            
            final String uri = "http://book-payment.herokuapp.com/payment";

            try {

                String response = restTemplate.postForObject(uri, new PaymentData(shopCar.getTotal()), String.class);

                System.out.println(response);

                attributes.addFlashAttribute("message", "Successfull Payment");

            } catch (HttpClientErrorException e) {
                e.printStackTrace();
                attributes.addFlashAttribute("message", "Payment has failed!");
            }

            return new ModelAndView("redirect:/products");

        };
    }
}
