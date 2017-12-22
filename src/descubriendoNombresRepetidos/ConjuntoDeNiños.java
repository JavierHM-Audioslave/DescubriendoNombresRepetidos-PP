package descubriendoNombresRepetidos;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ConjuntoDeNiños {
	
	private Niño[] arrDeNiños;
	private int cantDeChicos;
	private int topCuantos;
	
	public ConjuntoDeNiños()
	{
		try
		{
			File archIn=new File(JOptionPane.showInputDialog("Ingrese el path completo del archivo de entrada"));
			FileReader fr=new FileReader(archIn);
			BufferedReader br=new BufferedReader(fr);
			String[] linea=new String[2];
			linea=br.readLine().split(" ");
			cantDeChicos=Integer.parseInt(linea[0]);
			topCuantos=Integer.parseInt(linea[1]);
			arrDeNiños=new Niño[cantDeChicos];
			
			for(int i=0; i<arrDeNiños.length; i++)
			{
				arrDeNiños[i]=new Niño(br.readLine());
			}
			
			try
			{
				fr.close();
				br.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void generarSalida()
	{
		int usadosDistintos=0;
		ArrayList<String> nom=new ArrayList<String>(0);
		ArrayList<Integer> frec=new ArrayList<Integer>(0);
		int indiceArrayList=-1;
		int auxDeI;
		
		for(int i=0; i<cantDeChicos; i++)
		{
			auxDeI=0;
			for(int j=0; j<cantDeChicos; j++)
			{
				if(i!=j && arrDeNiños[i].getNombre().equals(arrDeNiños[j].getNombre()))
				{
					if(auxDeI==0)
					{
						indiceArrayList++;
						auxDeI=1;
					}
					
					//int hola=0;
					boolean confirmacion=false;
					for(String o:nom)
					{
							
						if(arrDeNiños[i].getNombre()==o)
						{
							frec.set(indiceArrayList,frec.get(indiceArrayList)+1);
							confirmacion=true;
							break;
						}
						//hola++;
					}
						
					if(confirmacion==false)
					{
						nom.add(arrDeNiños[i].getNombre());
						frec.add(1);
						usadosDistintos++;
					}
				}					
			//}
			//else
			//{
				//i=cantDeChicos;
				//j=cantDeChicos;
			//}
			}
		}
		
		String[] arrDeTops=buscandoLosMayores(nom,frec);
		
		try
		{
			File archSal=new File(JOptionPane.showInputDialog("Ingrese el path completo de salida"));
			FileWriter fw=new FileWriter(archSal);
			PrintWriter pw=new PrintWriter(fw);
			
			for(int i=0; i<arrDeTops.length; i+=2)
			{
				pw.print(arrDeTops[i]+" ");
				pw.println(Integer.parseInt(arrDeTops[i+1])+1);
			}
			
			try
			{
				fw.close();
				pw.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	private String[] buscandoLosMayores(ArrayList<String> nom, ArrayList<Integer> frec)
	{
		String[] arrConLosTops=new String[topCuantos*2];
		int algo=0, algoalgo=0;
		int llenos=0, cont=0, bandera;
		for(int i=0; llenos<arrConLosTops.length/2; i+=2)
		{
			arrConLosTops[algo]=nom.get(algoalgo);
			arrConLosTops[algo+1]=Integer.toString(frec.get(algoalgo));
			llenos++;
			bandera=0;
		
			for(int g=0; g<llenos*2; g+=2) // POR ACA //
			{
				if(!arrConLosTops[g].equals(arrConLosTops[algo]) || cont==0)
				{
					bandera++;
				}
			}
			if(bandera==(llenos-1) || cont==0)
			{
				algo+=2;
			}
			else
			{
				llenos--;
			}
			cont=1;
			algoalgo++;
		}
		
		int z=0;
		int[] auxDif=new int[arrConLosTops.length/2];
		int mayorTemporal;
		int posDeMayorTemporal=0;
		
		for(String o:nom)
		{	
			int y=1;
			for(int j=0; j<arrConLosTops.length/2; j++)
			{
				auxDif[j]=frec.get(z)-Integer.parseInt(arrConLosTops[y]);
				y+=2;
			}
			
			mayorTemporal=0;
			for(int j=0; j<auxDif.length; j++)
			{
				if(auxDif[j]>mayorTemporal)
				{
					mayorTemporal=auxDif[j];
					posDeMayorTemporal=j;
				}				
			}
			
			boolean yaExiste=false;
			
			if(mayorTemporal>0)
			{
				for(int c=0; c<auxDif.length*2; c+=2)
				{
					if(o.equals(arrConLosTops[c]))
					{
						yaExiste=true;
						break;
					}
				}
				
				if(yaExiste==false)
				{
					arrConLosTops[posDeMayorTemporal*2]=o;
					arrConLosTops[posDeMayorTemporal*2+1]=Integer.toString(frec.get(z));	//		Integer.toString(mayorTemporal);
				}
			}
			
			z++;
		}
		
		
		return arrConLosTops;
	}
	

}
