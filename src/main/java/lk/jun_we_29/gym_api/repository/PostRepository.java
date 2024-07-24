package lk.jun_we_29.gym_api.repository;

import lk.jun_we_29.gym_api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("from Post p where p.user.id=:id")
    List<Post> findAllByUserId(@Param("id") long id);
}
