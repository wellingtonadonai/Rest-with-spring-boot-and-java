package com.example.wellington.udemy.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    //http://localhost:8080/math/sum/
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) throws Exception {
        if (!isNumeric(numberOne)|| !isNumeric(numberTwo))
            throw new UnsupportedOperationException("please set a numeric value");

        return convertToDouble (numberOne) + convertToDouble (numberTwo);
    }

    private Double convertToDouble(String strnumber) {
        if (strnumber == null || strnumber.isEmpty())
            throw new UnsupportedOperationException("please set a numeric value");
        String number = strnumber.replace(",", ".");
        return Double.parseDouble(number);

    }

    private boolean isNumeric(String strnumber) {
        if (strnumber == null || strnumber.isEmpty()) return false;
        String number = strnumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");

    }


    //http://localhost:8080/math/subtraction/
    //http://localhost:8080/math/division/
    //http://localhost:8080/math/sum/
    //http://localhost:8080/math/sum/

}
