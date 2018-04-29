package br.com.xml;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.emulador.Config;
import br.com.emulador.Game;

/**
 * The Class Xml.
 *
 * @author deibson_magali
 */
public class Xml {

	/** The Constant XML. */
	private static final String XML_EXTENSION = ".xml";

	/** The Constant TAG_GAME. */
	private static final String TAG_GAME = "game";

	/** The Constant TAG_NAME. */
	private static final String TAG_NAME = "name";

	/** The Constant TAG_EMULATOR. */
	private static final String TAG_EMULATOR = "emulator";

	/** The Constant TAG_EMUPATH. */
	private static final String TAG_EMUPATH = "emupath";

	/** The Constant TAG_EXEC. */
	private static final String TAG_EXEC = "exec";

	/** The Constant TAG_IMAGEPATH. */
	private static final String TAG_IMAGEPATH = "imagepath";

	/** The Constant TAG_WIDTH. */
	private static final String TAG_WIDTH = "width";

	/** The Constant TAG_HEIGHT. */
	private static final String TAG_HEIGHT = "height";

	/** The Constant TAG_TITLE. */
	private static final String TAG_TITLE = "title";

	/** The Constant TAG_IMAGEEXT. */
	private static final String TAG_IMAGEEXT = "imageext";

	/** The Constant TAG_VIDEOPATH. */
	private static final String TAG_VIDEOPATH = "videopath";

	/** The Constant TAG_LOGOPATH. */
	private static final String TAG_LOGOPATH = "logopath";

	/** The Constant TAG_VIDEO_MUTE. */
	private static final String TAG_VIDEO_MUTE = "videomute";

	/** The Constant TAG_VIDEOEXT. */
	private static final String TAG_VIDEOEXT = "videoext";

	/** The Constant TAG_IMAGEDEFAUT. */
	private static final String TAG_IMAGEDEFAUT = "imagedefaut";

	/** The Constant TAG_CONFIG. */
	private static final String TAG_CONFIG = "config";

	/** The Constant TAG_WIDTH_VIDEO. */
	private static final String TAG_WIDTH_VIDEO = "widthvideo";

	/** The Constant TAG_HEIGHT_VIDEO. */
	private static final String TAG_HEIGHT_VIDEO = "heightvideo";

	/** The Constant TAG_EIXO_VIDEO_Y. */
	private static final String TAG_EIXO_VIDEO_Y = "eixovideoy";

	/** The Constant TAG_EIXO_VIDEO_X. */
	private static final String TAG_EIXO_VIDEO_X = "eixovideox";

	/** The Constant TAG_MAXIMIZED. */
	private static final String TAG_MAXIMIZED = "maximized";

	/** The Constant TAG_WIDTH_LOGO. */
	private static final String TAG_WIDTH_LOGO = "widthlogo";

	/** The Constant TAG_HEIGHT_LOGO. */
	private static final String TAG_HEIGHT_LOGO = "heightlogo";

	/** The Constant TAG_EIXO_LOGO_Y. */
	private static final String TAG_EIXO_LOGO_Y = "eixologoy";

	/** The Constant TAG_EIXO_LOGO_X. */
	private static final String TAG_EIXO_LOGO_X = "eixologox";

	/** The Constant TAG_WINDOW_BAR. */
	private static final String TAG_WINDOW_BAR = "windowbar";
	
	/** The Constant TAG_ON_TOP. */
	private static final String TAG_ON_TOP = "ontop";
	
	/** The Constant TAG_WIDTH_LOGO. */
	private static final String TAG_WIDTH_IMAGE = "widthimage";

	/** The Constant TAG_HEIGHT_LOGO. */
	private static final String TAG_HEIGHT_IMAGE = "heightimage";

	/** The Constant TAG_EIXO_LOGO_Y. */
	private static final String TAG_EIXO_IMAGE_Y = "eixoimagey";

	/** The Constant TAG_EIXO_LOGO_X. */
	private static final String TAG_EIXO_IMAGE_X = "eixoimagex";

	/** The Constant TAG_EMU_CUSTOM. */
	private static final String TAG_EMU_CUSTOM = "emucustom";
	
	private static final String TAG_APP = "app";

	/** The Constant TAG_DESCRIPTION. */
	private static final String TAG_DESCRIPTION = "description";
	
	/** The yes. */
	private static String YES = "yes";
	
	/** The no. */
	private static String NO = "no";
	
	/**
	 * Carregar.
	 *
	 * @param path the path
	 * @return the list
	 */
	public File[] carregar(final String path) {
		return this.dirListFiles(XML_EXTENSION, path);
	}
	
	public File carregar(){
		File file = null;
		if(new File("frontmamefx.xml").exists()){
			file = new File("frontmamefx.xml");
		}else{
			file = crearXMLFile();			
		}
		return file;
	}
	
	public File crearXMLFile() {
		File file = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.newDocument();
			Element emulator = document.createElement("emulator");
			document.appendChild(emulator);
			Element games = document.createElement("games");
			Element config = document.createElement("config");
			emulator.appendChild(config);
			emulator.appendChild(games);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File("frontmamefx.xml"));
			transformer.transform(source, result);
			file = new File("frontmamefx.xml");
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
		} catch (TransformerConfigurationException ex) {
			Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
		} catch (TransformerException ex) {
			Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
		}
		return file;
	}

	/**
	 * Carregar games tag.
	 *
	 * @param path the path
	 * @param file the file
	 * @return the list
	 */
	public List<Game> carregarGamesTag(final String path, final File file) {
		final List<Game> games = new ArrayList<Game>();
		final Document doc = this.documentFactory(path, file);
		Game gameObj;
		try {
			final int totalGames = doc.getElementsByTagName(TAG_GAME).getLength();
			for (int f = 0; f < totalGames; f++) {
				final Element game = (Element) doc.getElementsByTagName(TAG_GAME).item(f);
				gameObj = new Game(game.getAttribute(TAG_NAME), game.getAttribute(TAG_EMU_CUSTOM), game.getAttribute(TAG_DESCRIPTION), game.getAttribute(TAG_APP));
				games.add(gameObj);
			}
		} catch (final Exception ex) {
			Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
		}
		java.util.Collections.sort(games);
		return games;
	}

	/**
	 * Removes the node.
	 *
	 * @param doc the doc
	 * @param inicio the inicio
	 * @param desejadoRemover the desejado remover
	 * @param path the path
	 * @param file the file
	 * @return the document
	 */
	public Document removeNode(final Document doc, final String inicio, final String desejadoRemover, final String path,
			final File file) {
		final Node pai = doc.getElementsByTagName(inicio).item(0);
		if ((pai != null) && pai.hasChildNodes()) {
			final NodeList filhos = pai.getChildNodes();
			for (int i = 0; i < filhos.getLength(); i++) {
				if (filhos.item(i).getNodeName().equals(desejadoRemover)) {
					pai.removeChild(filhos.item(i));
					break;
				} else {
					this.removeNode(doc, filhos.item(i).getNodeName(), desejadoRemover, path, file);
				}
			}
		}
		return doc;
	}

	/**
	 * Carregar config tag.
	 *
	 * @param path the path
	 * @param file the file
	 * @return the config
	 */
	public Config carregarConfigTag(final String path, final File file) {
		final Config config = new Config();
		this.hasTagEmulator(path, file);
		this.hasTagConfig(path, file);

		final Document doc = this.documentFactory(path, file);
		final Node nNode = doc.getElementsByTagName(TAG_CONFIG).item(0);
		Element eElement;
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			eElement = (Element) nNode;
			config.setEmuPath(this.getTagElement(eElement, TAG_EMUPATH, ".\\", doc, path + file));
			config.setEmuExec(this.getTagElement(eElement, TAG_EXEC, "mame.exe", doc, path + file));
			config.setHeight(Integer.valueOf(this.getTagElement(eElement, TAG_HEIGHT, "480", doc, path + file)));
			config.setImagePath(this.getTagElement(eElement, TAG_IMAGEPATH, ".\\image\\", doc, path + file));
			config.setTitle(this.getTagElement(eElement, TAG_TITLE, "Front End Mame JavaFX", doc, path + file));
			config.setWidth(Integer.valueOf(this.getTagElement(eElement, TAG_WIDTH, "640", doc, path + file)));
			config.setImageExt(this.getTagElement(eElement, TAG_IMAGEEXT, "png", doc, path + file));
			config.setImageDefaut(this.getTagElement(eElement, TAG_IMAGEDEFAUT, "imageNotFound", doc, path + file));

			config.setVideoExt(this.getTagElement(eElement, TAG_VIDEOEXT, "flv", doc, path + file));
			config.setVideoPath(this.getTagElement(eElement, TAG_VIDEOPATH, ".\\video\\", doc, path + file));

			config.setLogoPath(this.getTagElement(eElement, TAG_LOGOPATH, ".\\logo\\", doc, path + file));
			config.setVideoMute(this.getTagElement(eElement, TAG_VIDEO_MUTE, NO, doc, path + file));

			config.setEixoVideoX(
					Integer.valueOf(this.getTagElement(eElement, TAG_EIXO_VIDEO_X, "0", doc, path + file)));
			config.setEixoVideoY(
					Integer.valueOf(this.getTagElement(eElement, TAG_EIXO_VIDEO_Y, "0", doc, path + file)));
			config.setHeightVideo(
					Integer.valueOf(this.getTagElement(eElement, TAG_HEIGHT_VIDEO, "480", doc, path + file)));
			config.setMaximized(this.getTagElement(eElement, TAG_MAXIMIZED, NO, doc, path + file));
			config.setWidthVideo(
					Integer.valueOf(this.getTagElement(eElement, TAG_WIDTH_VIDEO, "640", doc, path + file)));

			config.setWindowBar(this.getTagElement(eElement, TAG_WINDOW_BAR, YES, doc, path + file));
			config.setOnTop(this.getTagElement(eElement, TAG_ON_TOP, YES, doc, path + file));
			config.setEixoLogoX(Integer.valueOf(this.getTagElement(eElement, TAG_EIXO_LOGO_X, "0", doc, path + file)));
			config.setEixoLogoY(Integer.valueOf(this.getTagElement(eElement, TAG_EIXO_LOGO_Y, "-130", doc, path + file)));
			config.setHeightLogo(
					Double.valueOf(this.getTagElement(eElement, TAG_HEIGHT_LOGO, "200", doc, path + file)));
			config.setWidthLogo(Double.valueOf(this.getTagElement(eElement, TAG_WIDTH_LOGO, "400", doc, path + file)));
			
			config.setEixoImageX(Integer.valueOf(this.getTagElement(eElement, TAG_EIXO_IMAGE_X, "0", doc, path + file)));
			config.setEixoImageY(Integer.valueOf(this.getTagElement(eElement, TAG_EIXO_IMAGE_Y, "0", doc, path + file)));
			config.setHeightImage(
					Double.valueOf(this.getTagElement(eElement, TAG_HEIGHT_IMAGE, "480", doc, path + file)));
			config.setWidthImage(Double.valueOf(this.getTagElement(eElement, TAG_WIDTH_IMAGE, "640", doc, path + file)));
		}

		return config;
	}
	
	
	/**
	 * Gets the tag element.
	 *
	 * @param element the element
	 * @param tagName the tag name
	 * @param defaultValue the default value
	 * @param doc the doc
	 * @param pathFile the path file
	 * @return the tag element
	 */
	private String getTagElement(final Element element, final String tagName, final String defaultValue,
			final Document doc, final String pathFile) {
		String retorno;
		try {
			retorno = element.getElementsByTagName(tagName).item(0).getTextContent();
		} catch (final Exception e) {
			retorno = defaultValue;
			this.createTag(doc, element.getNodeName(), tagName, pathFile, defaultValue);
			this.transFormeXML(doc, pathFile);
		}
		return retorno;
	}

	/**
	 * Checks for tag config.
	 *
	 * @param path the path
	 * @param file the file
	 * @return true, if successful
	 */
	private boolean hasTagConfig(final String path, final File file) {
		boolean isValid = true;
		final Document doc = this.documentFactory(path, file);
		Node nNode = doc.getElementsByTagName(TAG_CONFIG).item(0);
		if (nNode == null) {
			isValid = false;
			// Remove todos as Tags ja conhecidas
			this.removeNode(doc, TAG_EMULATOR, TAG_EMUPATH, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_EXEC, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_IMAGEPATH, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_WIDTH, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_HEIGHT, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_TITLE, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_IMAGEEXT, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_IMAGEDEFAUT, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_VIDEOEXT, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_VIDEOPATH, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_LOGOPATH, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_VIDEO_MUTE, path, file);

			this.removeNode(doc, TAG_EMULATOR, TAG_EIXO_VIDEO_X, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_EIXO_VIDEO_Y, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_MAXIMIZED, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_WIDTH_VIDEO, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_HEIGHT_VIDEO, path, file);

			this.removeNode(doc, TAG_EMULATOR, TAG_EIXO_LOGO_X, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_EIXO_LOGO_Y, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_WIDTH_LOGO, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_HEIGHT_LOGO, path, file);

			this.removeNode(doc, TAG_EMULATOR, TAG_EIXO_IMAGE_X, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_EIXO_IMAGE_Y, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_WIDTH_IMAGE, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_HEIGHT_IMAGE, path, file);
		
			this.removeNode(doc, TAG_EMULATOR, TAG_WINDOW_BAR, path, file);
			this.removeNode(doc, TAG_EMULATOR, TAG_ON_TOP, path, file);

			nNode = doc.getElementsByTagName(TAG_EMULATOR).item(0);
			final Element eElement = (Element) nNode;
			// Cria a tag config
			this.createTag(doc, eElement.getNodeName(), TAG_CONFIG, path + file, null);
			this.transFormeXML(doc, path + file);
		}
		return isValid;
	}

	/**
	 * Checks for tag emulator.
	 *
	 * @param path the path
	 * @param file the file
	 * @return true, if successful
	 */
	private boolean hasTagEmulator(final String path, final File file) {
		boolean isValid = true;
		Document doc = this.documentFactory(path, file);
		if(doc == null){
			crearXMLFile();
			isValid = false;
		}else{
			final NodeList nodeEmu = doc.getElementsByTagName(TAG_EMULATOR);
			final NodeList nodeConfig = doc.getElementsByTagName(TAG_CONFIG);
			if ((nodeEmu == null) && (nodeConfig == null)) {
				doc.createElement(TAG_EMULATOR);
				this.transFormeXML(doc, path + file);
			}
		}
		return isValid;
	}

	/**
	 * Document factory.
	 *
	 * @param path the path
	 * @param file the file
	 * @return the document
	 */
	private Document documentFactory(final String path, final File file) {
		Document doc = null;
		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(path + file.getName());
		} catch (final ParserConfigurationException ex) {
			Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
		} catch (final SAXException ex) {
			Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
		} catch (final IOException ex) {
			Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
		}
		return doc;
	}

	/**
	 * Dir list files.
	 *
	 * @param extension the extension
	 * @param path the path
	 * @return the file[]
	 */
	private File[] dirListFiles(final String extension, final String path) {
		final FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(final File file) {
				return file.getName().endsWith(extension);
			}
		};
		final File dir = new File(path);
		return dir.listFiles(filter);
	}

	/**
	 * Creates the tag.
	 *
	 * @param doc the doc
	 * @param origemTag the origem tag
	 * @param novaTag the nova tag
	 * @param path the path
	 * @param value the value
	 */
	private void createTag(final Document doc, final String origemTag, final String novaTag, final String path,
			final String value) {
		final Element origem = (Element) doc.getElementsByTagName(origemTag).item(0);
		final Element novo = doc.createElement(novaTag);
		if (value != null) {
			novo.setTextContent(value);
		}
		origem.appendChild(novo);
	}

	/**
	 * Trans forme xml.
	 *
	 * @param doc the doc
	 * @param path the path
	 */
	private void transFormeXML(final Document doc, final String path) {
		final TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			final Transformer transformer = transformerFactory.newTransformer();
			final DOMSource source = new DOMSource(doc);
			final StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
		} catch (final TransformerConfigurationException ex) {
			Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
		} catch (final TransformerException ex) {
			Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


}
