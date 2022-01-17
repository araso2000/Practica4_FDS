package practica4;

public class Backtrack {
    int[][] tableroAbierto;
    int[][] tableroCerrado;
    int anchura;
    int altura;
    int contador=2;
    long tiempoAbierto=0;
    long tiempoCerrado=0;
    
    public Backtrack(int[][] tab1,int[][] tab2,int x,int y){
        this.tableroAbierto=tab1;
        this.tableroCerrado=tab2;
        this.anchura=x;
        this.altura=y;
    }
    
    public String existeSolucion(){
        int[] saltosX = new int[] {-1,-2,-2,-1,1,2,2,1};
        int[] saltosY = new int[] {2,1,-1,-2,2,1,-1,-2};
        int inix=0;
        int iniy=0;
        
        for(int ii=0;ii<anchura;ii++){
            for(int jj=0;jj<altura;jj++){
                this.tableroAbierto[ii][jj] = 0;
                this.tableroCerrado[ii][jj] = 0;
            }
        }
        
        boolean aciertoAbierto = buscarCaminoAbierto(0,this.anchura,this.altura,inix,iniy,saltosX,saltosY);
        boolean aciertoCerrado = buscarCaminoCerrado(0,this.anchura,this.altura,inix,iniy,saltosX,saltosY);
               
        return(imprimir(aciertoAbierto,aciertoCerrado));
    }
    
    public boolean buscarCaminoAbierto(int i,int ancho,int alto,int xInicio,int yInicio,int[] masX,int[] masY){
        long temp=System.nanoTime();
        boolean exito = false;
        for(int k=0;k<8 && !exito; k++){
            int coordX = xInicio + masX[k];
            int coordY = yInicio + masY[k];
            if (coordX>=0 && coordX<ancho && coordY>=0 && coordY<alto){
                if(this.tableroAbierto[coordX][coordY] == 0){
                    this.tableroAbierto[coordX][coordY] = i;
                    
                    if(i==ancho*alto){
                        exito=true;
                    }else{
                        exito = buscarCaminoAbierto(i+1,ancho,alto,coordX,coordY,masX,masY);
                        if(!exito){
                            this.tableroAbierto[coordX][coordY] = 0;
                        }
                    }
                }
            }
        }
        tiempoAbierto = System.nanoTime() - temp;
        return exito;
    }
    
    public boolean buscarCaminoCerrado(int i,int ancho,int alto,int xInicio,int yInicio,int[] masX,int[] masY){
        long temp=System.nanoTime();
        boolean exito = false;
        for(int k=0;k<8 && !exito; k++){
            int coordX = xInicio + masX[k];
            int coordY = yInicio + masY[k];
            if (coordX>=0 && coordX<ancho && coordY>=0 && coordY<alto){
                if(i==(ancho*alto)+1 && tableroCerrado[coordX][coordY] == 1){
                    exito=true;
                }
                
                if(this.tableroCerrado[coordX][coordY] == 0){
                    this.tableroCerrado[coordX][coordY] = i;
                    if(i==(ancho*alto)+1){
                        exito=true;
                    }else{
                        exito = buscarCaminoCerrado(i+1,ancho,alto,coordX,coordY,masX,masY);
                        if(!exito){
                            this.tableroCerrado[coordX][coordY] = 0;
                        }
                    }
                }
            }
        }
        tiempoCerrado = System.nanoTime() - temp;
        return exito;
    }
        
    public String imprimir(boolean aciertoAbierto,boolean aciertoCerrado){
        String imp="";
        imp = imp + "DIMENSIONES DEL TABLERO - Ancho:" + this.anchura + " - Alto:" + this.altura + "\n";
        imp = imp + "Hay solucion ABIERTA? " + aciertoAbierto + "\n";
        imp = imp + "\t";
        for(int ii=0;ii<this.altura;ii++){
            imp = imp + ii + "\t";
        }
        imp = imp + "\n\n";
        
        for(int ii=0;ii<this.anchura;ii++){
            for(int jj=0;jj<(this.altura+1);jj++){
                if(jj==0){
                    imp = imp + ii + "\t";
                }else{
                    imp = imp + this.tableroAbierto[ii][(jj-1)] + "\t";
                }
            }
            imp = imp + "\n";
        }
        imp = imp + "Tiempo de ejecución ABIERTO: " + this.tiempoAbierto + " nanosegundos \n\n";
        //-----------------------------------------------------------------------------------------------------------------------
        imp = imp + "Hay solucion CERRADA? " + aciertoCerrado + "\n";
        imp = imp + "\t";
        for(int ii=0;ii<this.altura;ii++){
            imp = imp + ii + "\t";
        }
        imp = imp + "\n\n";
        
        for(int ii=0;ii<this.anchura;ii++){
            for(int jj=0;jj<(this.altura+1);jj++){
                if(jj==0){
                    imp = imp + ii + "\t";
                }else{
                    imp = imp + this.tableroCerrado[ii][(jj-1)] + "\t";
                }
            }
            imp = imp + "\n";
        }
        imp = imp + "Tiempo de ejecución CERRADO: " + this.tiempoCerrado + " nanosegundos \n\n------------------------------------------------------------------------------------------------------------------------------------------\n\n";
        System.out.print(imp);
        return(imp);
    }
}