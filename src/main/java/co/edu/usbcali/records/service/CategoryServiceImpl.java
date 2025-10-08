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
import java.util.Map;
import java.util.Optional;

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
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO categoryRequestDTO) throws Exception {
        if (id == null) {
            throw new Exception("Category id is required");
        }

        if (categoryRequestDTO == null) {
            throw new Exception("Category data is required");
        }
        // Buscar la categoría existente
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found with id: " + id));
        // Actualizar los campos
        existingCategory.setName(categoryRequestDTO.getName());
        existingCategory.setDescription(categoryRequestDTO.getDescription());

        // Guardar y retornar
        Category updatedCategory = categoryRepository.save(existingCategory);
        return CategoryMapper.EntityToDto(updatedCategory);
    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public CategoryResponseDTO updateCategoryPartial(Integer id, CategoryRequestDTO categoryRequestDTO) throws Exception {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found with id: " + id));

        if(categoryRequestDTO.getName() != null){
            existingCategory.setName(categoryRequestDTO.getName());
        }
        if(categoryRequestDTO.getDescription() != null){
            existingCategory.setDescription(categoryRequestDTO.getDescription());
        }
        Category updatedCategoryPartial = categoryRepository.save(existingCategory);
        return CategoryMapper.EntityToDto(updatedCategoryPartial);
    }


}
