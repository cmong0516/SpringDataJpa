package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.datajpa.domain.Member;
import study.datajpa.dto.MemberDto;

import java.util.List;

//@Repository
// 생략해도됨.
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsername(String username);

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
    // 있나 찾아보고 없으면 만들면 되겠다 :)
    // 공식문서 사용!
    // 이름이 조금이라도 다르면 원하는대로 실행되지 않는다.

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);
    // .setParameter 로 하던걸 @RequestParam 하듯이 !

    @Query("select new study.datajpa.dto.MemberDto(m.id,m.username,t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    // count 쿼리는 모든 항목을 조회하기 때문에 쿼리를 나눠서 효율적으로 사용.
//    @Query(value = "select m from Member m left join m.team t")
    @Query(value = "select m from Member m ", countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    Slice<Member> findByAgeSlice(int age, Pageable pageable);
}
