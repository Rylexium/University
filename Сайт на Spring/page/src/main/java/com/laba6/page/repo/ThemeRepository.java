package com.laba6.page.repo;

import com.laba6.page.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer>, JpaSpecificationExecutor<Theme> {

//    @Query(value = "SELECT id, name, id_teacher, id_student, type_work, year, grade\n" +
//            " FROM public.themes where type_work=:number", nativeQuery = true)
//    List<Theme> findAllByWork(@Param("number") Integer number);
//
//
//    @Query(value = "SELECT id, name, id_teacher, id_student, type_work, year, grade\n" +
//            " FROM public.themes where id_teacher=:teacher", nativeQuery = true)
//    List<Theme> findAllByIdTeacher(@Param("teacher") Integer teacher);
//
//    @Query(value = "SELECT id, name, id_teacher, id_student, type_work, year, grade\n" +
//            " FROM public.themes where id_student=:student", nativeQuery = true)
//    List<Theme> findAllByIdStudent(@Param("student") Integer student);
//
//    @Query(value = "SELECT id, name, id_teacher, id_student, type_work, year, grade\n" +
//            " FROM public.themes where year=:year", nativeQuery = true)
//    List<Theme> findAllByYear(@Param("year") Integer year);
//
//    @Query(value = "SELECT id, name, id_teacher, id_student, type_work, year, grade\n" +
//            " FROM public.themes where grade=:grade", nativeQuery = true)
//    List<Theme> findAllByGrade(@Param("grade") Integer grade);
//
    @Query(value = "select t.* " +
                    "from themes t " +
                    "INNER JOIN themes_keys tk on t.id=tk.id_theme " +
                    "where tk.id_key=:idKeyWord", nativeQuery = true)
    List<Theme> findAllByKeyWord(@Param("idKeyWord") Integer idKeyWord);
}
