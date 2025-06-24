
package edsem12;

import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String oracion;
        System.out.println("Ingrese una oracion: ");
        oracion = sc.nextLine();
        Whatsapp caracteres = new Whatsapp();
        System.out.println("MENU DE OPCIONES");
        System.out.println("1. Mostrar oracion separada por nodos");
        System.out.println("2. Corregir alguna palabra de la oracion");
        System.out.println("3. Eliminar palabra de la oracion");
        System.out.println("-------------------------------------------");
        System.out.print("Ingrese una opcion: ");
        int opcion;
        do{
            opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    caracteres.separarpalabras(oracion);
                    caracteres.print();
                break;
                case 2:
                    
                    caracteres.
                    

            }
        }while(opcion!=4);
    }
}
