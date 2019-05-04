package com.score.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.score.bean.Plc;
//import com.score.bean.Relife;
import com.score.dao.PredictMapper;
//import com.score.dao.RelifeMapper;
import com.score.service.IPredictService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("Plc")
@Transactional(readOnly = true)
public class PredictServiceImpl implements IPredictService {

    @Autowired
    private PredictMapper predictMapper;

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {

        boolean notNull = false;
        List<Plc> plcList = new ArrayList<Plc>();

        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        Plc plc;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }

            plc = new Plc();
            if(row.getCell(0)!=null){
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                plc.setPlcId(Integer.parseInt(row.getCell(0).getStringCellValue()));
            }

            if(row.getCell(1)!=null){
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                plc.setPlcdjNo(Integer.parseInt(row.getCell(1).getStringCellValue()));
            }

            Date plctime = row.getCell(2).getDateCellValue();
            plc.setPlcTime(plctime);

            if(row.getCell(3)!=null){
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                plc.setSpindleLoad(Float.parseFloat(row.getCell(3).getStringCellValue()));
            }

            if(row.getCell(4)!=null){
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                plc.setPlcx(Float.parseFloat(row.getCell(4).getStringCellValue()));
            }
            if(row.getCell(5)!=null){
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                plc.setPlcy(Float.parseFloat(row.getCell(5).getStringCellValue()));
            }
            if(row.getCell(6)!=null){
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                plc.setPlcz(Float.parseFloat(row.getCell(6).getStringCellValue()));
            }


            plcList.add(plc);
        }
        for (Plc plcResord : plcList) {
            Integer id = plcResord.getPlcId();
            int cnt = predictMapper.selectById(id);
            if (cnt == 0) {
                predictMapper.addUser(plcResord);
                System.out.println(" 插入 "+plcResord);
            } else {
                predictMapper.updateUserById(plcResord);
                System.out.println(" 更新 "+plcResord);
            }
        }
        return notNull;
    }


}