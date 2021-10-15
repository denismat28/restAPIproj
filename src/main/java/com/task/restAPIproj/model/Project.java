package com.task.restAPIproj.model;

import com.task.restAPIproj.entity.ProjectEntity;
import com.task.restAPIproj.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    private Long projectId;
    private String title;
    private List<User> users=new ArrayList<>();

    public static Project toModel(ProjectEntity entity) {
        Project model = new Project();
        model.setProjectId(entity.getProjectId());
        model.setTitle(entity.getTitle());
        model.setUsers(entity.getUsers().stream().map(User::toModel).collect(Collectors.toList()));
        return model;
    }
}
