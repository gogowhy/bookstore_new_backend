package book.demo.repository;
import book.demo.repository.*;
import book.demo.entity.*;
import book.demo.controller.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import book.demo.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface orderkafkaRepository extends JpaRepository<orderkafka,Integer>{


}
