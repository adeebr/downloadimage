import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;

public class Image_downloader {
	public static void main (String[] args) throws IOException {
		URL url;
		InputStream is = null;
		DataInputStream dis;
		String line;
		String websitehtml = "";
		
		System.out.print("Enter website url (with http://): ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String urlString = br.readLine();
		
		url = new URL(urlString);
		is = url.openStream();  
		dis = new DataInputStream(new BufferedInputStream(is));
		
		while ((line = dis.readLine()) != null) {
			websitehtml += line;
		}

		int ind = websitehtml.indexOf("id='header-img'");

		String imagetrim = "";
		String imagelink = "";
		
		char c = 'a';
		while (c != '>') 
		{
			c = websitehtml.charAt(ind);
			imagetrim += c;
			ind++;
		}
		
		imagetrim = imagetrim.substring(imagetrim.indexOf("src"));
		imagetrim = imagetrim.substring(5);
		
		for (int x = 0; x < imagetrim.length(); x++) 
		{
			if (imagetrim.charAt(x) == '\"') {
				break;
			}
			imagelink += imagetrim.charAt(x);
		}
		
		  URL imageurl = new URL(imagelink);
		  Image image = ImageIO.read(imageurl);

		  BufferedImage bi = (BufferedImage) image;
		  File outputfile = new File("c://Header_image.jpg");
		  ImageIO.write(bi, "jpg", outputfile);

	}
}
