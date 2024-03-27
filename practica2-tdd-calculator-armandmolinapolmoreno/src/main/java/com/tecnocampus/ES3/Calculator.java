package com.tecnocampus.ES3;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private String token="";
    private List<Integer> numbers=new ArrayList<>();
    private List<Character> operators=new ArrayList<>();
    private boolean lastFactorial=false;
    public void processToken(char token) {
        if (Character.isDigit(token)) {//Miramos si el caracter es un numero
            this.token += token;
            lastFactorial=false;
        } else {
            // El token es una operación, la aplicamos al número actual y al resultado acumulado
            switch (token) {
                case '!':

                    this.token = factorial(this.token);
                    numbers.add(Integer.parseInt(this.token));
                    this.token = "";
                    lastFactorial=true;
                    break;

                default:
                    operators.add(token);
                    try {
                        numbers.add(Integer.parseInt(this.token));
                    }
                    catch(Exception e){

                    }
                    this.token = "";
                    lastFactorial=false;
            }
        }
    }

    public String getDisplay( ) {
        if(lastFactorial)return String.valueOf(numbers.get(numbers.size()-1));
        if(this.token!="")return this.token;
        if(operators.isEmpty()) {
            if (numbers.isEmpty()) return this.token;
            else return String.valueOf(numbers.get(0));
        }
        else{
            List<Integer> resultadosParciales = new ArrayList<>();
            resultadosParciales.add(numbers.get(0));
            int resultado =0;
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i).equals('*')) {
                        int ultimoResultado = resultadosParciales.remove(resultadosParciales.size() - 1);
                        try {
                            resultadosParciales.add(ultimoResultado * numbers.get(i + 1));
                        } catch (Exception e) {
                            return String.valueOf(numbers.get(i));
                        }


                } else if (operators.get(i).equals('+')) {
                    try{
                       resultadosParciales.add(numbers.get(i + 1));
                    }catch(Exception e){
                        for (int num : resultadosParciales) {
                            resultado += num;
                        }
                        return String.valueOf(resultado);
                    }

                }else if(operators.get(i).equals('=')){
                    if(numbers.size()==1)
                    return String.valueOf(numbers.get(0));
                    else{
                        for (int num : resultadosParciales) {
                            resultado += num;
                        }
                    }
                }
            }
            return String.valueOf(resultado);
        }

    }
    private String factorial(String n) {
        int number= Integer.parseInt(n);
        if (number == 0) {
            return "1";
        } else {
            int result = 1;
            for (int i = 1; i <= number; i++) {
                result *= i;
            }
            return String.valueOf(result);
        }
    }

}
