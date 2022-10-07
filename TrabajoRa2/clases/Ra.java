package clases;

public class Ra {
	
	private String id, nombre,descripcion;
	private float ponderacion;
	private Asignatura codAsig;
	
	public Ra(String id, String nombre, String descripcion, float ponderacion, Asignatura codAsig) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ponderacion = ponderacion;
		this.codAsig = codAsig;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(float ponderacion) {
		this.ponderacion = ponderacion;
	}

	public Asignatura getCodAsig() {
		return codAsig;
	}

	public void setCodAsig(Asignatura codAsig) {
		this.codAsig = codAsig;
	}

	@Override
	public String toString() {
		return "Ra [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", ponderacion=" + ponderacion
				+ ", codAsig=" + codAsig + "]";
	}
	
	
	
}
