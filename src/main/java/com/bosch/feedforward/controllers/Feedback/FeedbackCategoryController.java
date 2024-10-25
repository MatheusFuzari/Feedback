package com.bosch.feedforward.controllers.Feedback;

import com.bosch.feedforward.dto.Feedback.FeedbackCategoryDTO;
import com.bosch.feedforward.entity.Feedback.FeedbackCategory;
import com.bosch.feedforward.services.Impl.Feedback.FeedbackCategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class FeedbackCategoryController {

    @Autowired
    private FeedbackCategoryServiceImpl feedbackCategoryService;

    @GetMapping("/category/{id}")
    private ResponseEntity<FeedbackCategory> getCategoryById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(this.feedbackCategoryService.getCategoryById(id));
    }

    @GetMapping("/category")
    private ResponseEntity<Page<FeedbackCategory>> getAllCategories(Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(this.feedbackCategoryService.getFeedbackCategories(page));
    }

    @PostMapping("/category")
    private ResponseEntity<List<FeedbackCategory>> createCategories(@RequestBody @Valid List<FeedbackCategoryDTO> data){
        List<FeedbackCategory> feedbackCategoryList = data.stream().map(
                dto -> {
                    FeedbackCategory convertedCategory = new FeedbackCategory();
                    BeanUtils.copyProperties(dto, convertedCategory);
                    return convertedCategory;
                }
        ).toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(this.feedbackCategoryService.createCategories(feedbackCategoryList));
    }

    @PutMapping("/category/{id}")
    private ResponseEntity<FeedbackCategory> updateCategory(@PathVariable UUID id, @RequestBody @Valid FeedbackCategoryDTO data){
        FeedbackCategory convertedCategory = new FeedbackCategory();
        BeanUtils.copyProperties(data, convertedCategory);

        return ResponseEntity.status(HttpStatus.OK).body(this.feedbackCategoryService.updateCategory(id, convertedCategory));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/category/{id}")
    private void deleteCategory(@PathVariable UUID id){
        this.feedbackCategoryService.deleteCategory(id);
    }
}
