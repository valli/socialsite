package com.socialsite.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * @author Ananth
 * 
 */
public class ImageService
{

	public static final int	THUMB_SIZE	= 100;
	public static final int	IMAGE_SIZE	= 150;

	/**
	 * rescale the images
	 * 
	 * @param image
	 *            imagedata in bytes
	 * @param size
	 *            size to be scaled
	 * @return resized imagedata in bytes
	 */
	public byte[] resize(final byte[] imageData, final int maxSize)
	{
		// Get the image from a file.
		final Image inImage = new ImageIcon(imageData).getImage();
		// Determine the scale.
		double scale = (double) maxSize / (double) inImage.getHeight(null);
		if (inImage.getWidth(null) > inImage.getHeight(null))
		{
			scale = (double) maxSize / (double) inImage.getWidth(null);
		}

		// Determine size of new image.
		// One of the dimensions should equal maxSize.
		final int scaledW = (int) (scale * inImage.getWidth(null));
		final int scaledH = (int) (scale * inImage.getHeight(null));

		// Create an image buffer in which to paint on.
		final BufferedImage outImage = new BufferedImage(scaledW, scaledH,
			BufferedImage.TYPE_INT_RGB);

		// Set the scale.
		final AffineTransform tx = new AffineTransform();

		// If the image is smaller than the desired image size,
		// don't bother scaling.
		if (scale < 1.0d)
		{
			tx.scale(scale, scale);
		}

		// Paint image.
		final Graphics2D g2d = outImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(inImage, tx, null);
		g2d.dispose();

		// JPEG-encode the image
		// and write to file.
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		final JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		try
		{
			encoder.encode(outImage);
			os.close();
		} catch (final ImageFormatException e)
		{
			Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE,
				null, e);
		} catch (final IOException e)
		{
			Logger.getLogger(ImageService.class.getName()).log(Level.SEVERE,
				null, e);
		}

		return os.toByteArray();
	}
}
