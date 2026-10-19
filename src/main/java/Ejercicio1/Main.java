package Ejercicio1;

import java.util.Scanner;

public class  Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe un número entero positivo:");
        String num = sc.nextLine();

        try {
        	//definimos los parametros de ejecucion del proceso
            ProcessBuilder pb = new ProcessBuilder(
            		//java ejecuta la maquina virtual de java
            		//"-cp", System.getProperty("java.class.path") hereda el classpath del proyecto actual
            		//Ejercicio1.Ejercicio1" (Paquete y clase) del programa que actua como proceso hijo
                    //num pasa la cadena coo primer argumento de linea de comando
            		"java", "-cp", System.getProperty("java.class.path"),
                    "Ejercicio1.Ejercicio1", num);
            pb.inheritIO();//redirige las entradas y salidas del hijo para que comparta la misma teerminal del proceso padre
            
            //.start arranca la ejecucion del proceso
            //waitfor bloquea al proceso padre hasta que el hijo termine
            //al terminar devuelve un numero que representa el codigo de salida(exit code)
            int codigo = pb.start().waitFor();

          //en linux 255=-1//254=-2
            if (codigo > 127) codigo -= 256;

            //muestra la respuesta segun el codigo devuelto
            switch (codigo) {
                case -1: System.out.println("No has escrito nada"); break;
                case -2: System.out.println("No has escrito un entero"); break;
                case -3: System.out.println("Has escrito un entero positivo"); break;
                case 0:  System.out.println("El entero debe ser positivo"); break;
                default: System.out.println("Código inesperado: " + codigo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
