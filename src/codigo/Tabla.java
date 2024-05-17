/*
 * Programaci�n interactiva
 * Autor: Ingrid Echeverry Montoya - 1943542
 * Autor: Jhan Alejandro Perez Umbarila - 1941003
 * Juego de escaleras y serpientes 
 */
package codigo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import complementos.ImageResize;

// TODO: Auto-generated Javadoc
/**
 * The Class Tabla. Contiene el escenario con el que el jugador va a interactuar (fondo, escaleras, serpientes, dado, etc).
 */
public class Tabla extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static JFrame ventana;
	private static ImageIcon imgFondo = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/fondo.jpg")), 630, 700).resize());
	private static ImageIcon imgReglas = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/reglas.jpg")), 630, 700).resize());
	private static ImageIcon imgBotones = new ImageIcon(Tabla.class.getResource("/img/botones.png"));	
	private static ImageIcon imgTabla = new ImageIcon(Tabla.class.getResource("/img/tabla.png"));
	private static ImageIcon imgJugador1 = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/jugador1.png")), 70, 70).resize());
	private static ImageIcon imgJugador2 = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/jugador2.png")), 70, 70).resize());
	private static ImageIcon imgJugador3 = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/jugador3.png")), 70, 70).resize());
        private static ImageIcon imgJugador4 = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/jugador4.png")), 70, 70).resize());
	private static ImageIcon turnoActual = new ImageIcon(Tabla.class.getResource("/img/selectionCircle.png"));
	private static ImageIcon imgSnake = new ImageIcon(Tabla.class.getResource("/img/snake.gif"));
	private static ImageIcon imgEscalera = new ImageIcon(Tabla.class.getResource("/img/escalera.gif"));
	private static JLabel iconoJugador1,iconoJugador2, iconoJugador3, iconoJugador4;
	protected static JLabel jugadorActual,serpiente,escalera;
	public static Font fuente = Tabla.loadFont();
	private static Color amarillo = new Color(246,184,81);
	private static Color azul = new Color(37,61,95);
        private static Color azul2 = new Color(51,0,153);
	private static JLabel fondo = new JLabel();
	private static JLabel fondoReglas = new JLabel();
	private static JPanel jugar = boton("Jugar");
	private static JPanel reglas = boton("Reglas");
	private static JLabel salir = new JLabel("x");
	private static JLabel volver = new JLabel("<");
	protected static JPanel lanzar = boton("Lanzar", 93,30,24);
	private Escucha escucha = new Escucha();
	protected static Dado dado = new Dado();
	private static Ficha ficha1 = new Ficha(Color.RED);
	private static Ficha ficha2 = new Ficha(Color.GREEN);
	private static Ficha ficha3 = new Ficha(Color.BLUE);
        private static Ficha ficha4 = new Ficha(Color.YELLOW);
	private static Jugador jugador1,jugador2, jugador3, jugador4;
	private static Controlador controlador;
	private static String ventanaActual;
	protected static Boolean reiniciar = false;
        
        
        public static int numeroJugadores = 2;
        public static int tablero = 1;
	
	
	/**
	 * Instantiates a new tabla. Constructor donde se crean y asignan los valores por defecto (iniciales) de cada uno de los atributos.
	 */
	public Tabla(int num, int tabler) 
        {
		ventana = this;
		
                numeroJugadores=num;
                tablero=tabler;
                
		fondo.setIcon(imgFondo);
		fondo.setBounds(0,0,630,700);
		
		jugar.setBounds(182, 254, 260, 80);
		reglas.setBounds(182, 399, 260, 80);
		lanzar.setBounds(142, 620, 93, 30);
	
		
		jugadorActual = new JLabel();
		jugadorActual.setIcon(turnoActual);
		
		salir.setForeground(Color.WHITE);
		salir.setFont(fuente.deriveFont(36f));
		salir.setBounds(602, 0, 21, 44);
		salir.addMouseListener(escucha);
		
		volver.setForeground(Color.WHITE);
		volver.setFont(fuente.deriveFont(30f));
		volver.setBounds(10, 0, 20, 44);
		volver.addMouseListener(escucha);
		
		serpiente = new JLabel();
		serpiente.setIcon(imgSnake);
		serpiente.setBounds(-25, -25, 600, 600);
		serpiente.setVisible(false);
		
		escalera = new JLabel();
		escalera.setIcon(imgEscalera);
		escalera.setBounds(100, 25, 350, 500);
		escalera.setVisible(false);

                switch (numeroJugadores) 
                {
                    case 2:
                        iconoJugador1 = new JLabel(); 
                        iconoJugador2 = new JLabel(); 
                        iconoJugador1.setIcon(imgJugador1);
                        iconoJugador2.setIcon(imgJugador2);
                        iconoJugador1.setBounds(280, 600, 70, 70);
                        iconoJugador2.setBounds(372, 600, 70, 70);
                        break;
                    case 3:
                        iconoJugador1 = new JLabel(); 
                        iconoJugador2 = new JLabel(); 
                        iconoJugador3 = new JLabel(); 
                        iconoJugador1.setIcon(imgJugador1);
                        iconoJugador2.setIcon(imgJugador2);
                        iconoJugador3.setIcon(imgJugador3);
                        iconoJugador1.setBounds(280, 600, 70, 70);
                        iconoJugador2.setBounds(372, 600, 70, 70);
                        iconoJugador3.setBounds(464, 600, 70, 70);
                        break;
                    case 4:
                        iconoJugador1 = new JLabel(); 
                        iconoJugador2 = new JLabel(); 
                        iconoJugador3 = new JLabel();
                        iconoJugador4 = new JLabel(); 
                        iconoJugador1.setIcon(imgJugador1);
                        iconoJugador2.setIcon(imgJugador2);
                        iconoJugador3.setIcon(imgJugador3);
                        iconoJugador4.setIcon(imgJugador4);
                        iconoJugador1.setBounds(280, 600, 70, 70);
                        iconoJugador2.setBounds(372, 600, 70, 70);
                        iconoJugador3.setBounds(464, 600, 70, 70);
                        iconoJugador4.setBounds(556, 600, 70, 70);
                        break;
                    default:
                        throw new AssertionError();
                }
		dado.setBounds(47, 605, Dado.tama, Dado.tama);
		ventana.setUndecorated(true);
		ventana.setLayout(null);
		ventana.setResizable(false);
		ventana.setVisible(true);
		ventana.setSize(630, 700);
		ventana.getContentPane().setBackground(azul2);
		ventana.setLocationRelativeTo(null);
		ventana.addMouseListener(escucha);
		ventana.addMouseMotionListener(escucha);

		cambiarVentana("Juego");
	}
	
	/**
	 * Cambiar ventana. Permite al usuario acceder a las diferentes ventanas en la interfaz del menu principal (jugar, reglas)
	 * @param nombre the nombre
	 */
	protected static void cambiarVentana(String nombre) {
		ventana.getContentPane().removeAll();
		ventana.add(salir);
		switch(nombre) {
		
		case "Juego":
			juego();
			ventanaActual = "Juego";
			break;
		
		}
		ventana.revalidate();
		ventana.repaint();
	}
	
	/**
	 * Menu. Contiene los componentes graficos de la ventana del menu principal
	 */
	private static void menu() {
		JLabel titulo = new JLabel("Serpientes y Escaleras");
		titulo.setFont(fuente.deriveFont(52f));
		titulo.setForeground(amarillo);
		titulo.setBounds(0,90,630,58);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		ventana.add(titulo);
		ventana.add(jugar);
		ventana.add(reglas);
		ventana.add(fondo);
	}
	
	/**
	 * Reglas. Contiene los componentes graficos de la ventana de reglas
	 */
	private static void reglas() {
		fondoReglas.setIcon(imgReglas);
		fondoReglas.setBounds(0,0,630,700);
		ventana.add(volver);
		ventana.add(fondoReglas);
	}
	

	
	/**
	 * Juego. Contiene los componentes graficos de la ventana juego (donde el usuario ya comienza a jugar)
	 */
	private static void juego() {
		JPanel juegoDeMesa = new JPanel();
		juegoDeMesa.setLayout(null);
		juegoDeMesa.setBounds(40, 35, 550, 550);
		
		JLabel tabla = new JLabel();
		tabla.setIcon((new ImageResize(imgTabla, 550,550)).resize());
		tabla.setBounds(0, 0, 550, 550);
                
               
                switch (numeroJugadores) 
                {
                    case 2:
                        ficha1.setBounds(7, 500, Ficha.tama, Ficha.tama);
                        ficha2.setBounds(30, 500, Ficha.tama, Ficha.tama);
                        jugador1 = new Jugador(1, ficha1);
                        jugador2 = new Jugador(2, ficha2);
                        juegoDeMesa.add(ficha1);
                        juegoDeMesa.add(ficha2);
                        ventana.add(iconoJugador1);
                        ventana.add(iconoJugador2);
		
                        break;
                    case 3:
                        ficha1.setBounds(7, 500, Ficha.tama, Ficha.tama);
                        ficha2.setBounds(30, 500, Ficha.tama, Ficha.tama);
                        ficha3.setBounds(7, 525, Ficha.tama, Ficha.tama);
                        jugador1 = new Jugador(1, ficha1);
                        jugador2 = new Jugador(2, ficha2);
                        jugador3 = new Jugador(3, ficha3);
                        juegoDeMesa.add(ficha1);
                        juegoDeMesa.add(ficha2);
                        juegoDeMesa.add(ficha3);
                        
                        ventana.add(iconoJugador1);
                        ventana.add(iconoJugador2);
                        ventana.add(iconoJugador3);
                
                        break;
                    case 4:
                        ficha1.setBounds(7, 500, Ficha.tama, Ficha.tama);
                        ficha2.setBounds(30, 500, Ficha.tama, Ficha.tama);
                        ficha3.setBounds(7, 525, Ficha.tama, Ficha.tama);
                        ficha4.setBounds(30, 525, Ficha.tama, Ficha.tama);
                        jugador1 = new Jugador(1, ficha1);
                        jugador2 = new Jugador(2, ficha2);
                        jugador3 = new Jugador(3, ficha3);
                        jugador4 = new Jugador(4, ficha4);
                        juegoDeMesa.add(ficha1);
                        juegoDeMesa.add(ficha2);
                        juegoDeMesa.add(ficha3);
                        juegoDeMesa.add(ficha4);
                        
                        ventana.add(iconoJugador1);
                        ventana.add(iconoJugador2);
                        ventana.add(iconoJugador3);
                        ventana.add(iconoJugador4);
                        break;
                    default:
                        throw new AssertionError();
                }
                Jugador[] jugadores = {jugador1, jugador2,jugador3,jugador4};
                
              
		
		jugadorActual.setBounds(278, 598, 74, 74);
		
		juegoDeMesa.add(escalera);
		juegoDeMesa.add(serpiente);
		
		juegoDeMesa.add(tabla);
		
		controlador = new Controlador(jugadores);
		
		Tabla.lanzar.setVisible(true);
		
		ventana.add(dado);
//		ventana.add(volver);
		
		ventana.add(jugadorActual);
		ventana.add(lanzar);
		ventana.add(juegoDeMesa);
	}
	
	/**
	 * Final juego. En caso de que n sea 0 se mostrara la ventana juego, de lo contrario la interfaz se cerrara
	 * @param n the n
	 */
	public static void finalJuego(int n) {
		if(n == 0) {
			cambiarVentana("Juego");
		}else {
			System.exit(0);
		}
	}
	
	
	/**
	 * The Class Escucha. Contiene metodos que permiten la interacci�n con la ventana a partir de eventos del mouse
	 */
	public class Escucha extends MouseAdapter {
		
		/** The y. */
		private int x,y;
		
		/**
		 * Mouse clicked. Sirve para desplazarse en las diferentes ventana de la interfaz (salir: salirse de la interfaz, 
		 jugar: para ir a la ventana donde el usuario comienza a jugar)
		 * @param e the e
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(salir)) {
				System.exit(0);
			}else if(e.getSource().equals(jugar)) {
				cambiarVentana("Juego");
			}else if(e.getSource().equals(volver) && ventanaActual == "Juego") {
				Object opciones[] = {"Menu", "Reiniciar"};
				int opcionVolver = JOptionPane.showOptionDialog(ventana, "Volver a menu principal?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[1]);
				if(opcionVolver == 0) {
					controlador.hilo.interrupt();
					cambiarVentana("Menu");
				}else if(opcionVolver == 1) {
					reiniciar = true;
					cambiarVentana("Juego");
				}
			}else if(e.getSource().equals(volver) && ventanaActual == "Reglas") {
				cambiarVentana("Menu");
			}
		}
		
		/**
		 * Mouse pressed. Cada que se presione en alg�n lugar de la ventana, las coordenadas x,y van a actualizarse
		 * @param e the e
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			x = e.getX();
			y = e.getY();
		}
		
		/**
		 * Mouse dragged. Permite desplazar nuestra ventana cuando arrastramos el mouse
		 * @param e the e
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			ventana.setLocation(ventana.getLocation().x+e.getX()-x, ventana.getLocation().y +e.getY()-y);
		}
	}
	
	
	/**
	 * Load font. Carga una fuente de letra
	 * @return the font
	 */
	private static Font loadFont() {
		try {
			fuente = Font.createFont(Font.TRUETYPE_FONT, new File(Tabla.class.getResource("/font/FredokaOne-Regular.ttf").getFile()));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(fuente);
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fuente;
	}
	
	/**
	 * Boton.
	 *
	 * @param texto the texto
	 * @return the j panel
	 */
	//Jpanel
	private static JPanel boton(String texto) 
        {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(345,106);
		panel.setOpaque(false);
		
		JLabel text = new JLabel(texto);
		text.setFont(fuente.deriveFont(45f));
		text.setForeground(azul);
		text.setBounds(0, 14, 260, 53);
		text.setHorizontalAlignment(JLabel.CENTER);
		
		JButton button = new JButton();
		ImageIcon imagen = (new ImageResize(imgBotones, 260,80)).resize();
		ImageIcon imagenMouse = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/botones_hover.png")), 260,80)).resize();
		button.setIcon(imagen);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setSize(260,80);
		
		button.addMouseListener(new MouseAdapter() 
                {
			public void mouseClicked(MouseEvent e) 
                        {
				if(text.getText() == "Jugar") 
                                {
					cambiarVentana("Juego");
				}
                                else 
                                    if(text.getText() == "Reglas") 
                                    {
					cambiarVentana("Reglas");
                                    }
			}
		    public void mouseEntered(MouseEvent e) 
                    {
		        button.setIcon(imagenMouse);
		    }

		    public void mouseExited(MouseEvent e) 
                    {
		        button.setIcon(imagen);

		    }
		});
		panel.add(text);
		panel.add(button);
		return panel;
	}
	
	/**
	 * Boton.
	 *
	 * @param texto the texto
	 * @param ancho the ancho
	 * @param altura the altura
	 * @param letra the letra
	 * @return the j panel
	 */
	//Jpanel
	private static JPanel boton(String texto, int ancho, int altura, float letra) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		
		JLabel text = new JLabel(texto);
		text.setFont(fuente.deriveFont(letra));
		text.setForeground(azul);
		int posicion = (int)(altura-(altura*1.8)/2);
		text.setBounds(0, posicion, ancho, (int)letra);
		text.setHorizontalAlignment(JLabel.CENTER);
		
		JButton button = new JButton();
		ImageIcon imagen = (new ImageResize(imgBotones, ancho, altura)).resize();
		ImageIcon imagenMouse = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/botones_hover.png")), ancho, altura)).resize();
		button.setIcon(imagen);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setSize(ancho,altura);
		
		button.addMouseListener(new MouseAdapter() 
                {
			
			/**
			 * Mouse clicked. Contiene los eventos que ocurriran cuando se presione el boton lanzar
			 * @param e the e
			 */
			public void mouseClicked(MouseEvent e) 
                        {
				if(text.getText() == "Lanzar") 
                                {
                                    reiniciar = false;
                                    dado.lanzar();
                                    //lanzar.setVisible(false);
                                    Timer timer = new Timer();
                                    timer.schedule(new TimerTask() 
                                    {
                                            int counter = 0;
                                            int movimientos = 0;
                                            int terminar = movimientos;
                                            @Override
                                            public void run() 
                                            {
                                                switch (controlador.turno) 
                                                {
                                                    case 1:
//                                                        Tabla.jugadorActual.setLocation(278, 598);
                                                        if(counter == 1) 
                                                        {
                                                                movimientos = dado.lado();
                                                                terminar = movimientos+1;
                                                                jugador1.moverFicha(movimientos);
                                                        }
                                                        if(terminar != 0) 
                                                        {
                                                            if(jugador1.serpiente || jugador1.escalera) 
                                                            {
                                                                    terminar = counter + 2;
                                                            }
                                                            else 
                                                            {
                                                                if(counter == terminar)
                                                                {
                                                                        if(!jugador1.gano && !reiniciar) 
                                                                        {
                                                                                controlador.turno = 2;
                                                                                Tabla.jugadorActual.setLocation(370, 598);
                                                                                //controlador.run();
                                                                        }
                                                                        timer.cancel();
                                                                }
                                                            }
                                                        }
                                                        counter++;
                                                        break;
                                                    case 2:
                                                        //Tabla.jugadorActual.setLocation(370, 598);
                                                        // TODO Auto-generated method stub
                                                        if(counter == 1) 
                                                        {
                                                                movimientos = dado.lado();
                                                                terminar = movimientos+1;
                                                                jugador2.moverFicha(movimientos);
                                                        }
                                                        if(terminar != 0) 
                                                        {
                                                            if(jugador2.serpiente || jugador2.escalera) 
                                                            {
                                                                    terminar = counter + 2;
                                                            }
                                                            else 
                                                            {
                                                                if(counter == terminar)
                                                                {
                                                                    if(!jugador2.gano && !reiniciar) 
                                                                    {
                                                                            
                                                                            switch (numeroJugadores) {
                                                                            case 2:
                                                                                Tabla.jugadorActual.setLocation(278, 598);
                                                                                controlador.turno = 1;
                                                                                break;
                                                                            case 3:
                                                                                Tabla.jugadorActual.setLocation(462, 598);
                                                                                controlador.turno = 3;
                                                                                break;
                                                                            case 4:
                                                                                Tabla.jugadorActual.setLocation(462, 598);
                                                                                controlador.turno = 3;
                                                                                break;
                                                                            default:
                                                                                throw new AssertionError();
                                                                        }
                                                                            
                                                                            //controlador.run();
                                                                    }
                                                                    timer.cancel();
                                                                }
                                                            }
                                                        }
                                                        counter++;
                                                        break;
                                                    case 3:
                                                        //Tabla.jugadorActual.setLocation(462, 598);
                                                        // TODO Auto-generated method stub
                                                        if(counter == 1) 
                                                        {
                                                                movimientos = dado.lado();
                                                                terminar = movimientos+1;
                                                                jugador3.moverFicha(movimientos);
                                                        }
                                                        if(terminar != 0) 
                                                        {
                                                            if(jugador3.serpiente || jugador3.escalera) 
                                                            {
                                                                    terminar = counter + 2;
                                                            }
                                                            else 
                                                            {
                                                                if(counter == terminar)
                                                                {
                                                                    if(!jugador3.gano && !reiniciar) 
                                                                    {
                                                                            
                                                                            switch (numeroJugadores) {
                                                                            case 3:
                                                                                Tabla.jugadorActual.setLocation(278, 598);
                                                                                controlador.turno = 1;
                                                                                break;
                                                                            case 4:
                                                                                Tabla.jugadorActual.setLocation(554, 598);
                                                                                controlador.turno = 4;
                                                                                break;
                                                                            default:
                                                                                throw new AssertionError();
                                                                        }
                                                                            
                                                                            //controlador.run();
                                                                    }
                                                                    timer.cancel();
                                                                }
                                                            }
                                                        }
                                                        counter++;
                                                        break;
                                                    case 4:
                                                        Tabla.jugadorActual.setLocation(554, 598);
                                                        if(counter == 1) 
                                                        {
                                                                movimientos = dado.lado();
                                                                terminar = movimientos+1;
                                                                jugador4.moverFicha(movimientos);
                                                        }
                                                        if(terminar != 0) 
                                                        {
                                                            if(jugador4.serpiente || jugador4.escalera) 
                                                            {
                                                                    terminar = counter + 2;
                                                            }
                                                            else 
                                                            {
                                                                if(counter == terminar)
                                                                {
                                                                    if(!jugador4.gano && !reiniciar) 
                                                                    {
                                                                            controlador.turno = 1;
                                                                            Tabla.jugadorActual.setLocation(278, 598);
                                                                            //controlador.run();
                                                                    }

                                                                    timer.cancel();
                                                                }
                                                            }
                                                        }
                                                        counter++;
                                                        break;
                                                    default:
                                                        throw new AssertionError();
                                                }
                                            }

                                    }, 0,900);
					
				}
			}
			
		    public void mouseEntered(MouseEvent e) 
                    {
		        button.setIcon(imagenMouse);
		    }

		    public void mouseExited(MouseEvent e) 
                    {
		        button.setIcon(imagen);

		    }
		});
		panel.add(text);
		panel.add(button);
		return panel;
	}
	
	
}