package br.com.automatodev.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.automatodev.dto.TaskModel;
import br.com.automatodev.dto.UserModel;
import br.com.automatodev.infrastructure.entity.TaskEntity;
import br.com.automatodev.infrastructure.entity.UserEntity;
import br.com.automatodev.infrastructure.exception.NotFoundException;
import br.com.automatodev.repository.TaskRepository;
import br.com.automatodev.service.TaskService;
import br.com.automatodev.service.UserService;
import lombok.NonNull;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TaskRepository taskRepo;

    /* ------------------------------------------------------------------------------------------------------*/

    @Override
    public List<TaskModel> getAllUserTasks(@NonNull String userId) {
      
        UserModel userTask = userService.getUserById(userId).orElseThrow(() -> new NotFoundException(String.format("userId %s ", userId)));
        UserEntity userDatabase = modelMapper.map(userTask, UserEntity.class);
        userDatabase.setId(UUID.fromString(userId));

        List<TaskEntity> databaseTasks = taskRepo.findByUser(userDatabase);
        
        List<TaskModel> userTasks = new ArrayList<>();
        databaseTasks.forEach(task -> {

            userTasks.add(modelMapper.map(task, TaskModel.class));
        });

        return userTasks;
    }

    /* ------------------------------------------------------------------------------------------------------*/

    @Override
    public TaskModel postNewTask(@NonNull String userId, @NonNull TaskModel taskModel) {
         
        UserModel userTask = userService.getUserById(userId).orElseThrow(() -> new NotFoundException(String.format("userId %s ", userId)));
        UserEntity userDatabase = modelMapper.map(userTask, UserEntity.class);
        userDatabase.setId(UUID.fromString(userId));
        
        TaskEntity newTaskDatabase = modelMapper.map(taskModel, TaskEntity.class);
        newTaskDatabase.setUser(userDatabase);

        newTaskDatabase = taskRepo.save(newTaskDatabase);

        return modelMapper.map(newTaskDatabase, TaskModel.class);
    }

    /* ------------------------------------------------------------------------------------------------------*/
    
    @Override
    public Optional<TaskModel> getTaskById(String taskId) {

        return taskRepo.findById(UUID.fromString(taskId))
        .map(taskDatabase -> Optional.of(modelMapper.map(taskDatabase, TaskModel.class)))
        .orElseGet(() -> Optional.ofNullable(null));

    }
    
    /* ------------------------------------------------------------------------------------------------------*/

}
