/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

import java.awt.Color;

import javax.swing.JPanel;


public class Ficha extends JPanel 
{
	private static final long serialVersionUID = 1L;
	protected static final int tama = 20;
	
	/**
	 * Instantiates a new ficha. Permite crear una ficha con su respectivo tama�o y color.
	 * @param color the color
	 */
	public Ficha(Color color) {
		this.setSize(tama,tama);
		this.setBackground(color);
	}
}
