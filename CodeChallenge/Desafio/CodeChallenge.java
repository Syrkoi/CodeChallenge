/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desafio;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Axel Berg
 */
public class CodeChallenge {
    
    public static boolean esPrioritario(Codigo unCodigo) {
        /*
        Realizar la función boolean ​esPrioritario​(codigo), donde devuelve true o
        false si el código de producto comienza con las letras 'P' o 'W.
        */
        boolean respuesta = false;
        if(unCodigo != null)
            respuesta = unCodigo.esPrioridad();
        return respuesta;
    }
    
    public static boolean verificar(Codigo unCodigo) {
        /*
        Realizar la función boolean ​verificar​(codigo), donde chequea si el 
        dígito verificador es correcto. Y devuelve true o false si el dígito 
        verificador es correcto.
        */
        boolean respuesta = false;
        if(unCodigo != null)
            respuesta = unCodigo.verificar();
        return respuesta;
    }
    
    public static void ordenarAlfabeticamente(LinkedList<Codigo> lista) {
        /*
        C)Realizar una función que recibe una lista de productos (lista/array de 
        los códigos), y las ordena alfabéticamente según su código alfabético 
        (XXX), de menor a mayor.
        
        Solo ordena los codigos de la lista por el codigo alfabetico, no importa 
        como queden los numeros.
        */
        if(lista.size() != 0)
            quickSort(0, lista.size()-1, lista);
    }
    
    private static void quickSort(int primero, int ultimo, LinkedList<Codigo> lista) {
        int i = primero;
        int j = ultimo;
        Codigo pivote = lista.get(primero + ((ultimo - primero) / 2));
        while(i <= j) {
            while(lista.get(i).getCodigoAlfabetico().compareTo(pivote.getCodigoAlfabetico()) < 0) {
                i++;
            }
            while(lista.get(j).getCodigoAlfabetico().compareTo(pivote.getCodigoAlfabetico()) > 0) {
                j--;
            }
            if(i <= j) {
                intercambiar(i, j, lista);
                i++;
                j--;
            }
        }
        if(primero < j) {
            quickSort(primero, j, lista);
        }
        if(i < ultimo) {
            quickSort(i, ultimo, lista);
        }
    }
    
    private static void intercambiar(int i, int j, LinkedList<Codigo> lista) {
        Codigo temporal = lista.get(i);
        
        lista.add(i, lista.get(j));
        lista.remove(i+1);
        lista.add(j, temporal);
        lista.remove(j+1);
    }
    
    public static LinkedList union(LinkedList<Codigo> primerLista, LinkedList<Codigo> segundaLista) {
        /*
        Realizar una función que recibe 2 listas de productos 
        (listas/arrays de los códigos) y devuelve una lista/array 
        que representa la Unión de los elementos.
        */
        LinkedList<Codigo> respuesta = new LinkedList<>();
        respuesta.addAll(primerLista);
        respuesta.addAll(segundaLista);
        int i = 0; int j = 1;
        Codigo auxiliar;
        while(i < respuesta.size()) {
            auxiliar = respuesta.get(i);
            while(j < respuesta.size()) {
                if(auxiliar.esIgual(respuesta.get(j))) {
                    respuesta.remove(j);
                }
                else
                    j++;
            }
            i++;
            j = i+1;
        }
        return respuesta;
    }
    
    public static LinkedList interseccion(LinkedList<Codigo> primerLista, LinkedList<Codigo> segundaLista) {
        /*
        Realizar una función que recibe 2 listas de productos 
        (listas/arrays de los códigos) ydevuelve una lista/array 
        que representa la Intersección de los elementos.
        */
        LinkedList<Codigo> respuesta = new LinkedList<>();
        int longitud1 = primerLista.size();
        int longitud2 = segundaLista.size();
        int i = 0; int j = 0;
        Codigo elemento;
        while(i < longitud1) {
            elemento = primerLista.get(i);
            while(j < longitud2) {
                if(elemento.esIgual(segundaLista.get(j))) {
                    if(!respuesta.contains(elemento)) {
                        respuesta.add(elemento);
                    }
                }
                j++;
            }
            j = 0;
            i++;
        }
        return respuesta;
    }
    
    public static void main(String []args) {
        //asegurarse que el String de letras este en MAYUSCULAS antes de crear el objeto.
        //asegurarse que el String de numeros sea entereamente de numeros antes de crear el objeto.
        //asegurarse que el numero final del codigo sea de un solo digito.
        
        Codigo c1 = new Codigo("WAB", "12345", 6);
        Codigo c2 = new Codigo("LAB", "64789", 5);
        System.out.print("Codigo: " + c1.getCodigoAlfabetico() + "-" + c1.getCodigoNumerico() + "-" + c1.getCodigoVerificador() + " / ");
        System.out.print(" / es prioridad? " + esPrioritario(c1));
        System.out.println(" / verifica? " + verificar(c1));
        System.out.print("Codigo: " + c2.getCodigoAlfabetico() + "-" + c2.getCodigoNumerico() + "-" + c2.getCodigoVerificador() + " / ");
        System.out.print(" / es prioridad? " + esPrioritario(c2));
        System.out.println(" / verifica? " + verificar(c2));
        System.out.println("");
        
        
        Codigo[] codigos = new Codigo[12];
        codigos[0] = new Codigo("WAB", "12345", 6);
        codigos[1] = new Codigo("LAB", "64789", 5);
        codigos[2] = new Codigo("VZZ", "00000", 0);
        codigos[3] = new Codigo("XAA", "00020", 2);
        codigos[4] = new Codigo("AAX", "00400", 3);
        codigos[5] = new Codigo("CAA", "50300", 9);
        codigos[6] = new Codigo("BAA", "00110", 6);
        codigos[7] = new Codigo("AAA", "00000", 0);
        codigos[8] = new Codigo("XZZ", "00001", 1);
        codigos[9] = new Codigo("AAD", "20000", 1);
        codigos[10] = new Codigo("AAD", "20001", 1);
        codigos[11] = new Codigo("AAD", "20004", 3);
        LinkedList<Codigo> lista1 = new LinkedList<>();
        LinkedList<Codigo> lista2 = new LinkedList<>();
        Random r = new Random();
        for(int i = 0; i < 5; i++) {
            lista1.add(codigos[r.nextInt(12)]);
            lista2.add(codigos[r.nextInt(10)]);
            }
        
        
        System.out.println("Lista a ordenar");
        for(Codigo elemento: lista1) {
            System.out.println(elemento.getCodigoAlfabetico() + "-" + elemento.getCodigoNumerico() + "-" + elemento.getCodigoVerificador());
            }
        System.out.println("");
        ordenarAlfabeticamente(lista1);
        System.out.println("Ordenado Alfabeticamente: ");
        for(Codigo elemento: lista1) {
    	  System.out.println(elemento.getCodigoAlfabetico() + "-" + elemento.getCodigoNumerico() + "-" + elemento.getCodigoVerificador());
        }
        System.out.println("");
        System.out.println("-------------------------------");
        System.out.println("");
        
        
        System.out.println("UNION");
        System.out.println("Lista 1");
        LinkedList<Codigo> resUnion = new LinkedList<>();
        for(Codigo elemento: lista1) {
    	  System.out.println(elemento.getCodigoAlfabetico() + "-" + elemento.getCodigoNumerico() + "-" + elemento.getCodigoVerificador());
        }
        System.out.println("");
        System.out.println("Lista 2");
        for(Codigo elemento: lista2) {
    	  System.out.println(elemento.getCodigoAlfabetico() + "-" + elemento.getCodigoNumerico() + "-" + elemento.getCodigoVerificador());
        }
        resUnion = union(lista1, lista2);
        System.out.println("");
        System.out.println("Lista Unida");
        for(Codigo elemento: resUnion) {
    	  System.out.println(elemento.getCodigoAlfabetico() + "-" + elemento.getCodigoNumerico() + "-" + elemento.getCodigoVerificador());
        }
        System.out.println("");
        System.out.println("-------------------------------");
        System.out.println("");
        
        
        LinkedList<Codigo> resInterseccion = new LinkedList<>();
        System.out.println("Interseccion");
        System.out.println("Lista 1");
        for(Codigo elemento: lista1) {
    	  System.out.println(elemento.getCodigoAlfabetico() + "-" + elemento.getCodigoNumerico() + "-" + elemento.getCodigoVerificador());
        }
        System.out.println("");
        System.out.println("Lista 2");
        for(Codigo elemento: lista2) {
    	  System.out.println(elemento.getCodigoAlfabetico() + "-" + elemento.getCodigoNumerico() + "-" + elemento.getCodigoVerificador());
        }
        resInterseccion = interseccion(lista1, lista2);
        System.out.println("");
        System.out.println("Lista Intersectada");
        for(Codigo elemento: resInterseccion) {
    	  System.out.println(elemento.getCodigoAlfabetico() + "-" + elemento.getCodigoNumerico() + "-" + elemento.getCodigoVerificador());
        }
        }
        
}
