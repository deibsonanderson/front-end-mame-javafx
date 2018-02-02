/*
 * 
 */
package br.com.emulador;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * The Class Letter.
 */
public class Letter {


	/**
	 * Find letter.
	 *
	 * @param caracteres the caracteres
	 * @param index the index
	 * @return the char
	 */
	public char findLetter(final char[] caracteres, final int index) {
		final char retorno = caracteres[index];
		return retorno;
	}

	/**
	 * Charge letter.
	 *
	 * @param games the games
	 * @return the char[]
	 */
	public char[] chargeLetter(final List<Game> games) {
		final TreeSet<Character> sort = new TreeSet<Character>();
		for (final Game game : games) {
			sort.add(game.getName().charAt(0));
		}
		final char[] caracteres = new char[sort.size()];
		int count = 0;

		final Iterator<Character> iterator = sort.iterator();
		while (iterator.hasNext()) {
			caracteres[count] = iterator.next();
			++count;
		}
		return caracteres;
	}
	
	/**
	 * Sets the transition.
	 *
	 * @param labelLetter the new transition
	 */
	public void setTransition(Label labelLetter){
		FadeTransition fadeTransitionLogo = new FadeTransition(Duration.millis(3000), labelLetter);
		fadeTransitionLogo.setFromValue(1.0);
		fadeTransitionLogo.setToValue(0.0);
		fadeTransitionLogo.play();
	}
}
