package edu.sgu.seminar.service;

import edu.sgu.seminar.dto.QrCodeProcessingResult;

public interface QrCodeEncoderService {
 QrCodeProcessingResult generateImageAsBase64(String textToBeEncoded) ;
}
