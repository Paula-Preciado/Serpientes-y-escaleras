/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

import codigo.Tabla;

/**
 *
 * @author Usuario
 */
public class Controlador extends Thread implements Runnable
{
	private Jugador jugador2, jugador3, jugador4;
	protected int turno;
	protected static Thread hilo;
	
	
        public Controlador(Jugador[] jugadores) 
        {
		this.turno = 1;
		jugador2 = jugadores[1];
		jugador3 = jugadores[2];
                jugador4 = jugadores[3];
		
		hilo = new Thread(this);
		hilo.start();
	}
	

	/**
	 * Run. Inicializa a los jugadores 2 y 3 por orden, despues de que el jugador 1 (usuario: rojo), haya pulsado el bot�n lanzar
	 */
	@SuppressWarnings("static-access")
	@Override
	public void run() 
        {
		//Segun el turno que llame al jugador que es para que tire 
		switch(turno) 
                {
                    case 2:
			//Esperar que se tire el dado
			try {
				//Tabla.lanzar.setVisible(false); 
				Tabla.jugadorActual.setLocation(370, 598);
				Tabla.dado.lanzar();
				hilo.sleep(1000);
				int movimientos = Tabla.dado.lado();
				jugador2.moverFicha(movimientos);
				hilo.sleep(movimientos*750);
				if(jugador2.serpiente || jugador2.escalera) 
                                {
                                    hilo.sleep(1500);
				}
				if(!jugador2.gano && !Tabla.reiniciar) 
                                {
					turno++;
					run();
				}
				
			} catch (InterruptedException e) 
                        {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
                    case 3:
			//Esperar que se tire el dado
			try {
				Tabla.jugadorActual.setLocation(462, 598);
				Tabla.dado.lanzar();
				hilo.sleep(1000);
				int movimientos = Tabla.dado.lado();
				jugador3.moverFicha(movimientos);
				hilo.sleep(movimientos*700);
				if(jugador3.serpiente || jugador3.escalera) 
                                {
					hilo.sleep(1500);
				}
                                if(!jugador3.gano && !Tabla.reiniciar) 
                                {
					turno++;
					run();
				}
				
			} catch (InterruptedException e) 
                        {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
                    case 4:
			//Esperar que se tire el dado
			try {
				Tabla.jugadorActual.setLocation(554, 598);
				Tabla.dado.lanzar();
				hilo.sleep(1000);
				int movimientos = Tabla.dado.lado();
				jugador4.moverFicha(movimientos);
				hilo.sleep(movimientos*700);
				if(jugador4.serpiente || jugador4.escalera) 
                                {
					hilo.sleep(1500);
				}
				if(!jugador4.gano) 
                                {
					turno = 1;
					Tabla.lanzar.setVisible(true);
					Tabla.jugadorActual.setLocation(278, 598);
				}
				
			} catch (InterruptedException e) 
                        {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
	}

	
	
}
