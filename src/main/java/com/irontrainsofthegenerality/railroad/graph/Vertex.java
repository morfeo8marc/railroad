package com.irontrainsofthegenerality.railroad.graph;

/**
 * A Vertex is an end point of {@link Edge}.
 * A Vertex can have a content of type T
 * @param <T> Is the type of the Vertex's content
 * 
 * @author morfeo8marc <github.com/morfeo8marc>
 * 
 */
public class Vertex<T> {
	/**
	 * The content of the Vertex
	 */
	private T content;
	/**
	 * The unique identifier of the vertex. It's used to access it.
	 */
	private int id;


	/**
	 * Constructs a new Vertex with the content set.
	 * This 
	 * 
	 * @param content The content of the Vertex
	 * @param id The identifier to be set
	 */
	public Vertex(T content, int id) {
		super();
		this.content = content;
		this.id = id;
	}

	/**
	 * Get the content return's the Vertex content
	 * @return the content of the Vertex
	 */
	public T getContent() {
		return content;
	}

	/**
	 * Set Contents assign the given content to the Vertex's content
	 * @param content to be setted
	 */
	public void setContent(T content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		}
		else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
