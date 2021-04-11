package edu.sgu.seminar.service;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;

import java.io.File;
import java.io.IOException;

public interface QrCodeDecoderService {
    String decodeQrCodeFile(File qrCodeFile) throws FormatException, ChecksumException, NotFoundException, IOException;
}
