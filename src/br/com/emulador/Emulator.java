/*
 * 
 */
package br.com.emulador;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class Emulator.
 *
 * @author deibson_magali
 */
public class Emulator {

	/** The Constant ESPACO. */
	private static final String ESPACO = " ";

	/**
	 * Run.
	 *
	 * @param game the game
	 * @param path the path
	 * @param exec the exec
	 */
	public void run(final Game game, final String path, final String exec) {

		final StringBuilder cmd = new StringBuilder(path);
		cmd.append(emulatorCurtomCheck(game,path, exec));
		cmd.append(ESPACO);
		cmd.append(game.getName());

		try {
			Runtime.getRuntime().exec(cmd.toString(), null, new File(path));
		} catch (final IOException ex) {
			Logger.getLogger(Emulator.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	/**
	 * Emulator curtom check.
	 *
	 * @param game the game
	 * @param exec the exec
	 * @return the string
	 */
	private String emulatorCurtomCheck(final Game game,final String path,final String exec){
		String retorno = exec;
		if(game.getEmulator() != null && !game.getEmulator().isEmpty()){
			if(new File(path+game.getEmulator()).exists()){
				retorno = game.getEmulator();
			}
		}
		return retorno;
	}	

}
