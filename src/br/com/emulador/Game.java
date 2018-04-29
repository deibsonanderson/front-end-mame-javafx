package br.com.emulador;

/**
 * The Class Game.
 */
public class Game implements Comparable<Game> {
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The emulator. */
	private String emulator;
	
	private String app;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the emulator.
	 *
	 * @return the emulator
	 */
	public String getEmulator() {
		return emulator;
	}
	
	/**
	 * Sets the emulator.
	 *
	 * @param emulator the new emulator
	 */
	public void setEmulator(String emulator) {
		this.emulator = emulator;
	}

	/**
	 * Instantiates a new game.
	 */
	public Game() {
		super();
	}

	/**
	 * Instantiates a new game.
	 *
	 * @param name the name
	 */
	public Game(String name) {
		super();
		this.name = name;
	}

	/**
	 * Instantiates a new game.
	 *
	 * @param name the name
	 * @param emulator the emulator
	 */
	public Game(String name, String emulator) {
		super();
		this.name = name;
		this.emulator = emulator;
	}
	
	
	public Game(String name, String emulator, String description, String app) {
		super();
		this.name = name;
		this.description = description;
		this.emulator = emulator;
		this.app = app;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	/**
	 * Instantiates a new game.
	 *
	 * @param name the name
	 * @param emulator the emulator
	 * @param description the description
	 */
	public Game(String name, String emulator, String description) {
		super();
		this.name = name;
		this.description = description;
		this.emulator = emulator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Game other = (Game) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Game o) {
		return this.getName().compareTo(o.getName());
	}
	
	
	
	
	

}
