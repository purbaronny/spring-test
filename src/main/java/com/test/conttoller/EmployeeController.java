package com.test.conttoller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.entity.Employee;
import com.test.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/test/member")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public Iterable<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public Employee findById(@PathVariable("id") Integer id) {
		return employeeService.findById(id);
	}
	
	@GetMapping("/reportTo/{to}")
	public Iterable<Employee> findAllReportTo(@PathVariable("to") String to) {
		return employeeService.findAllByPositionCode(to);
	}
	
	@GetMapping("/{page}/{size}")
	public Page<Employee> findAll(@PathVariable("page") int page, @PathVariable("size") int size) {
		return employeeService.findAll(page, size);
	}
	
	@PostMapping("/searchBy")
	public Page<Map<String, String>> findSearchCriteria(@RequestBody RequestEmployee requestEmployee) {
		return employeeService.findAll(requestEmployee);
	}
	
	@PostMapping
	public ResponseEntity<ResponseData<Employee>> save(@RequestBody Employee employee, Errors errors) {
		ResponseData<Employee> responseData = new ResponseData<>();
		
		if(errors.hasErrors()) {
			for(ObjectError objectError : errors.getAllErrors()) {
				responseData.getMessages().add(objectError.getDefaultMessage());
			}
			responseData.setStatus(false);
			responseData.setPayLoad(null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
		}
		try {
			Employee employee2 = employeeService.save(employee);
			
			responseData.setStatus(true);			
			responseData.setPayLoad(employee2);
		} catch(Exception e) {
			if(errors.hasErrors()) {
				for(ObjectError objectError : errors.getAllErrors()) {
					responseData.getMessages().add(objectError.getDefaultMessage());
				}
				responseData.setStatus(false);
				responseData.setPayLoad(null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
			} else {			
				responseData.getMessages().add(e.getMessage());
				responseData.setStatus(false);
				responseData.setPayLoad(null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
			}
		}
		
 		return ResponseEntity.ok(responseData);
	}
	
	 @PostMapping("/upload")
	 public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		 logger.info("sudah masuk");
        try {
        	logger.info("sudah masuk 0");
            String filePath = employeeService.uploadFile(file);
            logger.info("sudah masuk 1");
            return ResponseEntity.ok(filePath);
        } catch (IllegalArgumentException e) {
        	logger.error("sudah masuk 3: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
        	logger.error("sudah masuk 4: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
	 }
}
