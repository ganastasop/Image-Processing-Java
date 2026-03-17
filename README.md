# Image Processing Toolkit (CLI)
A robust Java-based utility for manipulating and processing images. The application supports standard PPM files and a custom YUV format, featuring a variety of image processing algorithms.

## Features
The toolkit provides the following operations through an interactive terminal menu:
* **Resizing**: Increase or decrease image dimensions (Double size / Half size).
* **Rotation**: Clockwise rotation of the image.
* **Grayscale Conversion**: Transform color images to grayscale.
* **Histogram Equalization**: Automatic contrast enhancement using pixel frequency distribution.
* **Image Stacking**: An algorithm to combine multiple images from a directory and reduce noise.

## Supported Formats
1. **PPM (Portable Pixmap)**: Standard format for color images.
2. **Custom YUV**: A specialized format for YUV color space representation, handling raw luminance and chrominance data.

## Technical Highlights
* **Binary File Parsing**: Custom-built parsers for reading and writing raw byte data from specialized file formats.
* **Modular Architecture**: Separation of game logic (Processing Engine) and user interface (CLI).

## How to run
1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Compile the source files: ```javac *.java```
4. Run the application: ```java ImageProcessing```

### Usage
Once launched, the application will display a text-based menu. Simply follow the on-screen prompts to:
1. Import an image file.
2. Select the desired processing operation.
3. Export the output to a new file.
