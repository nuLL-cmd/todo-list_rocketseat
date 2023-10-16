package br.com.automatodev.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.automatodev.infrastructure.entity.TaskEntity;
import br.com.automatodev.infrastructure.entity.UserEntity;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<TaskEntity, UUID>{

    List<TaskEntity> findByUser(UserEntity user);
}
