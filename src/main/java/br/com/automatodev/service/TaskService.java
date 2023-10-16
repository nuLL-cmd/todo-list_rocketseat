package br.com.automatodev.service;

import java.util.List;
import java.util.Optional;

import br.com.automatodev.dto.TaskModel;
import io.micrometer.common.lang.NonNull;
    
public interface TaskService {
    
    List<TaskModel> getAllUserTasks(@NonNull String userId);
    
    Optional<TaskModel> getTaskById(String userId) ;

    TaskModel postNewTask(@NonNull String userId, @NonNull TaskModel taskModel);
}
