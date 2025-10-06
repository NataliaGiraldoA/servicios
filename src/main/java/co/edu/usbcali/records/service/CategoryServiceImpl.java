package co.edu.usbcali.records.service;

import co.edu.usbcali.records.domain.Category;
import co.edu.usbcali.records.dto.CategoryRequestDTO;
import co.edu.usbcali.records.dto.CategoryResponseDTO;
import co.edu.usbcali.records.mapper.CategoryMapper;
import co.edu.usbcali.records.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<String> getCategoriesString() {
        List<Category> categories = categoryRepository.findAll();
        List<String> categoriesString = new ArrayList<>();
        for (Category category : categories) {
            categoriesString.add(category.getName());
        }
        return categoriesString;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getCategoriesResponseDTO() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> categoriesDTO = CategoryMapper.EntityToDoList(categories);
        return categoriesDTO;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) throws Exception {

        // 1. Validar el objeto genreRequestDTO
        //Validar que el objeto no sea nulo

        if (categoryRequestDTO == null) {
            throw new Exception("Category name is empty");
        }
        //2.Validar los campos de el objeto
        if (categoryRequestDTO.getName() == null || categoryRequestDTO.getName().isBlank()) {
            throw new Exception("Category name is empty");
        }
        //Convertir el requestDTO hacia Entidad
        Category category = CategoryMapper.requestDtoToEntity(categoryRequestDTO);
        category = categoryRepository.save(category);

        return CategoryMapper.EntityToDto(category);
    }
}
