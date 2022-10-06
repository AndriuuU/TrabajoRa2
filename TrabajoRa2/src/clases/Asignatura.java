package clases;

public class Asignatura {

	private String codAdig,nombre;
	private int horas;
	private Profesor dniProfesor;
	
	public Asignatura(String codAdig, String nombre, int horas, Profesor dniProfesor) {
		super();
		this.codAdig = codAdig;
		this.nombre = nombre;
		this.horas = horas;
		this.dniProfesor = dniProfesor;
	}

	public String getCodAdig() {
		return codAdig;
	}

	public void setCodAdig(String codAdig) {
		this.codAdig = codAdig;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public Profesor getDniProfesor() {
		return dniProfesor;
	}

	public void setDniProfesor(Profesor dniProfesor) {
		this.dniProfesor = dniProfesor;
	}

	@Override
	public String toString() {
		return "Asignatura [codAdig=" + codAdig + ", nombre=" + nombre + ", horas=" + horas + ", dniProfesor="
				+ dniProfesor + "]";
	}
	
	
	
}
