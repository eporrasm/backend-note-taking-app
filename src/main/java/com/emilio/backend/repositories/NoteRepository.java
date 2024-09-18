package com.emilio.backend.repositories;

import com.emilio.backend.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long>{
    List<NoteEntity> findByArchived(boolean archived);
    List<NoteEntity> findByArchivedAndCategoryId(boolean archived, Long categoryId);
}
