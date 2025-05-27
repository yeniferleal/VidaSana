package com.vidaSana.util;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import javax.servlet.http.Part;
import lombok.Data;

@Data
public class Imagen {

    private String pathFiles;
    private File uploads;
    private String[] extens = {".png", ".jpg", ".jpeg"};

    //-----------------------------------------------  
    //Foto
    private File file;
    private InputStream input;

    public Imagen() {
        pathFiles = "C:\\fotos\\";
        uploads = new File(pathFiles);
    }

    public String getRutaFoto(Part part, File pathUploads, String fileName) throws Exception {
        String pathAbsolute = "";

        Path path = Paths.get(part.getSubmittedFileName());
        fileName += getExtension(path.getFileName().toString());
        this.input = part.getInputStream();

        if (input != null) {
            this.file = new File(pathUploads, fileName);
            pathAbsolute = file.getAbsolutePath();
            if (!existeImagen(pathAbsolute)) {
                return modificarPathAbsolute(pathAbsolute);
            } else {
                this.input = null;
                this.file = null;
                throw new Exception("La imagen ya existe.");
            }
        }

        return null;
    }

    public void saveFile() throws Exception {
        File file = new File("C:\\fotos");
        if(!file.exists()){
            file.mkdir();
        }
        if(this.file!=null && this.input!=null)
            Files.copy(this.input, this.file.toPath());
        else
            throw new Exception("Error al agregar imagen.");
    }
    public String saveFile(Part part, File pathUploads,String fileName) throws Exception {
        String pathAbsolute = "";

        Path path = Paths.get(part.getSubmittedFileName());
        fileName += getExtension(path.getFileName().toString());
        //fileName = path.getFileName().toString();
        InputStream input = part.getInputStream();

        if (input != null) {
            File file = new File(pathUploads, fileName);
            pathAbsolute = file.getAbsolutePath();
            if(!existeImagen(pathAbsolute))
                Files.copy(input, file.toPath());
            else
                throw new Exception("La imagen ya existe.");
        }

        return modificarPathAbsolute(pathAbsolute);
    }

    public boolean existeImagen(String path) {
        File archivo = new File(path);
        if (archivo.exists()) {
            if (archivo.isFile()) {
                return true;
            }
        }
        return false;
    }

    public boolean isExtension(String fileName, String[] extensions) {
        for (String et : extensions) {
            if (fileName.toLowerCase().endsWith(et)) {
                return true;
            }
        }

        return false;
    }

    private String modificarPathAbsolute(String path) {
        String[] ruta = path.split(Pattern.quote("\\"));
        String pathAbsolute = "file:///";
        for (byte i = 0; i < ruta.length; i++) {
            pathAbsolute += ruta[i];
            if ((i + 1) != ruta.length) {
                pathAbsolute += "/";
            }
        }
        return pathAbsolute;
    }

    public String getExtension(String fileName) {
        for (String et : this.extens) {
            if (fileName.toLowerCase().endsWith(et)) {
                return et;
            }
        }

        return null;
    }

}
