package cs5500.project.spring.repository;


import java.util.List;

import cs5500.project.db.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * AssignmentRepository class which extends the MongoRepository and queries the Assignment
 * table in the database
 */
@RepositoryRestResource(collectionResourceRel = "assignments", path = "assignments")
public interface AssignmentRepository extends MongoRepository<Assignment, String>{

    /**
     * method to return all assignments
     * @return a list of assignments
     */
    List<Assignment> findAll();

    /**
     * method that returns a list of assignments of the given course
     * @param course a course
     * @return list of Assignments
     */
    List<Assignment> findAssignmentsByCourse(@Param("course") String course);

}