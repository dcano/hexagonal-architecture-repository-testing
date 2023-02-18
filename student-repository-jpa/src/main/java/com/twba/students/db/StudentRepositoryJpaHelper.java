package com.twba.students.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryJpaHelper extends JpaRepository<StudentEntity, String> {

    StudentEntity findStudentEntityByEmailAddressAndActive(String emailAddress, boolean active);

}
