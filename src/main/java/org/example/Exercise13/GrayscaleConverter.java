package org.example.Exercise13;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

// Grayscale conversion task for a single row of pixels
class GrayscaleImageAction extends RecursiveAction {
    private final int row;
    private final BufferedImage image;

    public GrayscaleImageAction(int row, BufferedImage image) {
        this.row = row;
        this.image = image;
    }

    @Override
    protected void compute() {
        for (int col = 0; col < image.getWidth(); col++) {
            int rgb = image.getRGB(col, row);

            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;

            // Compute luminance using perceptual weights
            int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);

            // Pack into ARGB format (Alpha=255, R=G=B=gray)
            int grayRGB = (0xFF << 24) | (gray << 16) | (gray << 8) | gray;

            image.setRGB(col, row, grayRGB);
        }
    }
}

public class GrayscaleConverter {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 0) {
            System.err.println("Usage: java GrayscaleImageConverter <image-file>");
            return;
        }

        File inputFile = new File(args[0]);
        if (!inputFile.exists()) {
            System.err.println("Error: File not found.");
            return;
        }

        // Read the original color image
        BufferedImage img = ImageIO.read(inputFile);
        ForkJoinPool pool = new ForkJoinPool();

        // Submit a GrayscaleImageAction for each row of the image
        for (int row = 0; row < img.getHeight(); row++) {
            pool.execute(new GrayscaleImageAction(row, img));
        }

        // Shutdown the pool and wait for all tasks to finish
        pool.shutdown();
        while (!pool.isTerminated()) {
            Thread.sleep(5);
        }

        // Write the output grayscale image
        File output = new File("org/example/Exercise13/grayscale_output.png");
        ImageIO.write(img, "png", output);
        System.out.println("Grayscale image written to grayscale_output.png");

        // Open the output image using the default system viewer
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(output);
        }
    }
}




