package com.delivery.api.db.store.repository;

import com.delivery.db.store.entity.StoreEntity;
import com.delivery.db.store.enums.StoreCategory;
import com.delivery.db.store.enums.StoreStatus;
import com.delivery.db.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Transactional
public class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @BeforeEach
    void setUp() {

        StoreEntity storeEntity1 = StoreEntity.builder()
                .name("홍콩반점")
                .address("서울시 강남구")
                .description("중국집")
                .phone("010-1234-1234")
                .category(StoreCategory.CHINESE)
                .build();

        StoreEntity storeEntity2 = StoreEntity.builder()
                .name("교촌치킨")
                .address("서울시 강남구")
                .description("치킨집")
                .phone("010-0000-0000")
                .category(StoreCategory.CHICKEN)
                .build();

        storeRepository.saveAll(List.of(storeEntity1, storeEntity2));
    }

    @Test
    void 가게_이름과_가게_상태로_가게_정보를_찾는다() {

        Optional<StoreEntity> storeEntity = storeRepository.findByNameAndStatus("홍콩반점", StoreStatus.REGISTERED);

        then(storeEntity).isPresent();
        then(storeEntity.get().getName()).isEqualTo("홍콩반점");
        then(storeEntity.get().getStatus()).isEqualTo(StoreStatus.REGISTERED);
    }

    @Test
    void 존재하지_않는_가게_이름으로_검색하면_null을_반환한다() {

        Optional<StoreEntity> storeEntity = storeRepository.findByNameAndStatus("abc", StoreStatus.REGISTERED);

        then(storeEntity).isEmpty();
    }

    @Test
    void 가게_상태로_가게_정보를_찾는다() {

        List<StoreEntity> storeEntities = storeRepository.findAllByStatus(StoreStatus.REGISTERED);

        then(storeEntities).isNotEmpty();
        then(storeEntities.size()).isEqualTo(2);
    }

    @Test
    void 가게_카테고리와_가게_상태로_가게_정보를_찾는다() {

        List<StoreEntity> storeEntities = storeRepository.findAllByCategoryAndStatus(StoreCategory.CHINESE, StoreStatus.REGISTERED);

        then(storeEntities).isNotEmpty();
        then(storeEntities.size()).isEqualTo(1);
    }

    @Test
    void 존재하지_않는_가게_카테고리와_가게_상태로_검색하면_null을_반환한다() {

        List<StoreEntity> storeEntities = storeRepository.findAllByCategoryAndStatus(StoreCategory.KOREAN, StoreStatus.REGISTERED);

        then(storeEntities).isEmpty();
    }
}
