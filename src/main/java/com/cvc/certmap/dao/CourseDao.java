package com.cvc.certmap.dao;

import com.cvc.certmap.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther xiehuaxin
 * @create 2018-12-18 11:19
 * @todo
 */
@Repository
public interface CourseDao extends JpaRepository<Course,Integer> {
    Course findByIdAndStatus(Integer goodsId, int usable);
}
