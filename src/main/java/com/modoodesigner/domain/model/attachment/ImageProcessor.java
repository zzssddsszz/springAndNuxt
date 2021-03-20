package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.utils.Size;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.im4java.core.*;
import org.im4java.process.ArrayListOutputConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ImageProcessor {

    //https://www.fatalerrors.org/a/using-grapicmagick-and-im4java-to-deal-with-image.html

    // image quality
    public static final String IMAGE_QUALITY = "quality";
    // imagee height
    public static final String IMAGE_HEIGHT = "height";
    // image width
    public static final String IMAGE_WIDTH = "width";
    // image format
    public static final String IMAGE_SUFFIX = "suffix";
    // image size
    public static final String IMAGE_SIZE = "size";
    // image path
    public static final String IMAGE_PATH = "path";

    /**
     * Whether use GraphicsMagick
     */
    private static final boolean IS_USE_GRAPHICS_MAGICK = true;

    /**
     * ImageMagick Installation path, windows Use under
     */
    private static final String IMAGE_MAGICK_PATH = "C:\\Program Files\\GraphicsMagick-1.3.34-Q16";

    /**
     *  gm Command path
     */
    private static final String GRAPHICS_MAGICK_PATH = "/usr/local/bin";

    private enum CommandType {
        convert("Conversion processing"), identify("Image information"), compositecmd("Image composition");
        private String name;

        CommandType(String name) {
            this.name = name;
        }
    }

    /**
     * Query the basic image information: format,Quality, width, height
     * <p>
     * gm identify -format %w,%h,%d/%f,%Q,%b,%e /Users/gary/Documents/999999999/10005582/1.jpg
     * <p>
     *
     * @param imagePath
     * @return
     */
    public static Map<String, String> getImageInfo(String imagePath) {
        Map<String, String> imageInfo = new HashMap<>();
        try {
            IMOperation op = new IMOperation();
            op.format("%w,%h,%d/%f,%Q,%b,%e");
            op.addImage();
            ImageCommand identifyCmd = getImageCommand(CommandType.identify);
            ArrayListOutputConsumer output = new ArrayListOutputConsumer();
            identifyCmd.setOutputConsumer(output);
            identifyCmd.run(op, imagePath);
            ArrayList<String> cmdOutput = output.getOutput();
            String[] result = cmdOutput.get(0).split(",");
            if (result.length == 6) {
                imageInfo.put(IMAGE_WIDTH, result[0]);
                imageInfo.put(IMAGE_HEIGHT, result[1]);
                imageInfo.put(IMAGE_PATH, result[2]);
                imageInfo.put(IMAGE_QUALITY, result[3]);
                imageInfo.put(IMAGE_SIZE, result[4]);
                imageInfo.put(IMAGE_SUFFIX, result[5]);
            }
        } catch (Exception e) {
            log.error("Image tool to get basic image information exception" + e.getMessage(), e);
        }
        return imageInfo;
    }

    /**
     * Get ImageCommand
     *
     * @param command Command type
     * @return
     */
    private static ImageCommand getImageCommand(CommandType command) {
        ImageCommand cmd = null;
        switch (command) {
            case convert:
                cmd = new ConvertCmd(IS_USE_GRAPHICS_MAGICK);
                break;
            case identify:
                cmd = new IdentifyCmd(IS_USE_GRAPHICS_MAGICK);
                break;
            case compositecmd:
                cmd = new CompositeCmd(IS_USE_GRAPHICS_MAGICK);
                break;
        }
        if (cmd != null && System.getProperty("os.name").toLowerCase().indexOf("windows") == -1) {
            cmd.setSearchPath(IS_USE_GRAPHICS_MAGICK ? GRAPHICS_MAGICK_PATH : IMAGE_MAGICK_PATH);
        }
        return cmd;
    }




    public void resize(String sourceFilePath, String targetFilePath, Size resizeTo) {
        Assert.isTrue(resizeTo.getHeight() > 0, "이미지 높이가 0보다 커야합니다.");
        Assert.isTrue(resizeTo.getWidth() > 0, "이미지 너비가 0보다 커야합니다.");

        try {
            IMOperation op = new IMOperation();
            op.addImage(sourceFilePath);
            op.quality(70d);
            op.resize(resizeTo.getWidth(), resizeTo.getHeight());
            op.addImage(targetFilePath);
            ConvertCmd convert = new ConvertCmd(true);
            convert.run(op);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Size getSize(String imageFilePath)  {
        Map<String, String> info = getImageInfo(imageFilePath);
        return new Size(NumberUtils.toInt(info.get(IMAGE_WIDTH)), NumberUtils.toInt(info.get(IMAGE_HEIGHT)));
    }
}
