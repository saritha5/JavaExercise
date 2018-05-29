package com.filter.files.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.filter.files.model.FileInfo;
import com.filter.files.util.PropertyReader;

@Service("fileInfoService")
public class FileInformationServiceImpl implements FileInformationService {
    private static final Logger logger = LoggerFactory.getLogger(FileInformationServiceImpl.class);

    private static final String VEHICLE_REGISTRATION_NO = "Vehicle Registration No";
    Properties properties = PropertyReader.fetchProperties();
    String dirLocation = properties.getProperty("dir.location");
    File root = null;
    boolean recursive = false;

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public List<FileInfo> getFileInformationInDir() {
        return getFilteredFileInfoList(null, false);
    }

    // List All files in dir
    @Override
    public List<FileInfo> getFileInfoConfigExtInDir() {
        root = new File(dirLocation);
        String[] extensions = properties.getProperty("file.extensions").split(",");
        return getFilteredFileInfoList(extensions, false);
    }

    // Filter files based on config
    private List<FileInfo> getFilteredFileInfoList(String[] extensions, boolean recursive) {
        root = new File(dirLocation);
        Collection<File> files = FileUtils.listFiles(root, extensions, recursive);
        logger.info("List of files from config dir", files);
        List<FileInfo> fileInfoList = new ArrayList<>();

        for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
            File file = iterator.next();

            if (file.isFile()) {
                FileInfo fileInfo = new FileInfo();

                fileInfo.setFileName(file.getName());
                fileInfo.setFileExtension(FilenameUtils.getExtension(file.getName()));
                fileInfo.setFileMimeType(URLConnection.guessContentTypeFromName(file.getName()));
                fileInfo.setFileSize(file.length());

                fileInfoList.add(fileInfo);
            }
        }

        return fileInfoList;
    }

    // Reading all regstation numbers
    @Override
    public List<String> readVehicleRegNo() {
        List<String> vehicleRegNoList = new ArrayList<String>();
        // String dirLocation =
        // properties.getProperty("file.vehicle.registration.details");
        Resource resource = resourceLoader.getResource("classpath:VehicleRegFile.xls");
        try {
            InputStream ExcelFileToRead = new FileInputStream(resource.getFile());
            XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

            XSSFWorkbook test = new XSSFWorkbook();

            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;

            Iterator rows = sheet.rowIterator();

            while (rows.hasNext()) {
                row = (XSSFRow) rows.next();
                StringBuilder strBuilder = new StringBuilder();
                Iterator cells = row.cellIterator();
                while (cells.hasNext()) {
                    cell = (XSSFCell) cells.next();
                    if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                        if (!cell.getStringCellValue().equalsIgnoreCase(VEHICLE_REGISTRATION_NO)
                                && !cell.getStringCellValue().equalsIgnoreCase("Make")
                                && !cell.getStringCellValue().equalsIgnoreCase("Colour")) {

                            strBuilder.append(cell.getStringCellValue()).append("-");

                            logger.info("VEHICLE REGISTRATION DETAILS : " + strBuilder);
                        }

                    }

                }
                String str = strBuilder.toString();
                if (!StringUtils.isEmpty(str)) {
                    vehicleRegNoList.add(str.substring(0, str.length() - 1));
                }
            }
        } catch (Exception ex) {
            logger.error("Error while reading from xsl file", ex.getMessage());
        }

        return vehicleRegNoList;

    }

}
