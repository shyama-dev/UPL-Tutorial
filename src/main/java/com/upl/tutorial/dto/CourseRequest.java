package com.upl.tutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class CourseRequest {

    private String title;
    
    private String description;

    private int instructor_id;

    private String status;
    /*

    public CourseRequest() {
    }
   

    public CourseRequest( String title, String description, int instructor_id, String status) {
        //this.course_id = course_id;
        this.title = title;
        this.description = description;
        this.instructor_id = instructor_id;
        this.status = status;
    }

    
    @Override
    public String toString() {
        return "CourseRequest [title=" + title + ", description=" + description
                + ", instructor_id=" + instructor_id + ", status=" + status + "]";
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    */
    
}
