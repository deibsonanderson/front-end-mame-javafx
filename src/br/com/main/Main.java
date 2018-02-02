package br.com.main;

import java.io.File;
import java.util.List;
import java.util.Random;

import br.com.emulador.Config;
import br.com.emulador.Emulator;
import br.com.emulador.Game;
import br.com.emulador.Letter;
import br.com.xml.Xml;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends Application {

	/** The Constant FILE_TYPE. */
	private final static String FILE_TYPE = "file:";

	/** The xml. */
	private final Xml xml = new Xml();
	
	/** The stage. */
	private Stage stage = null;

	/** The games. */
	private List<Game> games;

	/** The indice. */
	private Integer indice = 0;

	/** The Constant XML_URL. */
	private static final String XML_URL = ".\\";

	/** The config. */
	public static Config config;

	/** The visualizador imagem. */
	private final ImageView imageScreem = new ImageView();

	/** The visualizador imagem. */
	private final ImageView imageScreemDefault = new ImageView();
	
	/** The logo screem. */
	private final ImageView logoScreem = new ImageView();

	/** The label defaut. */
	private final Label labelDefaut = new Label();
	
	/** The label letter. */
	private final Label labelLetter = new Label();
	
	/** The bracket left. */
	private final String BRACKET_LEFT = "[";
	
	/** The bracket right. */
	private final String BRACKET_RIGHT = "]";

	/** The raiz. */
	private final StackPane raiz = new StackPane();
	
	/** The media player. */
	MediaPlayer mediaPlayer;

	/** The media. */
	Media media;

	/** The media view. */
	MediaView mediaView;
	
	/** The yes. */
	private static String YES = "yes";
	
	private static String YES_LETTER = "y";
	
	private static String NO_LETTER = "n";
	
	/** The no. */
	private static String NO = "no";
	
	/** The true. */
	private static String TRUE = "true";
	
	/** The false. */
	private static String FALSE = "false";
	

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		launch();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage palco) throws Exception {
		this.stage = palco;
		boolean erro = false;
		try {
			this.initConfigXml(XML_URL);
		} catch (final Exception e) {
			erro = true;
			config = new Config();
			config.setHeight(480);
			config.setTitle("Front End Mame JavaFX");
			config.setWidth(640);
		}
		labelLetter.setVisible(false);
		final Scene cena = this.initScene(this.raiz, config.getWidth(), config.getHeight());
		if ((this.games != null) && !this.games.isEmpty()) {
			this.raiz.getChildren().add(initImageScreemDefault());
			this.initImageScreem(this.imageScreem, this.changeImagem(this.indice));
			this.raiz.getChildren().add(this.imageScreem);
			this.raiz.getChildren().add(this.labelDefaut);
			this.mediaView = this.initVideo(this.indice);
			if (this.mediaView != null) {
				this.raiz.getChildren().add(this.mediaView);
			}
			this.changeLogo(this.indice);
			this.raiz.getChildren().add(this.labelLetter);
			this.raiz.getChildren().add(this.logoScreem);			
		} else if (erro) {
			final Label msgEmptyGames = new Label();
			msgEmptyGames.setText("Ouve um erro no XML favor verificar a estrutura basica no README!");
			this.raiz.getChildren().add(msgEmptyGames);
		} else {
			final Label msgEmptyGames = new Label();
			msgEmptyGames.setText("Não foi localizado jogos, favor veridicar o XML de configuração!");
			this.raiz.getChildren().add(msgEmptyGames);
		}
		this.initStage(this.stage, cena);
		this.setFocus(this.stage);

	}

	/**
	 * Change logo.
	 *
	 * @param indice
	 *            the indice
	 */
	private void changeLogo(final int indice) {
		this.stage.setTitle("Front End Mame JavaFX [ "+descriptionCheck(games.get(indice))+" ]");
		final Image image = new Image(FILE_TYPE + config.getLogoPath() + this.games.get(indice).getName() + config.getImageExt());
		this.logoScreem.setImage(image);
		this.logoScreem.setTranslateY(config.getEixoLogoY());
		this.logoScreem.setTranslateX(config.getEixoLogoX());
		this.logoScreem.setFitWidth(config.getWidthLogo());
		this.logoScreem.setFitHeight(config.getHeightLogo());
		this.logoScreem.setVisible(true);
	}

	/**
	 * Sets the focus.
	 *
	 * @param palco
	 *            the new focus
	 */
	private void setFocus(final Stage palco) {
		palco.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(final ObservableValue<? extends Boolean> observable, final Boolean oldValue,
					final Boolean newValue) {
				if (observable.getValue() && (Main.this.mediaPlayer != null)) {
					Main.this.mediaPlayer.play();
				} else if (Main.this.mediaPlayer != null) {
					Main.this.mediaPlayer.pause();
				}
			}
		});
	}

	/**
	 * Inits the config xml.
	 *
	 * @param path
	 *            the path
	 */
	private void initConfigXml(final String path) {
		final File[] files = this.xml.carregar(path);
		config = this.xml.carregarConfigTag(path, files[0]);
		this.games = this.xml.carregarGamesTag(path, files[0]);
	}

	/**
	 * Inits the video.
	 *
	 * @param indice
	 *            the indice
	 * @return the media view
	 */
	private MediaView initVideo(final int indice) {
		try {
			final StringBuffer path = new StringBuffer(config.getVideoPath());
			path.append(this.games.get(indice).getName());
			path.append(config.getVideoExt());
			final File file = new File(path.toString());
			if ((file != null) && file.exists()) {
				this.media = new Media(new File(path.toString()).toURI().toString());
				this.mediaPlayer = new MediaPlayer(this.media);
				this.mediaView = new MediaView(this.mediaPlayer);
				this.mediaView.setFitHeight(config.getHeightVideo());
				this.mediaView.setFitWidth(config.getWidthVideo());
				this.mediaView.setTranslateX(config.getEixoVideoX());
				this.mediaView.setTranslateY(config.getEixoVideoY());

				this.mediaPlayer.setMute(booleanCheck(config.getVideoMute()));
				
				this.mediaPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
				this.mediaPlayer.play();
				return this.mediaView;
			} else {
				return null;
			}

		} catch (final Exception e) {
			return null;
		}
	}

	/**
	 * Change video.
	 *
	 * @param indice
	 *            the indice
	 */
	private void changeVideo(final int indice) {
		if (this.mediaPlayer != null) {
			this.mediaPlayer.stop();
		}
		final StringBuffer path = new StringBuffer(config.getVideoPath());
		path.append(this.games.get(indice).getName());
		path.append(config.getVideoExt());
		final File file = new File(path.toString());
		if ((file != null) && file.exists()) {
			if (this.mediaView == null) {
				this.raiz.getChildren().add(this.initVideo(indice));
			}
			this.mediaView.setVisible(true);
			this.mediaPlayer.stop();
			this.media = new Media(new File(path.toString()).toURI().toString());
			this.mediaPlayer = new MediaPlayer(this.media);
			this.mediaView.setMediaPlayer(this.mediaPlayer);
			
			this.mediaPlayer.setMute(booleanCheck(config.getVideoMute()));
			
			this.mediaPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
			this.mediaPlayer.play();
		} else if (this.mediaPlayer != null) {
			this.mediaView.setVisible(false);
		}
	}

	/**
	 * Inits the scene.
	 *
	 * @param raiz
	 *            the raiz
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return the scene
	 */
	private Scene initScene(final StackPane raiz, final int width, final int height) {
		final Scene cena = new Scene(raiz, width, height);

		/**
		 * Metodo responsavel por carregarmento de keyPress
		 */
		cena.setOnKeyPressed(new EventHandler<KeyEvent>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see javafx.event.EventHandler#handle(javafx.event.Event)
			 */
			@Override
			public void handle(final KeyEvent event) {
				if (!Main.this.games.isEmpty()) {
					switch (event.getCode()) {
					case ENTER:
						if(Main.this.mediaView != null){
							Main.this.mediaPlayer.stop();
						}
						new Emulator().run(Main.this.games.get(Main.this.indice), config.getEmuPath(),
								config.getEmuExec());
						break;
					case UP:
						Main.this.imageScreem.setImage(Main.this.changeImagem(--Main.this.indice));
						Main.this.changeVideo(Main.this.indice);
						Main.this.changeLogo(Main.this.indice);
						break;
					case DOWN:
						Main.this.imageScreem.setImage(Main.this.changeImagem(++Main.this.indice));
						Main.this.changeVideo(Main.this.indice);
						Main.this.changeLogo(Main.this.indice);
						break;
					case PAGE_UP:
						Main.this.indice = Main.this.indice + 10;
						Main.this.imageScreem.setImage(Main.this.changeImagem(Main.this.indice));
						Main.this.changeVideo(Main.this.indice);
						Main.this.changeLogo(Main.this.indice);
						break;
					case PAGE_DOWN:
						Main.this.indice = Main.this.indice - 10;
						Main.this.imageScreem.setImage(Main.this.changeImagem(Main.this.indice));
						Main.this.changeVideo(Main.this.indice);
						Main.this.changeLogo(Main.this.indice);
						break;
					case SPACE:
						final Random random = new Random();
						Main.this.indice = random.nextInt((Main.this.games.size()));
						Main.this.imageScreem.setImage(Main.this.changeImagem(Main.this.indice));
						Main.this.changeVideo(Main.this.indice);
						Main.this.changeLogo(Main.this.indice);
						break;
					case RIGHT:
						findNextLetter(true);
						break;
					case LEFT:
						findNextLetter(false);
						break;
					case ESCAPE:
						System.exit(0);
						break;
					default:
						break;
					}
				}
			}

		});
		return cena;
	}

	/**
	 * Find next letter.
	 *
	 * @param isNext the is acendent
	 */
	private void findNextLetter(final boolean isNext) {
		int ctrl = 0;
		if ((Main.this.games != null) && !Main.this.games.isEmpty()) {
			final Letter let = new Letter();
			final char[] caracteres = let.chargeLetter(Main.this.games);
			for (int i = 0; i < caracteres.length; i++) {
				if (caracteres[i] == Main.this.games.get(Main.this.indice).getName().charAt(0)) {
					if(isNext){
						ctrl = (i+1);
					}else{
						ctrl = (i-1);
					}
					break;
				}
			}
			if(ctrl > (caracteres.length-1)){
				ctrl = 0;
			}else if(ctrl < 0 ){
				ctrl = (caracteres.length-1);
			}			
			for (int i = 0; i < Main.this.games.size(); i++) {
				if (caracteres[ctrl] == Main.this.games.get(i).getName().charAt(0)) {
					changeLetter(caracteres[ctrl]);
					Main.this.indice = i;
					Main.this.imageScreem.setImage(Main.this.changeImagem(Main.this.indice));
					Main.this.changeVideo(Main.this.indice);
					Main.this.changeLogo(Main.this.indice);
					break;
				}
			}
		}
	}

	/**
	 * Inits the stage.
	 *
	 * @param palco
	 *            the palco
	 * @param cena
	 *            the cena
	 */
	private void initStage(final Stage palco, final Scene cena) {
		palco.setTitle(config.getTitle());
		palco.setScene(cena);
		palco.setResizable(false);
		
		palco.setAlwaysOnTop(booleanCheck(config.getOnTop()));
		palco.setMaximized(booleanCheck(config.getMaximized()));

		if (NO.equalsIgnoreCase(config.getWindowBar()) || 
				FALSE.equalsIgnoreCase(config.getWindowBar()) ||
				NO_LETTER.equalsIgnoreCase(config.getWindowBar())){
			palco.initStyle(StageStyle.UNDECORATED);
		}
		palco.show();

	}

	/**
	 * Change imagem.
	 *
	 * @param indice
	 *            the indice
	 * @return the image
	 */
	
	
	private Image changeImagem(final int indice) {
		final StringBuffer path = new StringBuffer(config.getImagePath());
		path.append(this.games.get(this.controladorIndice(indice)).getName());
		path.append(config.getImageExt());
		final File file = new File(path.toString());
		final Image image;
		if (file.exists() && !file.isDirectory()) {
			image = new Image(new StringBuffer(FILE_TYPE).append(path).toString());
			Main.this.labelDefaut.setText("");
		} else {
			image = new Image(new StringBuffer(FILE_TYPE).append(config.getImagePath()).append(config.getImageDefaut())
					.append(config.getImageExt()).toString());

			Main.this.labelDefaut.setText(BRACKET_LEFT + descriptionCheck(Main.this.games.get(Main.this.indice)) + BRACKET_RIGHT);
			Main.this.labelDefaut.setStyle("-fx-color:black;-fx-font: 50px Tahoma;");
			Main.this.labelDefaut.setTranslateY(120);
			this.stage.setTitle(config.getTitle()+" - "+BRACKET_LEFT+descriptionCheck(this.games.get(this.controladorIndice(indice)))+BRACKET_RIGHT);
		}
		return image;
	}

	/**
	 * Controlador indice.
	 *
	 * @param value
	 *            the value
	 * @return the int
	 */
	private int controladorIndice(final int value) {
		final int total = (this.games.size() - 1);
		if (value < 0) {
			this.indice = total;
		} else if (value > total) {
			this.indice = 0;
		}
		return this.indice;
	}

	/**
	 * Inits the image screem.
	 *
	 * @param imagemScreem
	 *            the imagem screem
	 * @param path
	 *            the path
	 */
	private void initImageScreem(final ImageView imagemScreem, final Image path) {
		imagemScreem.setImage(path);
		imagemScreem.setTranslateX(config.getEixoImageX());
		imagemScreem.setTranslateY(config.getEixoImageY());
		imagemScreem.setFitWidth(config.getWidthImage());
		imagemScreem.setFitHeight(config.getHeightImage());
	}
	
	/**
	 * Inits the image screem default.
	 *
	 * @return the image view
	 */
	private ImageView initImageScreemDefault() {
		final StringBuffer path = new StringBuffer(config.getImagePath());
		path.append(config.getImageDefaut());
		path.append(config.getImageExt());		
		imageScreemDefault.setImage(new Image(new File(path.toString()).toURI().toString()));
		imageScreemDefault.setFitWidth(config.getWidth());
		imageScreemDefault.setFitHeight(config.getHeight());
		return imageScreemDefault;
	}

	
	
	/**
	 * Change letter.
	 *
	 * @param letter the letter
	 */
	private void changeLetter(final Character letter) {
		labelLetter.setText(letter.toString().toUpperCase());
		labelLetter.setVisible(true);
		labelLetter.setStyle("-fx-font: 200px 'Serif'; -fx-text-fill: #FFFF00");
		int x = config.getWidth();
		int y = config.getHeight();
		
		labelLetter.setTranslateX((-(x/3)));
		labelLetter.setTranslateY((y/3));
		new Letter().setTransition(labelLetter);
	}
	
	
	/**
	 * Description check.
	 *
	 * @param game the game
	 * @return the string
	 */
	private String descriptionCheck(final Game game){
		String retorno = game.getName().toUpperCase();
		if(game.getDescription() != null && !game.getDescription().isEmpty()){
			retorno = game.getDescription();
		}
		return retorno;
	}
	
	/**
	 * Boolean check.
	 *
	 * @param value the valor
	 * @return true, if successful
	 */
	private boolean booleanCheck(final String value){
		boolean retorno = false;
		if(YES.equalsIgnoreCase(value) || TRUE.equalsIgnoreCase(value) || YES_LETTER.equalsIgnoreCase(value)){
			retorno = true;
		}
		return retorno;
	}

}
