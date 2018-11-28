package LP;

import javax.swing.JFrame;

import org.junit.Test;

import LN.clsMaquina_Eolica;
import LN.clsMaquina_Hidraulica;
import LN.clsMaquina_Mareomotriz;
import LN.clsMaquina_Solar;
import LN.clsUsuario;

public class LP_Test {

	@Test
	public void test() {
		clsUsuario u=new clsUsuario();
		ProgressBar pb=new ProgressBar("", "", u);
		
		clsListaM a=new clsListaM("", "VISUALIZAR", "");
		
		JFrame j=null;
		clsMaquina_Eolica e=new clsMaquina_Eolica();
		clsMaquina_Hidraulica h=new clsMaquina_Hidraulica();
		clsMaquina_Mareomotriz m=new clsMaquina_Mareomotriz();
		clsMaquina_Solar s=new clsMaquina_Solar();
		
		clsModificarEolica me=new clsModificarEolica(e, j);
		clsModificarHidraulica mh=new clsModificarHidraulica(h, j);
		clsModificarMareomotriz mm=new clsModificarMareomotriz(m, j);
		clsModificarSolar ms=new clsModificarSolar(s, j);
		
		
		principalFrame pf=new principalFrame(u);
	}

}

