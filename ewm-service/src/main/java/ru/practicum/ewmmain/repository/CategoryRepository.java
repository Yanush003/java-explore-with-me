package ru.practicum.ewmmain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewmmain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
