package co.edu.usbcali.records.service;

import co.edu.usbcali.records.dto.CategoryRequestDTO;
import co.edu.usbcali.records.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<String> getCategoriesString();
    List<CategoryResponseDTO> getCategoriesResponseDTO();
    CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) throws Exception;
    CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO categoryRequestDTO) throws Exception;
    CategoryResponseDTO updateCategoryPartial(Integer id, CategoryRequestDTO categoryRequestDTO) throws Exception;
}
