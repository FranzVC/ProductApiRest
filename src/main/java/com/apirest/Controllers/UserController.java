package com.apirest.Controllers;

import com.apirest.Exceptions.ResourceNotFoundException;
import com.apirest.Models.User;
import com.apirest.Services.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Users Management System", description = "Operations pertaining to user in Users Management System")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return (List<User>) userService.findAll();
    }

    @ApiOperation(value = "Get a user by Id")
    @GetMapping("/user/{id}")

    public User getUser(@ApiParam(value = "User id from whom object will retrieve",
            required = true) @PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return userService.findById(id);
    }

    @ApiOperation(value = "Add a user")
    @PostMapping("/user")
    public User postUser(@ApiParam(value = "User you want save in database",
            required = true) @Valid @RequestBody User user) {
        return userService.save(user);
    }

    @ApiOperation(value = "Remove a user from database")
    @DeleteMapping("/user/{id}")
    public HttpStatus deleteUser(@ApiParam(value = "User id whom will be removed",
            required = true) @PathVariable(value = "id") Integer id) {
        userService.deleteById(id);
        return HttpStatus.OK;
    }

    @ApiOperation(value = "Update a user")
    @PutMapping("/user/{id}")
    public ResponseEntity<User> putUser(@ApiParam(value = "User id whose will be updated", required = true) @PathVariable(value = "id") Integer id,
                                              @ApiParam(value = "Update user object", required = true) @Valid @RequestBody User user) throws ResourceNotFoundException {
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }
}