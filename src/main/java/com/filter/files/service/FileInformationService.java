package com.filter.files.service;


import java.util.List;

import com.filter.files.model.FileInfo;

public interface FileInformationService {

    List<FileInfo> getFileInformationInDir();

    List<FileInfo> getFileInfoConfigExtInDir();

    List<String> readVehicleRegNo();

}
