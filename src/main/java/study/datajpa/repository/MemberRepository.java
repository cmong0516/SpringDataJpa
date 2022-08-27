package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.datajpa.domain.Member;

import java.util.List;

//@Repository
// 생략해도됨.
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsername(String username);

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
    // 있나 찾아보고 없으면 만들면 되겠다 :)
}
