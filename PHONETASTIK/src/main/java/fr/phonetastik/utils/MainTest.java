package fr.phonetastik.utils;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;

public class MainTest {
	

    public static void main( String[] args ) {

        String chemin = "static/images/iphone13.jpg";
        File file = new File( chemin );
        String mimeType = new MimetypesFileTypeMap().getContentType( file );
        // mimeType should now be something like "image/png"

        String type=mimeType.substring(0,5);
             System.out.println(type);
        
    }

}
