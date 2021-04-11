package edu.sgu.seminar.service.impl;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import edu.sgu.seminar.service.QrCodeDecoderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
@Service
@Slf4j
public class QrCodeDecoderServiceImpl implements QrCodeDecoderService {
    @Override
    public String decodeQrCodeFile(File qrCodeFile) throws FormatException, ChecksumException, NotFoundException, IOException {
        log.info("start decoding file {}", qrCodeFile.getName());
        BufferedImage bufferedImage = ImageIO.read(qrCodeFile);
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
        Map<DecodeHintType, Object> hintMap = new EnumMap<>(DecodeHintType.class);
        hintMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(DecodeHintType.PURE_BARCODE, Void.class);
        Result result = new QRCodeReader().decode(binaryBitmap, hintMap);
        log.info("file {} successfully decoded", qrCodeFile.getName());
        return result.getText();
    }
}
