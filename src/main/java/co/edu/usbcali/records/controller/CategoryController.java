package co.edu.usbcali.records.controller;


import co.edu.usbcali.records.dto.CategoryRequestDTO;
import co.edu.usbcali.records.dto.CategoryResponseDTO;
import co.edu.usbcali.records.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    public List<String> getCategoriesString() {
        return categoryService.getCategoriesString();
    }

    @GetMapping("/all/object")
    public List<CategoryResponseDTO> getCategoriesResponseDTO(){
       return categoryService.getCategoriesResponseDTO();
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> saveCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) throws Exception {
        return new ResponseEntity<>(categoryService.saveCategory(categoryRequestDTO), HttpStatus.CREATED);

    }
}
