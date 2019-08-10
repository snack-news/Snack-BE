-- Category 테스트 데이터
insert into category (id, title) values (1, 'IT');
insert into category (id, title) values (2, '커머스');

-- Topic 테스트 데이터
insert into topic (id, type, name) values (1, 'CORP', '카카오');
insert into topic (id, type, name) values (2, 'CORP', '애플');

-- Tag 테스트 데이터
insert into tag(id, title) values (1,'TOP10');
insert into tag(id, title) values (2,'HOT');

-- News 테스트 데이터
insert into news(id, category_id, title, content, create_at, modified_at) values (1, 1, '01 news title', 'news content: IT/카카오/[none]', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (2, 1, '04 news title', 'news content: IT/카카오/TOP10', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (3, 1, '05 news title', 'news content: IT/카카오/HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (4, 1, '06 news title', 'news content: IT/카카오/TOP10,HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (5, 1, '07 news title', 'news content: IT/애플/[none]', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (6, 1, '08 news title', 'news content: IT/애플/TOP10', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (7, 1, '09 news title', 'news content: IT/애플/HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (8, 1, '02 news title', 'news content: IT/애플/TOP10,HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (9, 1, '03 news title', 'news content: IT/카카오/[none]', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (10, 1, '10 news title', 'news content: IT/카카오/TOP10', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (11, 1, '11 news title', 'news content: IT/카카오/HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (12, 1, '12 news title', 'news content: IT/카카오/TOP10,HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (13, 2, '13 news title', 'news content: 커머스/애플/[none]', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (14, 2, '14 news title', 'news content: 커머스/애플/TOP10', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (15, 2, '15 news title', 'news content: 커머스/애플/HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (16, 2, '16 news title', 'news content: 커머스/애플/TOP10,HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (17, 2, '17 news title', 'news content: 커머스/카카오/[none]', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (18, 2, '18 news title', 'news content: 커머스/카카오/TOP10', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (19, 2, '19 news title', 'news content: 커머스/카카오/HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (20, 2, '20 news title', 'news content: 커머스/카카오/TOP10,HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (21, 2, '21 news title', 'news content: 커머스/애플/[none]', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (22, 2, '22 news title', 'news content: 커머스/애플/TOP10', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (23, 2, '23 news title', 'news content: 커머스/애플/HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (24, 2, '24 news title', 'news content: 커머스/애플/TOP10,HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (25, 1, '25 news title', 'news content: IT/카카오/[none]', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (26, 1, '26 news title', 'news content: IT/카카오/TOP10', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (27, 1, '27 news title', 'news content: IT/카카오/HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (28, 1, '28 news title', 'news content: IT/카카오/TOP10,HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (29, 1, '29 news title', 'news content: IT/애플/[none]', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (30, 1, '30 news title', 'news content: IT/애플/TOP10', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (31, 1, '31 news title', 'news content: IT/애플/HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (32, 1, '32 news title', 'news content: IT/애플/TOP10,HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (33, 1, '33 news title', 'news content: IT/카카오/[none]', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (34, 1, '34 news title', 'news content: IT/카카오/TOP10', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (35, 1, '35 news title', 'news content: IT/카카오/HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (36, 1, '36 news title', 'news content: IT/카카오/TOP10,HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (37, 2, '37 news title', 'news content: 커머스/애플/[none]', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (38, 2, '38 news title', 'news content: 커머스/애플/TOP10', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (39, 2, '39 news title', 'news content: 커머스/애플/HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (40, 2, '40 news title', 'news content: 커머스/애플/TOP10,HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (41, 2, '41 news title', 'news content: 커머스/카카오/[none]', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (42, 2, '42 news title', 'news content: 커머스/카카오/TOP10', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (43, 2, '43 news title', 'news content: 커머스/카카오/HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (44, 2, '44 news title', 'news content: 커머스/카카오/TOP10,HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (45, 2, '45 news title', 'news content: 커머스/애플/[none]', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (46, 2, '46 news title', 'news content: 커머스/애플/TOP10', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (47, 2, '47 news title', 'news content: 커머스/애플/HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');
insert into news(id, category_id, title, content, create_at, modified_at) values (48, 2, '48 news title', 'news content: 커머스/애플/TOP10,HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00');

-- News-Topic 연관관계 테스트 데이터
insert into news_topic(news_id, topic_id) values (1, 1);
insert into news_topic(news_id, topic_id) values (2, 1);
insert into news_topic(news_id, topic_id) values (3, 1);
insert into news_topic(news_id, topic_id) values (4, 1);
insert into news_topic(news_id, topic_id) values (5, 2);
insert into news_topic(news_id, topic_id) values (6, 2);
insert into news_topic(news_id, topic_id) values (7, 2);
insert into news_topic(news_id, topic_id) values (8, 2);
insert into news_topic(news_id, topic_id) values (9, 1);
insert into news_topic(news_id, topic_id) values (9, 2);
insert into news_topic(news_id, topic_id) values (10, 1);
insert into news_topic(news_id, topic_id) values (10, 2);
insert into news_topic(news_id, topic_id) values (11, 1);
insert into news_topic(news_id, topic_id) values (11, 2);
insert into news_topic(news_id, topic_id) values (12, 1);
insert into news_topic(news_id, topic_id) values (12, 2);

insert into news_topic(news_id, topic_id) values (13, 1);
insert into news_topic(news_id, topic_id) values (14, 1);
insert into news_topic(news_id, topic_id) values (15, 1);
insert into news_topic(news_id, topic_id) values (16, 1);
insert into news_topic(news_id, topic_id) values (17, 2);
insert into news_topic(news_id, topic_id) values (18, 2);
insert into news_topic(news_id, topic_id) values (19, 2);
insert into news_topic(news_id, topic_id) values (20, 2);
insert into news_topic(news_id, topic_id) values (21, 1);
insert into news_topic(news_id, topic_id) values (21, 2);
insert into news_topic(news_id, topic_id) values (22, 1);
insert into news_topic(news_id, topic_id) values (22, 2);
insert into news_topic(news_id, topic_id) values (23, 1);
insert into news_topic(news_id, topic_id) values (23, 2);
insert into news_topic(news_id, topic_id) values (24, 1);
insert into news_topic(news_id, topic_id) values (24, 2);

insert into news_topic(news_id, topic_id) values (25, 1);
insert into news_topic(news_id, topic_id) values (26, 1);
insert into news_topic(news_id, topic_id) values (27, 1);
insert into news_topic(news_id, topic_id) values (28, 1);
insert into news_topic(news_id, topic_id) values (29, 2);
insert into news_topic(news_id, topic_id) values (30, 2);
insert into news_topic(news_id, topic_id) values (31, 2);
insert into news_topic(news_id, topic_id) values (32, 2);
insert into news_topic(news_id, topic_id) values (33, 1);
insert into news_topic(news_id, topic_id) values (33, 2);
insert into news_topic(news_id, topic_id) values (34, 1);
insert into news_topic(news_id, topic_id) values (34, 2);
insert into news_topic(news_id, topic_id) values (35, 1);
insert into news_topic(news_id, topic_id) values (35, 2);
insert into news_topic(news_id, topic_id) values (36, 1);
insert into news_topic(news_id, topic_id) values (36, 2);

insert into news_topic(news_id, topic_id) values (37, 1);
insert into news_topic(news_id, topic_id) values (38, 1);
insert into news_topic(news_id, topic_id) values (39, 1);
insert into news_topic(news_id, topic_id) values (40, 1);
insert into news_topic(news_id, topic_id) values (41, 2);
insert into news_topic(news_id, topic_id) values (42, 2);
insert into news_topic(news_id, topic_id) values (43, 2);
insert into news_topic(news_id, topic_id) values (44, 2);
insert into news_topic(news_id, topic_id) values (45, 1);
insert into news_topic(news_id, topic_id) values (45, 2);
insert into news_topic(news_id, topic_id) values (46, 1);
insert into news_topic(news_id, topic_id) values (46, 2);
insert into news_topic(news_id, topic_id) values (47, 1);
insert into news_topic(news_id, topic_id) values (47, 2);
insert into news_topic(news_id, topic_id) values (48, 1);
insert into news_topic(news_id, topic_id) values (48, 2);


-- News-Tag 연관관계 테스트 데이터
insert into news_tag(news_id, tag_id) values (2, 1);
insert into news_tag(news_id, tag_id) values (3, 2);
insert into news_tag(news_id, tag_id) values (4, 1);
insert into news_tag(news_id, tag_id) values (4, 2);

insert into news_tag(news_id, tag_id) values (6, 1);
insert into news_tag(news_id, tag_id) values (7, 2);
insert into news_tag(news_id, tag_id) values (8, 1);
insert into news_tag(news_id, tag_id) values (8, 2);

insert into news_tag(news_id, tag_id) values (10, 1);
insert into news_tag(news_id, tag_id) values (11, 2);
insert into news_tag(news_id, tag_id) values (12, 3);
insert into news_tag(news_id, tag_id) values (12, 2);

insert into news_tag(news_id, tag_id) values (14, 1);
insert into news_tag(news_id, tag_id) values (15, 2);
insert into news_tag(news_id, tag_id) values (16, 1);
insert into news_tag(news_id, tag_id) values (16, 2);

insert into news_tag(news_id, tag_id) values (18, 1);
insert into news_tag(news_id, tag_id) values (19, 2);
insert into news_tag(news_id, tag_id) values (20, 1);
insert into news_tag(news_id, tag_id) values (20, 2);

insert into news_tag(news_id, tag_id) values (22, 1);
insert into news_tag(news_id, tag_id) values (23, 2);
insert into news_tag(news_id, tag_id) values (24, 1);
insert into news_tag(news_id, tag_id) values (24, 2);

insert into news_tag(news_id, tag_id) values (26, 1);
insert into news_tag(news_id, tag_id) values (27, 2);
insert into news_tag(news_id, tag_id) values (28, 1);
insert into news_tag(news_id, tag_id) values (28, 2);

insert into news_tag(news_id, tag_id) values (30, 1);
insert into news_tag(news_id, tag_id) values (31, 2);
insert into news_tag(news_id, tag_id) values (32, 1);
insert into news_tag(news_id, tag_id) values (33, 2);

insert into news_tag(news_id, tag_id) values (34, 1);
insert into news_tag(news_id, tag_id) values (35, 2);
insert into news_tag(news_id, tag_id) values (36, 3);
insert into news_tag(news_id, tag_id) values (36, 2);

insert into news_tag(news_id, tag_id) values (38, 1);
insert into news_tag(news_id, tag_id) values (39, 2);
insert into news_tag(news_id, tag_id) values (40, 1);
insert into news_tag(news_id, tag_id) values (40, 2);

insert into news_tag(news_id, tag_id) values (42, 1);
insert into news_tag(news_id, tag_id) values (43, 2);
insert into news_tag(news_id, tag_id) values (44, 1);
insert into news_tag(news_id, tag_id) values (44, 2);

insert into news_tag(news_id, tag_id) values (46, 1);
insert into news_tag(news_id, tag_id) values (47, 2);
insert into news_tag(news_id, tag_id) values (48, 1);
insert into news_tag(news_id, tag_id) values (48, 2);