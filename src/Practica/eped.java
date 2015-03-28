import java.io.*;
import Biblioteca.Listas.*;
import Biblioteca.Iterator.*;
/**
 * Write a description of class eped2015 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class eped
{
    private QueryDepotList deposito;
    private ListIF<Query> consultas;
    private Query consulta;
    //private static final String file = "texto.txt";
    private static String file, prefix;
    private IteratorIF<Query> itr;
    public static void main (String [ ] args) {
        file = args[0];
        prefix = args[1];
        new eped(file,prefix);
    }
    
    /**
     * Constructor for objects of class eped2015
     */
    public eped(String file, String prefix)
    {
        // initialise instance variables
        //System.out.println("Bien");
        deposito = new QueryDepotList();
        readFile(deposito,file);
        consultas = deposito.listOfQueries(prefix);
        itr = consultas.getIterator();
        while(itr.hasNext())  {
            Query q = itr.getNext();
            System.out.println("" +q.getText() +" se repite: "+ q.getFreq() +" veces.");
        }
    }
    
    public int numeroConsulas()
    {
        return deposito.numQueries();
    }
    
    public void imprimirConsultas()
    {
        
    }
    
    private void readFile(QueryDepotList deposito, String file)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String response = reader.readLine();
            while(response != null)  {
                deposito.incFreqQuery(response);
                response = reader.readLine();
            }
            reader.close();
        }
        catch(FileNotFoundException e)  {
            System.err.println("No se puede abrir " + file);
        }
        catch(IOException e)  {
            System.err.println("Hubo un error al leer " + file);
        }
        
        if(deposito.numQueries() == 0)  {
            //consulta = new Query("sinConsultas");
            deposito.incFreqQuery("sinConsultas");
        }
        
    }  
}