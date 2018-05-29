package com.filter.files.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.filter.files.model.FileInfo;
import com.filter.files.service.FileInformationService;

@RestController
@RequestMapping("/")
public class RestfulController {
	private static final Logger logger = LoggerFactory.getLogger(RestfulController.class);

	@Autowired
	FileInformationService fileInformationService;

	@RequestMapping(value = "/getFilesInfo/", method = RequestMethod.GET)
	public ResponseEntity<List<FileInfo>> getFilesInfo() {
		logger.info("Received getFilesInfo request");
		List<FileInfo> filesInfo = fileInformationService.getFileInformationInDir();
		if (filesInfo.isEmpty()) {
			return new ResponseEntity<List<FileInfo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FileInfo>>(filesInfo, HttpStatus.OK);
	}

	@RequestMapping(value = "/getConfigExtFilesInfo/", method = RequestMethod.GET)
	public ResponseEntity<List<FileInfo>> getConfigExtFilesInfo() {
		logger.info("Received getConfigExtFilesInfo request");
		List<FileInfo> filesInfo = fileInformationService.getFileInfoConfigExtInDir();
		if (filesInfo.isEmpty()) {
			return new ResponseEntity<List<FileInfo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FileInfo>>(filesInfo, HttpStatus.OK);
	}

	@RequestMapping(value = "/getVehicleRegistrationNo/", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getVehicleRegNumbers() {
		logger.info("Received getConfigExtFilesInfo request");
		List<String> regInfo = fileInformationService.readVehicleRegNo();
		if (regInfo.isEmpty()) {
			return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<String>>(regInfo, HttpStatus.OK);
	}

}