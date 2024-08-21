package com.fsoft.jwdnd.course1.cloudstorage.repository;

import com.fsoft.jwdnd.course1.cloudstorage.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

    List<File> findByUserId(Integer user_id);

}
