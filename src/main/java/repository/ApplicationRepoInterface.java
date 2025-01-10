package repository;


import model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepoInterface extends JpaRepository<Application,Long> {
    // No additional methods are added in this interface, as it extends JpaRepository.
    // JpaRepository provides methods for basic CRUD operations for QuizEntry entities.
}
