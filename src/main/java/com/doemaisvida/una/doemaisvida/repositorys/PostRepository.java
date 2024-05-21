package com.doemaisvida.una.doemaisvida.repositorys;

import com.doemaisvida.una.doemaisvida.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

}
