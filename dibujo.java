
package Icono;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class dibujo extends JPanel{
    int movimientoX = 50;
    int movimientoY = 50;
    int colisiones = 0;
    
    private JFrame ventana;
    private Container contenedor;
    //figura representada en hexadecimal
    private final int [] figura =  {
    0x0000000,
    0x0000000,
    0x000000c,
    0x200000a,
    0x2aaaaa9,
    0x5555558,
    0x8000008,
    0xfffffff,
    0x8000008,
    0x5555558,
    0x2aaaaa9,
    0x000000a,
    0x000000c,
    0x0000000,
    0x0000000

        
    };
    
    //Mascara
    private final int Mascara = 0x8000;
    
    //ancho en bits
    private final int Ancho_bits = 32;
    private int coordenada_X;
    private int coordenada_y;
    
    private Thread Hilo;

    public dibujo() {
        //inicializar ventana
        ventana = new JFrame("Dibujando icono");
        //definir tamaño a ventana
        ventana.setSize(500,500);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);//posición al centro
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        
        contenedor= ventana.getContentPane();
        contenedor.setSize(500,500);
        //agregar una ventana en el contenedor
        contenedor.add(this, BorderLayout.CENTER);
        
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
       
        System.out.println("(" + this.coordenada_X + "," + this.coordenada_y + ")");
        
        
        for(int i=0; i< this.figura.length; i++){
            //iterador para el ancho en bits
            for (int j = 0; j < this.Ancho_bits; j++) {
                int temp = this.figura[i]&(this.Mascara >> j);
                
                if(temp != 0){
                    g.drawLine(
                            coordenada_y + j,
                            coordenada_X + i,
                            coordenada_y + j,
                            coordenada_X + i);
                }
                
            }
        }
        
    }
    public void dibujar(){
         
        this.coordenada_X = (int)(Math.random()*500);
        this.coordenada_y = (int)(Math.random()*500);
        
        while(colisiones < 10){
            try {
                this.coordenada_X = this.coordenada_X - movimientoX;
                this.coordenada_y = this.coordenada_y - movimientoY;
                
                this.Hilo.sleep(500);//medio segundo
                
                if(this.coordenada_X > 0 && this.coordenada_X < 500){
                    if(this.coordenada_y > 0 && this.coordenada_y < 500){
                        paint(getGraphics());
                    }else{
                        System.err.println(colisiones);
                        colisiones ++;
                        dibujar();
                        
                    }
                }else{
                    System.err.println(colisiones);
                    colisiones ++;
                    dibujar();
                    
                }
                
                
                 //paint(getGraphics());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        
           }

    
}
