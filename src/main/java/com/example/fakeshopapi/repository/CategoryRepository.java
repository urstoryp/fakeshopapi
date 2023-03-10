package com.example.fakeshopapi.repository;

import com.example.fakeshopapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
