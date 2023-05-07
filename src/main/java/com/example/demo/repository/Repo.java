package com.example.demo.repository;

import com.example.demo.entity.Konsekrowany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<Konsekrowany,Long>  {



}
