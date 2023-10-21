package ru.practicum.ewmmain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewmmain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select count(c)>0 from Category c where c.name = :name and (:id is null or c.id !=:id)")
    Boolean isNameExisting(Long id, String name);
}
