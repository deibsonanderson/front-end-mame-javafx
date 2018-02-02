/*
 * 
 */
package br.com.emulador;

/**
 * The Class Config.
 */
public class Config {

	/** The emu path. */
	private String emuPath;

	/** The emu exec. */
	private String emuExec;

	/** The image path. */
	private String imagePath;

	/** The width. */
	private Integer width;

	/** The height. */
	private Integer height;

	/** The title. */
	private String title;

	/** The image ext. */
	private String imageExt;

	/** The video ext. */
	private String videoExt;

	/** The video path. */
	private String videoPath;

	/** The logo path. */
	private String logoPath;

	/** The video mute. */
	private String videoMute;

	/** The width video. */
	private Integer widthVideo;

	/** The height video. */
	private Integer heightVideo;

	/** The eixo video y. */
	private Integer eixoVideoY;

	/** The eixo video x. */
	private Integer eixoVideoX;

	/** The maximized. */
	private String maximized;

	/** The width logo. */
	private Double widthLogo;

	/** The height logo. */
	private Double heightLogo;

	/** The eixo logo y. */
	private Integer eixoLogoY;

	/** The eixo logo x. */
	private Integer eixoLogoX;
	
	/** The window bar. */
	private String windowBar;

	/** The on top. */
	private String onTop;
	
	/** The width logo. */
	private Double widthImage;

	/** The height logo. */
	private Double heightImage;

	/** The eixo logo y. */
	private Integer eixoImageY;

	/** The eixo logo x. */
	private Integer eixoImageX;
	
	
	/**
	 * Gets the window bar.
	 *
	 * @return the window bar
	 */
	public String getWindowBar() {
		return windowBar;
	}

	/**
	 * Sets the window bar.
	 *
	 * @param windowbar the new window bar
	 */
	public void setWindowBar(String windowbar) {
		this.windowBar = windowbar;
	}

	/**
	 * Gets the width logo.
	 *
	 * @return the width logo
	 */
	public Double getWidthLogo() {
		return widthLogo;
	}

	/**
	 * Sets the width logo.
	 *
	 * @param widthLogo the new width logo
	 */
	public void setWidthLogo(Double widthLogo) {
		this.widthLogo = widthLogo;
	}

	/**
	 * Gets the height logo.
	 *
	 * @return the height logo
	 */
	public Double getHeightLogo() {
		return heightLogo;
	}

	/**
	 * Sets the height logo.
	 *
	 * @param heightLogo the new height logo
	 */
	public void setHeightLogo(Double heightLogo) {
		this.heightLogo = heightLogo;
	}

	/**
	 * Gets the eixo logo y.
	 *
	 * @return the eixo logo y
	 */
	public Integer getEixoLogoY() {
		return eixoLogoY;
	}

	/**
	 * Sets the eixo logo y.
	 *
	 * @param eixoLogoY the new eixo logo y
	 */
	public void setEixoLogoY(Integer eixoLogoY) {
		this.eixoLogoY = eixoLogoY;
	}

	/**
	 * Gets the eixo logo x.
	 *
	 * @return the eixo logo x
	 */
	public Integer getEixoLogoX() {
		return eixoLogoX;
	}

	/**
	 * Sets the eixo logo x.
	 *
	 * @param eixoLogoX the new eixo logo x
	 */
	public void setEixoLogoX(Integer eixoLogoX) {
		this.eixoLogoX = eixoLogoX;
	}

	/**
	 * Gets the width video.
	 *
	 * @return the width video
	 */
	public Integer getWidthVideo() {
		return widthVideo;
	}

	/**
	 * Sets the width video.
	 *
	 * @param widthVideo the new width video
	 */
	public void setWidthVideo(Integer widthVideo) {
		this.widthVideo = widthVideo;
	}

	/**
	 * Gets the height video.
	 *
	 * @return the height video
	 */
	public Integer getHeightVideo() {
		return heightVideo;
	}

	/**
	 * Sets the height video.
	 *
	 * @param heightVideo the new height video
	 */
	public void setHeightVideo(Integer heightVideo) {
		this.heightVideo = heightVideo;
	}

	/**
	 * Gets the eixo video y.
	 *
	 * @return the eixo video y
	 */
	public Integer getEixoVideoY() {
		return eixoVideoY;
	}

	/**
	 * Sets the eixo video y.
	 *
	 * @param eixoVideoY the new eixo video y
	 */
	public void setEixoVideoY(Integer eixoVideoY) {
		this.eixoVideoY = eixoVideoY;
	}

	/**
	 * Gets the eixo video x.
	 *
	 * @return the eixo video x
	 */
	public Integer getEixoVideoX() {
		return eixoVideoX;
	}

	/**
	 * Sets the eixo video x.
	 *
	 * @param eixoVideoX the new eixo video x
	 */
	public void setEixoVideoX(Integer eixoVideoX) {
		this.eixoVideoX = eixoVideoX;
	}

	/**
	 * Gets the maximized.
	 *
	 * @return the maximized
	 */
	public String getMaximized() {
		return maximized;
	}

	/**
	 * Sets the maximized.
	 *
	 * @param maximized the new maximized
	 */
	public void setMaximized(String maximized) {
		this.maximized = maximized;
	}

	/**
	 * Gets the video mute.
	 *
	 * @return the video mute
	 */
	public String getVideoMute() {
		return videoMute;
	}

	/**
	 * Sets the video mute.
	 *
	 * @param videoMute the new video mute
	 */
	public void setVideoMute(String videoMute) {
		this.videoMute = videoMute;
	}

	/**
	 * Gets the logo path.
	 *
	 * @return the logo path
	 */
	public String getLogoPath() {
		return logoPath;
	}

	/**
	 * Sets the logo path.
	 *
	 * @param logoPath the new logo path
	 */
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	/** The image defaut. */
	private String imageDefaut;

	/**
	 * Gets the emu path.
	 *
	 * @return the emu path
	 */
	public String getEmuPath() {
		return this.emuPath;
	}

	/**
	 * Sets the emu path.
	 *
	 * @param emuPath
	 *            the new emu path
	 */
	public void setEmuPath(final String emuPath) {
		this.emuPath = emuPath;
	}

	/**
	 * Gets the emu exec.
	 *
	 * @return the emu exec
	 */
	public String getEmuExec() {
		return this.emuExec;
	}

	/**
	 * Sets the emu exec.
	 *
	 * @param emuExec
	 *            the new emu exec
	 */
	public void setEmuExec(final String emuExec) {
		this.emuExec = emuExec;
	}

	/**
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	public String getImagePath() {
		return this.imagePath;
	}

	/**
	 * Sets the image path.
	 *
	 * @param imagePath
	 *            the new image path
	 */
	public void setImagePath(final String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public Integer getWidth() {
		return this.width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width
	 *            the new width
	 */
	public void setWidth(final Integer width) {
		this.width = width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public Integer getHeight() {
		return this.height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height
	 *            the new height
	 */
	public void setHeight(final Integer height) {
		this.height = height;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Gets the image ext.
	 *
	 * @return the image ext
	 */
	public String getImageExt() {
		return this.imageExt;
	}

	/**
	 * Sets the image ext.
	 *
	 * @param imageExt
	 *            the new image ext
	 */
	public void setImageExt(final String imageExt) {
		this.imageExt = imageExt;
	}

	/**
	 * Gets the image defaut.
	 *
	 * @return the image defaut
	 */
	public String getImageDefaut() {
		return this.imageDefaut;
	}

	/**
	 * Sets the image defaut.
	 *
	 * @param imageDefaut
	 *            the new image defaut
	 */
	public void setImageDefaut(final String imageDefaut) {
		this.imageDefaut = imageDefaut;
	}

	/**
	 * Gets the video ext.
	 *
	 * @return the video ext
	 */
	public String getVideoExt() {
		return videoExt;
	}

	/**
	 * Sets the video ext.
	 *
	 * @param videoExt the new video ext
	 */
	public void setVideoExt(String videoExt) {
		this.videoExt = videoExt;
	}

	/**
	 * Gets the video path.
	 *
	 * @return the video path
	 */
	public String getVideoPath() {
		return videoPath;
	}

	/**
	 * Sets the video path.
	 *
	 * @param videoPath the new video path
	 */
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	/**
	 * Gets the width image.
	 *
	 * @return the width image
	 */
	public Double getWidthImage() {
		return widthImage;
	}

	/**
	 * Sets the width image.
	 *
	 * @param widthImage the new width image
	 */
	public void setWidthImage(Double widthImage) {
		this.widthImage = widthImage;
	}

	/**
	 * Gets the height image.
	 *
	 * @return the height image
	 */
	public Double getHeightImage() {
		return heightImage;
	}

	/**
	 * Sets the height image.
	 *
	 * @param heightImage the new height image
	 */
	public void setHeightImage(Double heightImage) {
		this.heightImage = heightImage;
	}

	/**
	 * Gets the eixo image Y.
	 *
	 * @return the eixo image Y
	 */
	public Integer getEixoImageY() {
		return eixoImageY;
	}

	/**
	 * Sets the eixo image Y.
	 *
	 * @param eixoImageY the new eixo image Y
	 */
	public void setEixoImageY(Integer eixoImageY) {
		this.eixoImageY = eixoImageY;
	}

	/**
	 * Gets the eixo image X.
	 *
	 * @return the eixo image X
	 */
	public Integer getEixoImageX() {
		return eixoImageX;
	}

	/**
	 * Sets the eixo image X.
	 *
	 * @param eixoImageX the new eixo image X
	 */
	public void setEixoImageX(Integer eixoImageX) {
		this.eixoImageX = eixoImageX;
	}

	/**
	 * Gets the on top.
	 *
	 * @return the on top
	 */
	public String getOnTop() {
		return onTop;
	}

	/**
	 * Sets the on top.
	 *
	 * @param onTop the new on top
	 */
	public void setOnTop(String onTop) {
		this.onTop = onTop;
	}
	
	

}
