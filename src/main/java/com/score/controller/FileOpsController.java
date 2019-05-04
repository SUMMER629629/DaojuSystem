package com.score.controller;

import com.score.bean.FilePath;
import com.score.bean.FileSavedResult;
import com.score.bean.Relife;
import com.score.dao.FilePathMapper;
import com.score.dao.RelifeMapper;
import com.score.util.Encrypt;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import java.lang.RuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author valor
 * @date 2018/11/21 14:34
 */
@RestController
@Service
public class FileOpsController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FilePathMapper filepathmapper;

    @Autowired
    private RelifeMapper relifemapper;





    @Value("${config.file-save}")
    private String fileSavePath = "files";

    /**
     * 上传图片
     */
    @RequestMapping(value = "/ups", method = RequestMethod.POST)
    public FileSavedResult upload() {
        // 判断content-type 是否为 form-data
        if (!StringUtils.contains(this.request.getContentType(), "form-data")) {
            throw new RuntimeException("Please use 'form-data' to pass files.");
        }

        // 使用MultipartResolver 转换请求为 MultipartHttpServletRequest
        MultipartResolver resolver = new StandardServletMultipartResolver();
        MultipartHttpServletRequest mRequest = resolver.resolveMultipart(this.request);
        // 获取上传文件的map 可以对多文件上传进行处理
        Map<String, MultipartFile> mapFiles = mRequest.getFileMap();

        if (null == mapFiles || 0 == mapFiles.size()) {
            throw new RuntimeException("No file(s) existed.");
        }

        // 记录文件保存结果
        int capacity = mapFiles.size() * 4 / 3 + 1;
        Map<String, String> saved = new HashMap<String, String>(capacity);
        Map<String, String> failed = new HashMap<String, String>(8);

        // 保存的文件相关参数
        String fileSavedName;
        MultipartFile file;
        // 对上传对文件循环保存
        for (Map.Entry<String, MultipartFile> entry : mapFiles.entrySet()) {
            file = entry.getValue();

            // file rule
            if (null == file.getOriginalFilename() || 60 < file.getOriginalFilename().length()) {
                failed.put(entry.getKey(), "File name is null or more than 60 characters.");
                continue;
            }

            if (StringUtils.contains(file.getOriginalFilename(), "%00")) {
                failed.put(entry.getKey(), "Block this file. Unsupported file name.");
                continue;
            }

            // 直接保存文件
            try {
                // todo 判断图片文件是否带有js

                // 如果没有抛出异常 则保存成功
                long time = System.currentTimeMillis();
                fileSavedName = saveTo(time, file.getInputStream(), file.getOriginalFilename(), this.fileSavePath);
                saved.put(entry.getKey(), fileSavedName);

                // todo 你记录一下文件路径
                FilePath filePath = new FilePath();

            } catch (Exception e) {
                // 记录保存失败的文件
                failed.put(entry.getKey(), e.getMessage());
            }
        }

        FileSavedResult result = new FileSavedResult();
        result.setSaved(saved);
        result.setFailed(failed);
        return result;
    }



    /**
     * 保存图片
     * @return 保存后的文件名
     */
    private String saveTo(long time, InputStream stream,String fileName, String dir) {
        if (stream == null) {
            throw new RuntimeException("No inputSteam in file.");
        }

        if (!dir.endsWith(File.separator)) {
            dir += File.separator;
        }

        // 获取文件后缀名
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (StringUtils.isBlank(ext)) {
            throw new RuntimeException("无法获取文件后缀名.");
        }

        // 重命名文件 todo 看你需要
//        String saveName = time + "-" + Encrypt.md5HexString(time + fileName) + "." + ext;
        File savedFile = new File(dir + fileName);
        try {
//            FileOutputStream fos = FileUtils.openOutputStream(new File(dir + saveName));
//            File savedFile = new File(dir + saveName);
            FileOutputStream fos = FileUtils.openOutputStream(savedFile);
            IOUtils.copy(stream, fos);

            stream.close();
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        FilePath filesystem = new FilePath();


        filesystem.setFileNo(Integer.parseInt(fileName.substring(0,1)));
        filesystem.setFileName(fileName);
        filesystem.setFilePath(dir);
        Integer total = filepathmapper.saveFile(filesystem);
//        Integer total1=daojuMapper.updateByPrimaryKey(daoju);

        Relife relife = new Relife();
        relife.setFlag(Integer.parseInt(fileName.substring(2,3)));
        relife.setRedaojuNo(Integer.parseInt(fileName.substring(0,1)));
        Integer total1 = relifemapper.updateFlag(relife);

        return savedFile.getAbsolutePath();
    }

}
