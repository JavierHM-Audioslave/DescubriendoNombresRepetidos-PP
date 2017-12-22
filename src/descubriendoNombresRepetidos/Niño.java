package descubriendoNombresRepetidos;	

public class Niño { // PREGUNTAR: Como el unico atributo del niño es el nombre, preguntar si esta bien escribir esta clase o si los datos de los nombres se podrian haber leido directamente a un atributo array de String en la clase LogicaDeCreacion. //
	
	private String nombre;
	
	public Niño(String nombre)
	{
		this.nombre=nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
