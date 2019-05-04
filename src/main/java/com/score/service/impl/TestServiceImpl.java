package com.score.service.impl;

import com.score.bean.User1;
import com.score.dao.User1Mapper;
import com.score.service.ITestService;
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

@Service("User1")
@Transactional(readOnly = true)
public class TestServiceImpl implements ITestService {

    @Autowired
    private User1Mapper user1Mapper;


    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {

        boolean notNull = false;
        List<User1> userList = new ArrayList<User1>();

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
        User1 user1;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }

            user1 = new User1();
            String name = row.getCell(0).getStringCellValue();
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            String phone = row.getCell(1).getStringCellValue();
            String add = row.getCell(2).getStringCellValue();
            Date date;
            date = row.getCell(3).getDateCellValue();
            String des = row.getCell(4).getStringCellValue();

            user1.setName(name);
            user1.setPhone(phone);
            user1.setAddress(add);
            user1.setEnrolDate(date);
            user1.setDes(des);

            userList.add(user1);
        }
        for (User1 userResord : userList) {
            String name = userResord.getName();
            int cnt = user1Mapper.selectByName(name);
            if (cnt == 0) {
                user1Mapper.addUser(userResord);
                System.out.println(" 插入 "+userResord);
            } else {
                user1Mapper.updateUserByName(userResord);
                System.out.println(" 更新 "+userResord);
            }
        }
        return notNull;
    }
}