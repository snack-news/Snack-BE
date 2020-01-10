-- Category 테스트 데이터
insert into category (id, title) values (1, 'IT');
insert into category (id, title) values (2, '커머스');

-- Topic 테스트 데이터
insert into topic (id, type, name) values (1, 'CORP', '카카오');
insert into topic (id, type, name) values (2, 'CORP', '애플');
insert into topic (id, type, name) values (3, 'PERSON', '이재용');

-- Tag 테스트 데이터
insert into tag(id, title) values (1,'TOP10');
insert into tag(id, title) values (2,'HOT');

-- News 테스트 데이터
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 1, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:01');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 2, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:02');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 3, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:03');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 4, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:04');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 5, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:05');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 6, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:06');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 7, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:07');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 8, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:08');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 9, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:09');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 10, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:10');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 11, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:11');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 12, 1, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:12');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 13, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:13');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 14, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:14');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 15, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:15');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 16, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:16');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 17, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:17');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 18, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:18');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 19, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:19');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 20, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:20');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 21, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:21');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 22, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:22');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 23, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:23');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 24, 2, '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:24');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 25, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:01');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 26, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:02');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 27, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:03');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 28, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:04');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 29, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:05');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 30, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:06');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 31, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:07');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 32, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:08');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 33, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:09');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 34, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:10');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 35, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:11');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 36, 1, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:12');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 37, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:13');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 38, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:14');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 39, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:15');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 40, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:16');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 41, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:17');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 42, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:18');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 43, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:19');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 44, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:20');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 45, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:21');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 46, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:22');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 47, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:23');
insert into post(type, id, category_id, create_at, modified_at, publish_at) values ('news', 48, 2, '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:24');

insert into news(id, title, content) values (1, '01 news title', 'news content: IT/카카오/[none]');
insert into news(id, title, content) values (2, '04 news title', 'news content: IT/카카오/TOP10');
insert into news(id, title, content) values (3, '05 news title', 'news content: IT/카카오/HOT');
insert into news(id, title, content) values (4, '06 news title', 'news content: IT/카카오/TOP10,HOT');
insert into news(id, title, content) values (5, '07 news title', 'news content: IT/애플/[none]');
insert into news(id, title, content) values (6, '08 news title', 'news content: IT/애플/TOP10');
insert into news(id, title, content) values (7, '09 news title', 'news content: IT/애플/HOT');
insert into news(id, title, content) values (8, '02 news title', 'news content: IT/애플/TOP10,HOT');
insert into news(id, title, content) values (9, '03 news title', 'news content: IT/카카오/[none]');
insert into news(id, title, content) values (10, '10 news title', 'news content: IT/카카오/TOP10');
insert into news(id, title, content) values (11, '11 news title', 'news content: IT/카카오/HOT');
insert into news(id, title, content) values (12, '12 news title', 'news content: IT/카카오/TOP10,HOT');
insert into news(id, title, content) values (13, '13 news title', 'news content: 커머스/애플/[none]');
insert into news(id, title, content) values (14, '14 news title', 'news content: 커머스/애플/TOP10');
insert into news(id, title, content) values (15, '15 news title', 'news content: 커머스/애플/HOT');
insert into news(id, title, content) values (16, '16 news title', 'news content: 커머스/애플/TOP10,HOT');
insert into news(id, title, content) values (17, '17 news title', 'news content: 커머스/카카오/[none]');
insert into news(id, title, content) values (18, '18 news title', 'news content: 커머스/카카오/TOP10');
insert into news(id, title, content) values (19, '19 news title', 'news content: 커머스/카카오/HOT');
insert into news(id, title, content) values (20, '20 news title', 'news content: 커머스/카카오/TOP10,HOT');
insert into news(id, title, content) values (21, '21 news title', 'news content: 커머스/애플/[none]');
insert into news(id, title, content) values (22, '22 news title', 'news content: 커머스/애플/TOP10');
insert into news(id, title, content) values (23, '23 news title', 'news content: 커머스/애플/HOT');
insert into news(id, title, content) values (24, '24 news title', 'news content: 커머스/애플/TOP10,HOT');
insert into news(id, title, content) values (25, '25 news title', 'news content: IT/카카오/[none]');
insert into news(id, title, content) values (26, '26 news title', 'news content: IT/카카오/TOP10');
insert into news(id, title, content) values (27, '27 news title', 'news content: IT/카카오/HOT');
insert into news(id, title, content) values (28, '28 news title', 'news content: IT/카카오/TOP10,HOT');
insert into news(id, title, content) values (29, '29 news title', 'news content: IT/애플/[none]');
insert into news(id, title, content) values (30, '30 news title', 'news content: IT/애플/TOP10');
insert into news(id, title, content) values (31, '31 news title', 'news content: IT/애플/HOT');
insert into news(id, title, content) values (32, '32 news title', 'news content: IT/애플/TOP10,HOT');
insert into news(id, title, content) values (33, '33 news title', 'news content: IT/카카오/[none]');
insert into news(id, title, content) values (34, '34 news title', 'news content: IT/카카오/TOP10');
insert into news(id, title, content) values (35, '35 news title', 'news content: IT/카카오/HOT');
insert into news(id, title, content) values (36, '36 news title', 'news content: IT/카카오/TOP10,HOT');
insert into news(id, title, content) values (37, '37 news title', 'news content: 커머스/애플/[none]');
insert into news(id, title, content) values (38, '38 news title', 'news content: 커머스/애플/TOP10');
insert into news(id, title, content) values (39, '39 news title', 'news content: 커머스/애플/HOT');
insert into news(id, title, content) values (40, '40 news title', 'news content: 커머스/애플/TOP10,HOT');
insert into news(id, title, content) values (41, '41 news title', 'news content: 커머스/카카오/[none]');
insert into news(id, title, content) values (42, '42 news title', 'news content: 커머스/카카오/TOP10');
insert into news(id, title, content) values (43, '43 news title', 'news content: 커머스/카카오/HOT');
insert into news(id, title, content) values (44, '44 news title', 'news content: 커머스/카카오/TOP10,HOT');
insert into news(id, title, content) values (45, '45 news title', 'news content: 커머스/애플/[none]');
insert into news(id, title, content) values (46, '46 news title', 'news content: 커머스/애플/TOP10');
insert into news(id, title, content) values (47, '47 news title', 'news content: 커머스/애플/HOT');
insert into news(id, title, content) values (48, '48 news title', 'news content: 커머스/애플/TOP10,HOT');

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
insert into news_tag(news_id, tag_id) values (12, 2);
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
insert into news_tag(news_id, tag_id) values (36, 1);
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

insert into pick(id, link, category_id, publish_at) values (1, '픽스 링크 - A', 1, '2020-01-02 00:00:00');
insert into pick(id, link, category_id, publish_at) values (2, '픽스 링크 - B', 1, '2020-01-01 00:00:00');
insert into pick(id, link, category_id, publish_at) values (3, '픽스 링크 - C', 2, '2020-01-03 00:00:00');

insert into pick_topic(pick_id, topic_id) values (1, 1);
insert into pick_topic(pick_id, topic_id) values (1, 2);
insert into pick_topic(pick_id, topic_id) values (2, 2);