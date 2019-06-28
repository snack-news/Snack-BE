-- Topic 테스트 데이터
insert into topic (id, type, name) values (1, 'CORP', '카카오');
insert into topic (id, type, name) values (2, 'CORP', '애플');
insert into topic (id, type, name) values (3, 'CORP', '삼성');
insert into topic (id, type, name) values (4, 'FIELD', '블록체인');

-- News 테스트 데이터
insert into news(id, title, content, create_at, modified_at) values (1, '첫 번째 뉴스 타이틀', '첫 번째 뉴스 내용입니다.', '2019-06-21 00:00:00', '2019-10-21 00:00:00');
insert into news(id, title, content, create_at, modified_at) values (2, '두 번째 뉴스 타이틀', '두 번째 뉴스 내용입니다.', '2019-07-21 00:00:00', '2019-10-21 00:00:00');
insert into news(id, title, content, create_at, modified_at) values (3, '세 번째 뉴스 타이틀', '세 번째 뉴스 내용입니다.', '2019-08-21 00:00:00', '2019-10-21 00:00:00');
insert into news(id, title, content, create_at, modified_at) values (4, '네 번째 뉴스 타이틀', '네 번째 뉴스 내용입니다.', '2019-09-21 00:00:00', '2019-10-21 00:00:00');

-- News-Topic 연관관계 테스트 데이터
insert into news_topic(news_id, topic_id) values (1, 1);
insert into news_topic(news_id, topic_id) values (2, 1);
insert into news_topic(news_id, topic_id) values (3, 2);
insert into news_topic(news_id, topic_id) values (4, 3);