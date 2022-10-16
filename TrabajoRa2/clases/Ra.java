package clases;

public class Ra {

	private String id, name, description;
	private float weighting;
	private Subjects codSubject;

	public Ra(String id, String name, String description, float weighting, Subjects codSubject) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.weighting = weighting;
		this.codSubject = codSubject;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getWeighting() {
		return weighting;
	}

	public void setWeighting(float weighting) {
		this.weighting = weighting;
	}

	public Subjects getCodSubject() {
		return codSubject;
	}

	public void setCodSubject(Subjects codSubject) {
		this.codSubject = codSubject;
	}

	@Override
	public String toString() {
		return "Ra [id=" + id + ", name=" + name + ", description=" + description + ", weighting=" + weighting
				+ ", codSubject=" + codSubject + "]";
	}

}
