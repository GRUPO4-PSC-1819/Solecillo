package LP;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import LN.clsMaquina_Hidraulica;
import java.awt.GridLayout;
import java.util.ArrayList;

/**
 * Clase que generará un JPanel que contendrá la tabla con los datos extraídos de BD, para poder insertarla después en clsRankingLista.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsTablaH extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	JTable table;
	
	/*Renderer de la tabla*/
	private static DefaultTableCellRenderer rendererCentrado = new DefaultTableCellRenderer();
	static 
	{
		rendererCentrado.setHorizontalAlignment(JLabel.CENTER);
	}

    public clsTablaH(ArrayList<clsMaquina_Hidraulica> u) 
    {
    	super(new GridLayout(1,0));
    	JFrame frame = new JFrame("Maquinas Hidráulicas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        table = new JTable(new MyTableModel(u));
        table.setFillsViewportHeight(true);
        
        table.getColumn("ID").setCellRenderer(rendererCentrado);
        table.getColumn("Valor").setCellRenderer(rendererCentrado);

        table.getColumn("ID").setPreferredWidth(25);
        table.getColumn("Valor").setPreferredWidth(25);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
    
    /**
	 *Clase interna para el manejo del modelo de datos del objeto JTable.
	 *@see <a href="http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableDemoProject/src/components/TableDemo.java">
	 *http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableDemoProject/src/components/TableDemo.java </a>
	 */	
    class MyTableModel extends AbstractTableModel 
    {
		private static final long serialVersionUID = 1L;
		
		private String[] columnNames = {"ID",
                "Nombre",
                "Color",
                "Valor",
                "Fabricante",
                "Estado",
                "Nombre pueblo",
                "Nombre río"};
        private Object[][] data;
        
        /**
         * Constructor del modelo de datos.
         * @param Lista de usuarios.
         */
        public MyTableModel(ArrayList<clsMaquina_Hidraulica> u)
        {
        	super();
    		int filas = u.size();
    		int cont;
    		data=new Object[filas][];
    		cont=0;
    		
    		
    		for (clsMaquina_Hidraulica aux : u)
    		{
    			Object[]n={new Integer(aux.getId()),
    					   new String(aux.getNombre()),	    					
    					   new String(aux.getColor()),
    					   new Double(aux.getValor()),
    					   new String(aux.getFabricante()),
    					   new String(aux.getEstado()),
    					   new String(aux.getNombre_pueblo()),
    					   new String(aux.getNombre_rio())};
    			data[cont]=n;
    			cont++;
    		}
        }
        
        public int getColumnCount() 
        {
            return columnNames.length;
        }

        public int getRowCount() 
        {
            return data.length;
        }

        public String getColumnName(int col) 
        {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) 
        {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) 
        {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) 
        {
                return false;
        }
    }
    
 public int getFila() {
		
    	int id;
    	try
		{
    		id=(int)table.getValueAt(table.getSelectedRow(), 0);
			return id;
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			JOptionPane.showMessageDialog(null, "Seleccione una máquina.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return -1;
	}
 
	public void setRowSelectionInterval(int i, int j) {
		// TODO Auto-generated method stub
		table.setRowSelectionInterval(i, i);
	}
}