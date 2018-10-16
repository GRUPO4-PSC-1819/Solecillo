package Persistencia;

import java.sql.Connection;

public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conec=clsBD.initBD("Data/Solecillo.bd");
		clsBD.crearTablaBD("Usuario");
		
	}

}
