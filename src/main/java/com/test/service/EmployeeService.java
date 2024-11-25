package com.test.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.test.conttoller.EmployeeController;
import com.test.conttoller.RequestEmployee;
import com.test.entity.Employee;
import com.test.entity.repo.EmployeeRepo;

@Service
@Transactional
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public Iterable<Employee> findAll() {
		return employeeRepo.findAll();
	}
	
	public Employee findById(Integer id) {
		return employeeRepo.findById(id).get();
	}
	
	public Iterable<Employee> findAllByPositionCode(String positionCode) {
		return employeeRepo.findAllByPositionCode(positionCode);
	}
	
	public Page<Employee> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);        

        return employeeRepo.findAll(pageable);
	}
	
	public Page<Map<String, String>> findAll(RequestEmployee requestEmployee) {
		if("CODE".equals(requestEmployee.getSearchBy())) {
			Pageable pageable = PageRequest.of(requestEmployee.getPage(), requestEmployee.getSize());
			
			return employeeRepo.searchByOrganizationCode(requestEmployee.getSearchCriteria(), pageable);
		} else if("NAME".equals(requestEmployee.getSearchBy())) {
			Pageable pageable = PageRequest.of(requestEmployee.getPage(), requestEmployee.getSize());
			
			return employeeRepo.searchByOrganizationName(requestEmployee.getSearchCriteria(), pageable);
		}
		Pageable pageable = PageRequest.of(requestEmployee.getPage(), requestEmployee.getSize());
		
		return new PageImpl(Collections.emptyList(), pageable, 0);
	}
	
	public Employee save(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	@Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) throws IOException {
        // Cek ekstensi file
        String fileExtension = getFileExtension(file);
        if (!fileExtension.equals("jpg") && !fileExtension.equals("jpeg") && !fileExtension.equals("png") && !fileExtension.equals("gif")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }

        // Cek ukuran file
        if (file.getSize() > 5 * 1024 * 1024) { // 5 MB
            throw new IllegalArgumentException("File size exceeds limit of 5 MB");
        }

        // Tentukan path file
        Path path = Paths.get(uploadDir, file.getOriginalFilename());
        Files.createDirectories(path.getParent()); // Pastikan direktori ada

        // Simpan file
        file.transferTo(path.toFile());
        
        String temp = path.toString();
        int start= temp.indexOf("\\public\\");
        temp = temp.substring(start);
        temp = temp.replaceAll("\\\\", "/");
        logger.info("temppp: " + temp);
        
        return temp;
    }

    private String getFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return filename != null ? filename.substring(filename.lastIndexOf(".") + 1).toLowerCase() : "";
    }
}
