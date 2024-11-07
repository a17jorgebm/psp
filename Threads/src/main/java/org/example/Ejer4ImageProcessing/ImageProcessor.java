package org.example.Ejer4ImageProcessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageProcessor implements Runnable{
    private static final Path IMAGE_SAVING_DIRECTORY=Path.of("src/main/java/org/example/Ejer4ImageProcessing/resources/processed");
    private static final String FILE_NAME_ENDING="_grey";
    private Path ficheiro;

    public ImageProcessor(Path ficheiro) {
        this.ficheiro = ficheiro;
    }

    @Override
    public void run() {
        String imageFormat=ficheiro.getFileName().toString().substring(ficheiro.getFileName().toString().lastIndexOf("."));
        String processedFileName=generateNewFileName(ficheiro.getFileName().toString(),imageFormat);

        BufferedImage img = null;
        File inputFile = ficheiro.toFile();
        File outputFile= IMAGE_SAVING_DIRECTORY.resolve(processedFileName).toFile();

        // read image
        try {
            img = ImageIO.read(inputFile);
        } catch (IOException e) {
            System.out.println(e);
        }

        // get image's width and height
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);
        // convert to grayscale
        for (int i = 0; i < pixels.length; i++) {

            // Here i denotes the index of array of pixels
            // for modifying the pixel value.
            int p = pixels[i];

            int a = (p >> 24) & 0xff;
            int r = (p >> 16) & 0xff;
            int g = (p >> 8) & 0xff;
            int b = p & 0xff;

            // calculate average
            int avg = (r + g + b) / 3;

            // replace RGB value with avg
            p = (a << 24) | (avg << 16) | (avg << 8) | avg;

            pixels[i] = p;
        }
        img.setRGB(0, 0, width, height, pixels, 0, width);
        // write image
        try {
            //non me iba porque hai que indicar a extension sin o punto
            ImageIO.write(img, imageFormat.substring(1), outputFile);
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(imageFormat);
        System.out.println(outputFile.getAbsolutePath());
    }

    private String generateNewFileName(String fileName, String imageFormat){
        int indexOfLastPoint=fileName.lastIndexOf(".");
        if (indexOfLastPoint==-1){
            return fileName + FILE_NAME_ENDING + "." + imageFormat;
        }
        return fileName.substring(0,indexOfLastPoint) + FILE_NAME_ENDING + imageFormat;
    }
}
