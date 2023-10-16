package br.com.automatodev.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.automatodev.dto.ReturnMessage;
import br.com.automatodev.dto.TaskModel;
import br.com.automatodev.infrastructure.exception.NotFoundException;
import br.com.automatodev.service.TaskService;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /* ------------------------------------------------------------------------------------------------------*/

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReturnMessage> create(@RequestParam String userId, @RequestBody TaskModel newTask) {

        try {
            
            TaskModel createdTask = taskService.postNewTask(userId, newTask);
            return new ResponseEntity<>(new ReturnMessage(HttpStatus.CREATED.toString(), HttpStatus.CREATED, createdTask), HttpStatus.CREATED);

        } catch (Exception e) {
           
            if(e instanceof NotFoundException) {

                return new ResponseEntity<>(
                    new ReturnMessage(e.getMessage(), HttpStatus.BAD_REQUEST, newTask)
                    , HttpStatus.BAD_REQUEST);
            }

             return new ResponseEntity<>(
                    new ReturnMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, newTask)
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* ------------------------------------------------------------------------------------------------------*/
        
    @GetMapping(value = "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReturnMessage> getById(@PathVariable String taskId) {

        try {

        return new ResponseEntity<ReturnMessage>(
            new ReturnMessage(HttpStatus.OK.toString(), HttpStatus.OK, 
            taskService.getTaskById(taskId).orElse(null))
            , HttpStatus.OK);

        }catch (Exception e) {

        return new ResponseEntity<>(
                    new ReturnMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null)
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* ------------------------------------------------------------------------------------------------------*/

}
