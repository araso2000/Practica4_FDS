package practica4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        String imp = "\n";
        
        int opt = 1;
        
        while(opt!=0){
            System.out.println("Introduce una opción (1.BATERIA DE PRUEBAS) (2.MODO MANUAL) (0.SALIR)");        
            opt = scan.nextInt();                                                                               
            
            switch(opt){
                case 1:
                    //Hemos eliminado los casos de prueba donde el camino cerrado se resuelve como NO TERMINA, ya que hace que la ejecución del programa no tenga fin nunca y 
                    //nos imposibilita la creación de un archivo TXT tal y como pide la guía de la práctica
                    int[] arrayAncho = {3,4,5,6,3,3,5,3,3,3,3,4,4,5,6,8,6,6,10,12};//{3,5,6,4,8,14};
                    int[] arrayAlto =  {3,4,5,6,10,12,6,4,5,6,8,6,3,3,3,3,4,5,3,3};//{14,8,8,8,4,3};
                    
                    for(int ii=0;ii<arrayAncho.length;ii++){
                        String temp = modoManual(arrayAncho[ii],arrayAlto[ii]);
                        imp=imp+temp;
                    }
                    
                    guardar(imp);
                    
                    break;
                
                case 2:
                    System.out.print("Modo manual \nIntroduzca ANCHO del tablero: \n");
                    int ancho = scan.nextInt();
                    System.out.println("Introduzca ALTO del tablero:");
                    int alto = scan.nextInt();
                    
                    modoManual(ancho,alto);
                    break;
                    
                default:
                    if(opt!=0){
                        System.out.println("Esa opción no es correcta!");
                    }
                    break;
            }
        }   
    }   
    
    private static String modoManual(int ancho,int alto) throws InterruptedException{
        int[][] tab1 = new int[ancho][alto];
        int[][] tab2 = new int[ancho][alto];
        
        for(int ii=0;ii<ancho;ii++){
            for(int jj=0;jj<alto;jj++){
                tab1[ii][jj] = 0;
                tab2[ii][jj] = 0;
            }
        }
                
        Backtrack bt = new Backtrack(tab1,tab2,ancho,alto);
        return(bt.existeSolucion());
    }
    
    private static void guardar(String imprimir) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Resultado Casos de Prueba Básicos.txt"))) {
                writer.write(imprimir);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
