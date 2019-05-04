package com.score.service;

import org.springframework.web.multipart.MultipartFile;

public interface IPredictService {

    boolean batchImport(String fileName, MultipartFile file) throws Exception;

}
