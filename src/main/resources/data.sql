-- 1. Location (동 단위까지의 위치라고 가정)
INSERT INTO location (id, name) VALUES (101, '강남동'); -- 미션 대상 위치
INSERT INTO location (id, name) VALUES (102, '송파동'); -- 미션 비대상 위치

-- 2. Food (가게 종류 및 사용자 선호 음식)
INSERT INTO food (id, name, created_at, updated_at) VALUES (500, 'KOREAN', NOW(), NOW());   -- 미션 대상 음식
INSERT INTO food (id, name, created_at, updated_at) VALUES (501, 'WESTERN', NOW(), NOW());   -- 미션 비대상 음식

-- 3. Member (사용자)
-- MEMBER_A (ID: 1000) - 미션 대상 사용자
INSERT INTO member (id, name, location_id, email, date_of_birth, status, mission_num, total_point, sns_type, created_at, updated_at)
VALUES (1000, 'MEMBER_A', 101, 'a@test.com', '1990-01-01', 'ACTIVE', 0, 100, 'APPLE', NOW(), NOW());

-- MEMBER_B (ID: 1001) - 미션 비대상 사용자 (위치가 다름)
INSERT INTO member (id, name, location_id, email, date_of_birth, status, mission_num, total_point, sns_type, created_at, updated_at)
VALUES (1001, 'MEMBER_B', 102, 'b@test.com', '1995-05-05', 'ACTIVE', 0, 50, 'GOOGLE', NOW(), NOW());


-- 5. Store (가게)
INSERT INTO store (id, name, location_id, food_id, owner_id, business_status, rating, created_at, updated_at)
VALUES (200, '강남 한식당', 101, 500, 900, 'OPERATING', 0.0, NOW(), NOW()); -- ⭐ 미션 대상 가게
INSERT INTO store (id, name, location_id, food_id, owner_id, business_status, rating, created_at, updated_at)
VALUES (201, '강남 양식집', 101, 501, 901, 'OPERATING', 0.0, NOW(), NOW()); -- 미션 비대상 (음식 불일치)
INSERT INTO store (id, name, location_id, food_id, owner_id, business_status, rating, created_at, updated_at)
VALUES (202, '송파 한식당', 102, 500, 902, 'CLOSED', 0.0, NOW(), NOW()); -- 미션 비대상 (위치 불일치)

-- 6. Review (미션 수행 결과와는 무관하며, 이전 '나의 리뷰' 테스트용 데이터로 유지)
-- MEMBER_A의 리뷰 (rating: BigDecimal로 가정)
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (300, 5.0, '너무 맛있어요!', 1000, 200, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (301, 3.5, '너무 비싸요!', 1000, 201, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (302, 4.5, '친절하고 분위기 좋아요.', 1000, 202, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (303, 3.0, '기대보다 별로였어요.', 1000, 201, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (304, 5.0, '인생 맛집 인정!', 1000, 200, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (305, 2.5, '음식이 너무 늦게 나와요.', 1000, 200, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (306, 4.0, '재료가 신선했어요.', 1000, 202, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (307, 3.8, '주차 공간이 협소해요.', 1000, 201, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (308, 4.9, '사장님이 서비스 주셨어요!', 1000, 200, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (309, 1.0, '최악, 다신 안 갈 거예요.', 1000, 201, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (310, 4.2, '가성비가 좋은 편이에요.', 1000, 202, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (311, 5.0, '완벽 그 자체! 강추!', 1000, 200, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (312, 3.3, '그냥 평범한 맛입니다.', 1000, 202, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (313, 4.6, '친구들 데려와도 좋을 듯.', 1000, 201, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (314, 2.0, '자리가 좁아서 불편했어요.', 1000, 200, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (315, 4.8, '데이트 장소로 최고!', 1000, 202, NOW(), NOW());
INSERT INTO review (id, rating, content, member_id, store_id, created_at, updated_at) VALUES (316, 1.5, '주문 실수 있었어요.', 1000, 201, NOW(), NOW());

-- 7. Mission (미션 데이터 추가)
-- Mission 엔티티 필드: id, status, min_order, due_date, point, store_id, created_at, updated_at
-- store_id = 200 (총 20개)
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (600, 'CHALLENGABLE', 15000, DATE_ADD(CURDATE(), INTERVAL 2 MONTH), 50, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (601, 'CHALLENGABLE', 20000, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), 100, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (602, 'CHALLENGABLE', 10000, DATE_ADD(CURDATE(), INTERVAL 3 DAY), 30, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (603, 'CHALLENGABLE', 15000, DATE_ADD(CURDATE(), INTERVAL 2 MONTH), 50, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (604, 'CHALLENGABLE', 20000, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), 100, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (605, 'CHALLENGABLE', 10000, DATE_ADD(CURDATE(), INTERVAL 3 DAY), 30, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (606, 'CHALLENGABLE', 15000, DATE_ADD(CURDATE(), INTERVAL 2 MONTH), 50, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (607, 'CHALLENGABLE', 20000, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), 100, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (608, 'CHALLENGABLE', 10000, DATE_ADD(CURDATE(), INTERVAL 3 DAY), 30, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (609, 'CHALLENGABLE', 15000, DATE_ADD(CURDATE(), INTERVAL 2 MONTH), 50, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (610, 'CHALLENGABLE', 20000, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), 100, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (611, 'CHALLENGABLE', 10000, DATE_ADD(CURDATE(), INTERVAL 3 DAY), 30, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (612, 'CHALLENGABLE', 15000, DATE_ADD(CURDATE(), INTERVAL 2 MONTH), 50, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (613, 'CHALLENGABLE', 20000, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), 100, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (614, 'CHALLENGABLE', 10000, DATE_ADD(CURDATE(), INTERVAL 3 DAY), 30, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (615, 'CHALLENGABLE', 15000, DATE_ADD(CURDATE(), INTERVAL 2 MONTH), 50, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (616, 'CHALLENGABLE', 20000, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), 100, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (617, 'CHALLENGABLE', 10000, DATE_ADD(CURDATE(), INTERVAL 3 DAY), 30, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (618, 'CHALLENGABLE', 15000, DATE_ADD(CURDATE(), INTERVAL 2 MONTH), 50, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (619, 'CHALLENGABLE', 20000, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), 100, 200, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (620, 'CHALLENGABLE', 10000, DATE_ADD(CURDATE(), INTERVAL 3 DAY), 30, 201, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (621, 'CHALLENGABLE', 15000, DATE_ADD(CURDATE(), INTERVAL 2 MONTH), 50, 201, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (622, 'CHALLENGABLE', 20000, DATE_ADD(CURDATE(), INTERVAL 1 MONTH), 100, 201, NOW(), NOW());
INSERT INTO mission (id, status, min_order, due_date, point, store_id, created_at, updated_at)
VALUES (623, 'CHALLENGABLE', 10000, DATE_ADD(CURDATE(), INTERVAL 3 DAY), 30, 201, NOW(), NOW());