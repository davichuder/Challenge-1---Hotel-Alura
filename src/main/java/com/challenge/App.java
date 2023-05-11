package com.challenge;

import java.awt.EventQueue;

import com.challenge.views.MenuPrincipal;

public class App 
{
	public static void main( String[] args )
    {
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
