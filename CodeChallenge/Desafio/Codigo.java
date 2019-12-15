/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desafio;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Axel Berg
 */
class Codigo {

    private String codigoAlfabetico;
    private String codigoNumerico;
    private int codigoVerificador;
    private static LinkedList<String> simbolosPrioritarios = new                //Los codigo tienen una lista con las letras que son prioridad, la lista es estatica asi  
        LinkedList<String>(Arrays.asList("P", "W"));                            //si se desea añadir o quitar letras este cambio se vea reflejado en todos los codigos.
    
    
    public Codigo(String alfabetico, String numerico, int verificador) {
        this.codigoAlfabetico = alfabetico;                                     //Asumo que llega desde afuera un String que ya verifique que sea solamente de letras.
        this.codigoNumerico = numerico;                                         //Asumo que llega desde afuera un String que ya verifique que sea solamente de numeros.
        this.codigoVerificador = verificador;                                   //Asumo que llega desde afuera un int que ya verifique que sea de un digito.
    }
    
    public String getCodigoAlfabetico() {
        return codigoAlfabetico;
    }

    public void setCodigoAlfabetico(String codigoAlfabetico) {
        this.codigoAlfabetico = codigoAlfabetico;
    }

    public String getCodigoNumerico() {
        return codigoNumerico;
    }

    public void setCodigoNumerico(String codigoNumerico) {
        this.codigoNumerico = codigoNumerico;
    }

    public int getCodigoVerificador() {
        return codigoVerificador;
    }

    public void setCodigoVerificador(int codigoVerificador) {
        this.codigoVerificador = codigoVerificador;
    }
    
    public String getSimbolosPrioridad() {
        int contador = 0;
        String respuesta = "";
        while(contador < this.simbolosPrioritarios.size()) {
            respuesta = respuesta + " / " + this.simbolosPrioritarios.get(contador);
            contador++;
        }
        return respuesta;
    }
    
    public void setSimbolosPrioridad(String letra) {
        this.simbolosPrioritarios.add(letra);
    }
    
    public boolean esPrioridad() {
        /*
        Este metodo mira si la primer letra del codigo alfabetico es 
        prioridad, para eso compara con todas las letras que hay en un 
        lista estatica que tiene cada codigo. Si la comparacion es verdadera
        entonces el codigo es prioridad y devuelve un boolean true.
        */
        boolean respuesta = false;
        int contador = 0; int longitud = this.simbolosPrioritarios.size();
        String codigoPrioridad = new String(new char[]{this.codigoAlfabetico.charAt(0)});
        System.out.print("Primer letra del codigo: " + codigoPrioridad);
        while((!respuesta) && (longitud > contador)) {
            respuesta = (this.simbolosPrioritarios.get(contador).equals(codigoPrioridad));
            contador++;
        }
        return respuesta;
    }
    
    public boolean verificar() {
        /*
        Este metodo verifica si la sumatoria del codigo numerico es igual al 
        numero de la ultima parte del codigo, devuelve un boolean true si 
        cumple con la condicion.
        */
        int sumatoria = Integer.parseInt(this.codigoNumerico);
        while(sumatoria > 9) {
            sumatoria = calculo(sumatoria);
        }
        return sumatoria == this.codigoVerificador;
    }
    
    private int calculo(int valor) {
        /*
        Metodo utilizado en el metodo "verificar".
        */
        int respuesta = 0;
        while(valor > 0) {
            respuesta = respuesta + (valor % 10);
            valor = valor / 10;
        }
        return respuesta;
    }
    
    public boolean esIgual(Codigo unCodigo) {
        /*
        Mi propio metodo para ver si 2 codigos son iguales segun mi criterio.
        */
        return(this.codigoAlfabetico == unCodigo.getCodigoAlfabetico() && 
                this.codigoNumerico == unCodigo.codigoNumerico && 
                this.codigoVerificador == this.codigoVerificador);
    }
    
}
