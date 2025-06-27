package com.example.wellington.udemy.math;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class SimpleMath {
    //http://localhost:8080/math/sum/
    public Double sum(Double numberOne, Double numberTwo){
        return numberOne + numberTwo;
    }

    //http://localhost:8080/math/subtraction/
    public Double subtraction(Double numberOne, Double numberTwo){
        return numberOne - numberTwo;
    }
    //http://localhost:8080/math/multiplication/
    public Double multiplication (Double numberOne, Double numberTwo){
        return numberOne * numberTwo;
    }

    //http://localhost:8080/math/division/
    public Double division (Double numberOne, Double numberTwo){
        return numberOne / numberTwo;
    }

    //http://localhost:8080/math/mean/
    public Double mean (Double numberOne, Double numberTwo){
        return (numberOne + numberTwo)/ 2;
    }





    //http://localhost:8080/math/squareroot/
    public Double squareRoot (Double number){
        return Math.sqrt(number);

    }
}
