package com.livinu.portfolio.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.livinu.portfolio.dto.ProductRequset;
import com.livinu.portfolio.dto.ProductResponse;
import com.livinu.portfolio.service.ProductService;
import com.livinu.portfolio.util.DeleteAPIResponse;

@RestController()
@RequestMapping("/product")
public class ProductController {
	
	 private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	/*@RequestMapping("/")
	public String index() {
		return "Spring Boot Example";
	}*/

	@Autowired
	ProductService productService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		return new ResponseEntity<List<ProductResponse>>(productService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
		return new ResponseEntity<ProductResponse>(productService.getProductById(productId), HttpStatus.OK);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequset productRequset) {
		return new ResponseEntity<ProductResponse>(productService.save(productRequset), HttpStatus.CREATED);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductResponse> update(@Valid @RequestBody ProductRequset productRequset) {
		return new ResponseEntity<ProductResponse>(productService.update(productRequset), HttpStatus.OK);
	}

	@DeleteMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<DeleteAPIResponse> delete(@PathVariable Long productId) {
		return new ResponseEntity<DeleteAPIResponse>(productService.delete(productId), HttpStatus.OK);
	}
	
	/*** ---- file uploader ---- ***/
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/home/ravi/images/";
    
   
    @PostMapping("/api/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadfile) {

        logger.debug("Single file upload!");
        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
            saveUploadedFiles(Arrays.asList(uploadfile));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }
    
    @RequestMapping("/upload")
    public String upload(Model model,@RequestParam("files") MultipartFile[] uploadfiles) {
    	
    	StringBuilder filesName = new StringBuilder();
    	for(MultipartFile file: uploadfiles) {
    		Path path = Paths.get(UPLOADED_FOLDER, file.getOriginalFilename());
    		filesName.append(file.getOriginalFilename());
    		try {
				Files.write(path, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    	}
    	model.addAttribute("msg", "Successfullyfiles uploaded >>>> "+filesName.toString());
		return "index";
    }
    

    // 3.1.2 Multiple file upload
   /* @PostMapping("/api/upload/multi")
    public ResponseEntity<?> uploadFileMulti(
            @RequestParam("extraField") String extraField,
            @RequestParam("files") MultipartFile[] uploadfiles) {
        logger.debug("Multiple file upload!");

        // Get file name
        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
            saveUploadedFiles(Arrays.asList(uploadfiles));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - "
                + uploadedFileName, HttpStatus.OK);

    }*/
    
  /*  @PostMapping("/api/upload/multi/model")
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute Product model) {

        logger.debug("Multiple file upload! With UploadModel");

        try {

            saveUploadedFiles(Arrays.asList(model.getFiles()));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

    }*/
    
    //save file
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }

    }

    
    

}
