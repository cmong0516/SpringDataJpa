package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.datajpa.domain.Item;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void save() {
        Item item = new Item();
        // id 값 null
        itemRepository.save(item);
        // id 값이 null 이기 때문에 jpa repository 에서 if 문에 걸려 새로운 앤티티로 인식.
        // em.persist 후에 Id 값 생성.
        // GeneratedValue 를 안쓸경우 Persistable 를 사용하고 @CreateDate 를 기준으로 isNew 메서드를 재정의 하면 좋다.
        // EntityListeners 빼먹지 않기 !
    }

}