package co.edu.usbcali.records.mapper;

import co.edu.usbcali.records.domain.Category;
import co.edu.usbcali.records.dto.CategoryRequestDTO;
import co.edu.usbcali.records.dto.CategoryResponseDTO;

import java.util.List;

public class CategoryMapper {

    public static CategoryResponseDTO EntityToDto(Category category) {

        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public static List<CategoryResponseDTO> EntityToDoList(List<Category> categories) {
        return categories.stream().map(CategoryMapper::EntityToDto).toList();
    }

    public static Category requestDtoToEntity(CategoryRequestDTO categoryRequestDTO) {
        return Category.builder().name(categoryRequestDTO.getName()).description(categoryRequestDTO.getDescription()).build();
    }
}
