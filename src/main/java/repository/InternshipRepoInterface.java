package repository;

import model.Internship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipRepoInterface extends JpaRepository<Internship,Long> {}
