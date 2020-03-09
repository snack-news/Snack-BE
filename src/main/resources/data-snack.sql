-- Category 테스트 데이터
insert into category (id, title) values (1, 'IT');
insert into category (id, title) values (2, '커머스');

-- Topic 테스트 데이터
insert into topic (id, type, name) values (1, 'CORP', '쿠팡');
insert into topic (id, type, name) values (2, 'CORP', '애플');
insert into topic (id, type, name) values (3, 'PERSON', '이재용');

-- Tag 테스트 데이터
insert into tag(id, title) values (1,'TOP10');
insert into tag(id, title) values (2,'HOT');

-- News 테스트 데이터
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (1, 1, '01 news title', '[{"type":"paragraph" }]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:01');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (2, 1, '04 news title', '[{"type":"paragraph","data":{"text":"- 쿠팡이 Software Engineer  신입 정규직 공개채용을 진행함. "}},{"type":"paragraph","data":{"text":"- 이번 공채는 자바, 안드로이드, iOS 분야라고 함."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:02');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (3, 1, '05 news title', '[{"type":"paragraph","data":{"text":"- 쿠팡이 신형 화물차를 도입한 배경은 낱개상품 포장 적재 효율을 극대화하기 위해서라고 함."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:03');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (4, 1, '06 news title', '[{"type":"paragraph","data":{"text":"- 쏘카 관계자는 "차량을 직접 소유하지 않고 빌려 쓰는 공유 모델은 글로벌뿐 아니라 한국에도 30대를 중심으로 급속하게 확산되고 있다" "}},{"type":"paragraph","data":{"text":"- "차량구독 서비스 `쏘카 패스`, 장기 대여 차량 공유 `쏘카 페어링` 등 서비스 이용률이 높아지면서 국내도 자차 공유 트렌드가 생기고 있다"라고 밝힘."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:04');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (5, 1, '07 news title', '[{"type":"paragraph","data":{"text":"- 쿠팡이 Software Engineer  신입 정규직 공개채용을 진행함. "}},{"type":"paragraph","data":{"text":"- 이번 공채는 자바, 안드로이드, iOS 분야라고 함."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:05');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (6, 1, '08 news title', '[{"type":"paragraph","data":{"text":"- 쿠팡이 신형 화물차를 도입한 배경은 낱개상품 포장 적재 효율을 극대화하기 위해서라고 함."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:06');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (7, 1, '09 news title', '[{"type":"paragraph","data":{"text":"By 스낵뉴스🙋‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:07');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (8, 1, '02 news title', '[{"type":"paragraph","data":{"text":"- 쿠팡이 신형 화물차를 도입한 배경은 낱개상품 포장 적재 효율을 극대화하기 위해서라고 함."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}],HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:08');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (9, 1, '03 news title', '[{"type":"paragraph","data":{"text":"By 스낵뉴스🙋‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:09');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (10, 1, '10 news title', '[{"type":"paragraph","data":{"text":"- 쿠팡이 신형 화물차를 도입한 배경은 낱개상품 포장 적재 효율을 극대화하기 위해서라고 함."}},{"type":"paragraph","data":{"text":"By 스낵뉴스🙋‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:10');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (11, 1, '11 news title', '[{"type":"paragraph","data":{"text":"1.2020년 첫 유니콘 기업이 상반기 내에 탄생할 것이라는 전망이 나오고 있음."}},{"type":"paragraph","data":{"text":"2.현재 가장 주목하는 기업은 마켓컬리. "}},{"type":"paragraph","data":{"text":"3.컬리는 중소기업벤처부의 예비 유니콘기업에 선정돼 6000억원의 기업가치를 인정받은 바 있음. "}},{"type":"paragraph","data":{"text":"4.쇼핑몰 플랫폼 지그재그도 최근 무신사가 유니콘에 오르는 등 패션 산업에서 유의미한 성장을 하고 있기에 유니콘이 될 가능성이 나오고 있음."}},{"type":"paragraph","data":{"text":"현재 정부 차원에서 스타트업을 유니콘 으로 성장시키는데 많은 도움을 주겠다고 하는 만큼, "}},{"type":"paragraph","data":{"text":"앞으로 국내에서 유니콘이 더 많이 나올 것 같은 생각이 드네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:11');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (12, 1, '12 news title', '[{"type":"paragraph","data":{"text":"- 쏘카 관계자는 "차량을 직접 소유하지 않고 빌려 쓰는 공유 모델은 글로벌뿐 아니라 한국에도 30대를 중심으로 급속하게 확산되고 있다" "}},{"type":"paragraph","data":{"text":"- "차량구독 서비스 `쏘카 패스`, 장기 대여 차량 공유 `쏘카 페어링` 등 서비스 이용률이 높아지면서 국내도 자차 공유 트렌드가 생기고 있다"라고 밝힘."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:12');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (13, 2, '13 news title', '[{"type":"paragraph","data":{"text":"1.최근 배달의민족, 요기요 등 배달 앱 서비스 기업은 식품/비식품 배달 서비스를 확대하는 중."}},{"type":"paragraph","data":{"text":"2.특히 배달앱 1위 우아한형제들은 작년 12월부터 B마트를 시작함. 현재 식품 배달을 요청하면 1시간 이내 물건이 배달되고, 낱개로 구매할 수 있는게 특징. "}},{"type":"paragraph","data":{"text":"3.딜리버리히어로가 운영하는 요기요는 편의점, 마트를 활용해 소량 배달 서비스를 진행 중."}},{"type":"paragraph","data":{"text":"4.딜리버리히어로에 따르면 편의점 배달 서비스 11월 주문량이 7월에 비해 10배 늘어났다고 함."}},{"type":"paragraph","data":{"text":"5.현재 소량 배달 서비스가 앞으로 더 커질지, 한계점에서 그칠지는 귀추가 주목됨."}},{"type":"paragraph","data":{"text":"날이 갈 수록 배달 니즈는 커지고 있습니다. "}},{"type":"paragraph","data":{"text":"소량 배달 서비스들은 자취생들의 수요를 충분히 흡수할 수 있어보이는데요."}},{"type":"paragraph","data":{"text":"다만 서비스가 성장하려면 파이를 늘려야되고, 정기적으로 장을 보는 사람들까지 끌어와야 될텐데 쉽지는 않아보이네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:13');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (14, 2, '14 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:14');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (15, 2, '15 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}],HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:15');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (16, 2, '16 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}],HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:16');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (17, 2, '17 news title', '[{"type":"paragraph","data":{"text":"- 쏘카 관계자는 "차량을 직접 소유하지 않고 빌려 쓰는 공유 모델은 글로벌뿐 아니라 한국에도 30대를 중심으로 급속하게 확산되고 있다" "}},{"type":"paragraph","data":{"text":"- "차량구독 서비스 `쏘카 패스`, 장기 대여 차량 공유 `쏘카 페어링` 등 서비스 이용률이 높아지면서 국내도 자차 공유 트렌드가 생기고 있다"라고 밝힘."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:17');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (18, 2, '18 news title', '[{"type":"paragraph","data":{"text":"1.2020년 첫 유니콘 기업이 상반기 내에 탄생할 것이라는 전망이 나오고 있음."}},{"type":"paragraph","data":{"text":"2.현재 가장 주목하는 기업은 마켓컬리. "}},{"type":"paragraph","data":{"text":"3.컬리는 중소기업벤처부의 예비 유니콘기업에 선정돼 6000억원의 기업가치를 인정받은 바 있음. "}},{"type":"paragraph","data":{"text":"4.쇼핑몰 플랫폼 지그재그도 최근 무신사가 유니콘에 오르는 등 패션 산업에서 유의미한 성장을 하고 있기에 유니콘이 될 가능성이 나오고 있음."}},{"type":"paragraph","data":{"text":"현재 정부 차원에서 스타트업을 유니콘 으로 성장시키는데 많은 도움을 주겠다고 하는 만큼, "}},{"type":"paragraph","data":{"text":"앞으로 국내에서 유니콘이 더 많이 나올 것 같은 생각이 드네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:18');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (19, 2, '19 news title', '[{"type":"paragraph","data":{"text":"1.최근 배달의민족, 요기요 등 배달 앱 서비스 기업은 식품/비식품 배달 서비스를 확대하는 중."}},{"type":"paragraph","data":{"text":"2.특히 배달앱 1위 우아한형제들은 작년 12월부터 B마트를 시작함. 현재 식품 배달을 요청하면 1시간 이내 물건이 배달되고, 낱개로 구매할 수 있는게 특징. "}},{"type":"paragraph","data":{"text":"3.딜리버리히어로가 운영하는 요기요는 편의점, 마트를 활용해 소량 배달 서비스를 진행 중."}},{"type":"paragraph","data":{"text":"4.딜리버리히어로에 따르면 편의점 배달 서비스 11월 주문량이 7월에 비해 10배 늘어났다고 함."}},{"type":"paragraph","data":{"text":"5.현재 소량 배달 서비스가 앞으로 더 커질지, 한계점에서 그칠지는 귀추가 주목됨."}},{"type":"paragraph","data":{"text":"날이 갈 수록 배달 니즈는 커지고 있습니다. "}},{"type":"paragraph","data":{"text":"소량 배달 서비스들은 자취생들의 수요를 충분히 흡수할 수 있어보이는데요."}},{"type":"paragraph","data":{"text":"다만 서비스가 성장하려면 파이를 늘려야되고, 정기적으로 장을 보는 사람들까지 끌어와야 될텐데 쉽지는 않아보이네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:19');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (20, 2, '20 news title', '[{"type":"paragraph","data":{"text":"1.2020년 첫 유니콘 기업이 상반기 내에 탄생할 것이라는 전망이 나오고 있음."}},{"type":"paragraph","data":{"text":"2.현재 가장 주목하는 기업은 마켓컬리. "}},{"type":"paragraph","data":{"text":"3.컬리는 중소기업벤처부의 예비 유니콘기업에 선정돼 6000억원의 기업가치를 인정받은 바 있음. "}},{"type":"paragraph","data":{"text":"4.쇼핑몰 플랫폼 지그재그도 최근 무신사가 유니콘에 오르는 등 패션 산업에서 유의미한 성장을 하고 있기에 유니콘이 될 가능성이 나오고 있음."}},{"type":"paragraph","data":{"text":"현재 정부 차원에서 스타트업을 유니콘 으로 성장시키는데 많은 도움을 주겠다고 하는 만큼, "}},{"type":"paragraph","data":{"text":"앞으로 국내에서 유니콘이 더 많이 나올 것 같은 생각이 드네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}],HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:20');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (21, 2, '21 news title', '[{"type":"paragraph","data":{"text":"1.최근 배달의민족, 요기요 등 배달 앱 서비스 기업은 식품/비식품 배달 서비스를 확대하는 중."}},{"type":"paragraph","data":{"text":"2.특히 배달앱 1위 우아한형제들은 작년 12월부터 B마트를 시작함. 현재 식품 배달을 요청하면 1시간 이내 물건이 배달되고, 낱개로 구매할 수 있는게 특징. "}},{"type":"paragraph","data":{"text":"3.딜리버리히어로가 운영하는 요기요는 편의점, 마트를 활용해 소량 배달 서비스를 진행 중."}},{"type":"paragraph","data":{"text":"4.딜리버리히어로에 따르면 편의점 배달 서비스 11월 주문량이 7월에 비해 10배 늘어났다고 함."}},{"type":"paragraph","data":{"text":"5.현재 소량 배달 서비스가 앞으로 더 커질지, 한계점에서 그칠지는 귀추가 주목됨."}},{"type":"paragraph","data":{"text":"날이 갈 수록 배달 니즈는 커지고 있습니다. "}},{"type":"paragraph","data":{"text":"소량 배달 서비스들은 자취생들의 수요를 충분히 흡수할 수 있어보이는데요."}},{"type":"paragraph","data":{"text":"다만 서비스가 성장하려면 파이를 늘려야되고, 정기적으로 장을 보는 사람들까지 끌어와야 될텐데 쉽지는 않아보이네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:21');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (22, 2, '22 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}]', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:22');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (23, 2, '23 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}],HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:23');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (24, 2, '24 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}],HOT', '2019-07-21 00:00:00', '2019-07-21 00:00:00', '2019-11-25 00:00:24');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (25, 1, '25 news title', '[{"type":"paragraph","data":{"text":"By 스낵뉴스🙋‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:01');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (26, 1, '26 news title', '[{"type":"paragraph","data":{"text":"- 쿠팡이 신형 화물차를 도입한 배경은 낱개상품 포장 적재 효율을 극대화하기 위해서라고 함."}},{"type":"paragraph","data":{"text":"By 스낵뉴스🙋‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:02');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (27, 1, '27 news title', '[{"type":"paragraph","data":{"text":"1.2020년 첫 유니콘 기업이 상반기 내에 탄생할 것이라는 전망이 나오고 있음."}},{"type":"paragraph","data":{"text":"2.현재 가장 주목하는 기업은 마켓컬리. "}},{"type":"paragraph","data":{"text":"3.컬리는 중소기업벤처부의 예비 유니콘기업에 선정돼 6000억원의 기업가치를 인정받은 바 있음. "}},{"type":"paragraph","data":{"text":"4.쇼핑몰 플랫폼 지그재그도 최근 무신사가 유니콘에 오르는 등 패션 산업에서 유의미한 성장을 하고 있기에 유니콘이 될 가능성이 나오고 있음."}},{"type":"paragraph","data":{"text":"현재 정부 차원에서 스타트업을 유니콘 으로 성장시키는데 많은 도움을 주겠다고 하는 만큼, "}},{"type":"paragraph","data":{"text":"앞으로 국내에서 유니콘이 더 많이 나올 것 같은 생각이 드네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:03');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (28, 1, '28 news title', '[{"type":"paragraph","data":{"text":"- 쏘카 관계자는 "차량을 직접 소유하지 않고 빌려 쓰는 공유 모델은 글로벌뿐 아니라 한국에도 30대를 중심으로 급속하게 확산되고 있다" "}},{"type":"paragraph","data":{"text":"- "차량구독 서비스 `쏘카 패스`, 장기 대여 차량 공유 `쏘카 페어링` 등 서비스 이용률이 높아지면서 국내도 자차 공유 트렌드가 생기고 있다"라고 밝힘."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:04');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (29, 1, '29 news title', '[{"type":"paragraph","data":{"text":"- 쿠팡이 Software Engineer  신입 정규직 공개채용을 진행함. "}},{"type":"paragraph","data":{"text":"- 이번 공채는 자바, 안드로이드, iOS 분야라고 함."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:05');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (30, 1, '30 news title', '[{"type":"paragraph","data":{"text":"- 쿠팡이 신형 화물차를 도입한 배경은 낱개상품 포장 적재 효율을 극대화하기 위해서라고 함."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:06');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (31, 1, '31 news title', '[{"type":"paragraph","data":{"text":"By 스낵뉴스🙋‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:07');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (32, 1, '32 news title', '[{"type":"paragraph","data":{"text":"- 쿠팡이 신형 화물차를 도입한 배경은 낱개상품 포장 적재 효율을 극대화하기 위해서라고 함."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}],HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:08');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (33, 1, '33 news title', '[{"type":"paragraph","data":{"text":"By 스낵뉴스🙋‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:09');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (34, 1, '34 news title', '[{"type":"paragraph","data":{"text":"- 쿠팡이 신형 화물차를 도입한 배경은 낱개상품 포장 적재 효율을 극대화하기 위해서라고 함."}},{"type":"paragraph","data":{"text":"By 스낵뉴스🙋‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:10');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (35, 1, '35 news title', '[{"type":"paragraph","data":{"text":"1.2020년 첫 유니콘 기업이 상반기 내에 탄생할 것이라는 전망이 나오고 있음."}},{"type":"paragraph","data":{"text":"2.현재 가장 주목하는 기업은 마켓컬리. "}},{"type":"paragraph","data":{"text":"3.컬리는 중소기업벤처부의 예비 유니콘기업에 선정돼 6000억원의 기업가치를 인정받은 바 있음. "}},{"type":"paragraph","data":{"text":"4.쇼핑몰 플랫폼 지그재그도 최근 무신사가 유니콘에 오르는 등 패션 산업에서 유의미한 성장을 하고 있기에 유니콘이 될 가능성이 나오고 있음."}},{"type":"paragraph","data":{"text":"현재 정부 차원에서 스타트업을 유니콘 으로 성장시키는데 많은 도움을 주겠다고 하는 만큼, "}},{"type":"paragraph","data":{"text":"앞으로 국내에서 유니콘이 더 많이 나올 것 같은 생각이 드네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:11');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (36, 1, '36 news title', '[{"type":"paragraph","data":{"text":"- 쏘카 관계자는 "차량을 직접 소유하지 않고 빌려 쓰는 공유 모델은 글로벌뿐 아니라 한국에도 30대를 중심으로 급속하게 확산되고 있다" "}},{"type":"paragraph","data":{"text":"- "차량구독 서비스 `쏘카 패스`, 장기 대여 차량 공유 `쏘카 페어링` 등 서비스 이용률이 높아지면서 국내도 자차 공유 트렌드가 생기고 있다"라고 밝힘."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:10');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (37, 2, '37 news title', '[{"type":"paragraph","data":{"text":"1.최근 배달의민족, 요기요 등 배달 앱 서비스 기업은 식품/비식품 배달 서비스를 확대하는 중."}},{"type":"paragraph","data":{"text":"2.특히 배달앱 1위 우아한형제들은 작년 12월부터 B마트를 시작함. 현재 식품 배달을 요청하면 1시간 이내 물건이 배달되고, 낱개로 구매할 수 있는게 특징. "}},{"type":"paragraph","data":{"text":"3.딜리버리히어로가 운영하는 요기요는 편의점, 마트를 활용해 소량 배달 서비스를 진행 중."}},{"type":"paragraph","data":{"text":"4.딜리버리히어로에 따르면 편의점 배달 서비스 11월 주문량이 7월에 비해 10배 늘어났다고 함."}},{"type":"paragraph","data":{"text":"5.현재 소량 배달 서비스가 앞으로 더 커질지, 한계점에서 그칠지는 귀추가 주목됨."}},{"type":"paragraph","data":{"text":"날이 갈 수록 배달 니즈는 커지고 있습니다. "}},{"type":"paragraph","data":{"text":"소량 배달 서비스들은 자취생들의 수요를 충분히 흡수할 수 있어보이는데요."}},{"type":"paragraph","data":{"text":"다만 서비스가 성장하려면 파이를 늘려야되고, 정기적으로 장을 보는 사람들까지 끌어와야 될텐데 쉽지는 않아보이네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:10');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (38, 2, '38 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:10');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (39, 2, '39 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}],HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:10');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (40, 2, '40 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}],HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:10');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (41, 2, '41 news title', '[{"type":"paragraph","data":{"text":"- 쏘카 관계자는 "차량을 직접 소유하지 않고 빌려 쓰는 공유 모델은 글로벌뿐 아니라 한국에도 30대를 중심으로 급속하게 확산되고 있다" "}},{"type":"paragraph","data":{"text":"- "차량구독 서비스 `쏘카 패스`, 장기 대여 차량 공유 `쏘카 페어링` 등 서비스 이용률이 높아지면서 국내도 자차 공유 트렌드가 생기고 있다"라고 밝힘."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:17');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (42, 2, '42 news title', '[{"type":"paragraph","data":{"text":"1.2020년 첫 유니콘 기업이 상반기 내에 탄생할 것이라는 전망이 나오고 있음."}},{"type":"paragraph","data":{"text":"2.현재 가장 주목하는 기업은 마켓컬리. "}},{"type":"paragraph","data":{"text":"3.컬리는 중소기업벤처부의 예비 유니콘기업에 선정돼 6000억원의 기업가치를 인정받은 바 있음. "}},{"type":"paragraph","data":{"text":"4.쇼핑몰 플랫폼 지그재그도 최근 무신사가 유니콘에 오르는 등 패션 산업에서 유의미한 성장을 하고 있기에 유니콘이 될 가능성이 나오고 있음."}},{"type":"paragraph","data":{"text":"현재 정부 차원에서 스타트업을 유니콘 으로 성장시키는데 많은 도움을 주겠다고 하는 만큼, "}},{"type":"paragraph","data":{"text":"앞으로 국내에서 유니콘이 더 많이 나올 것 같은 생각이 드네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:18');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (43, 2, '43 news title', '[{"type":"paragraph","data":{"text":"1.최근 배달의민족, 요기요 등 배달 앱 서비스 기업은 식품/비식품 배달 서비스를 확대하는 중."}},{"type":"paragraph","data":{"text":"2.특히 배달앱 1위 우아한형제들은 작년 12월부터 B마트를 시작함. 현재 식품 배달을 요청하면 1시간 이내 물건이 배달되고, 낱개로 구매할 수 있는게 특징. "}},{"type":"paragraph","data":{"text":"3.딜리버리히어로가 운영하는 요기요는 편의점, 마트를 활용해 소량 배달 서비스를 진행 중."}},{"type":"paragraph","data":{"text":"4.딜리버리히어로에 따르면 편의점 배달 서비스 11월 주문량이 7월에 비해 10배 늘어났다고 함."}},{"type":"paragraph","data":{"text":"5.현재 소량 배달 서비스가 앞으로 더 커질지, 한계점에서 그칠지는 귀추가 주목됨."}},{"type":"paragraph","data":{"text":"날이 갈 수록 배달 니즈는 커지고 있습니다. "}},{"type":"paragraph","data":{"text":"소량 배달 서비스들은 자취생들의 수요를 충분히 흡수할 수 있어보이는데요."}},{"type":"paragraph","data":{"text":"다만 서비스가 성장하려면 파이를 늘려야되고, 정기적으로 장을 보는 사람들까지 끌어와야 될텐데 쉽지는 않아보이네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:19');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (44, 2, '44 news title', '[{"type":"paragraph","data":{"text":"1.2020년 첫 유니콘 기업이 상반기 내에 탄생할 것이라는 전망이 나오고 있음."}},{"type":"paragraph","data":{"text":"2.현재 가장 주목하는 기업은 마켓컬리. "}},{"type":"paragraph","data":{"text":"3.컬리는 중소기업벤처부의 예비 유니콘기업에 선정돼 6000억원의 기업가치를 인정받은 바 있음. "}},{"type":"paragraph","data":{"text":"4.쇼핑몰 플랫폼 지그재그도 최근 무신사가 유니콘에 오르는 등 패션 산업에서 유의미한 성장을 하고 있기에 유니콘이 될 가능성이 나오고 있음."}},{"type":"paragraph","data":{"text":"현재 정부 차원에서 스타트업을 유니콘 으로 성장시키는데 많은 도움을 주겠다고 하는 만큼, "}},{"type":"paragraph","data":{"text":"앞으로 국내에서 유니콘이 더 많이 나올 것 같은 생각이 드네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}],HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:20');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (45, 2, '45 news title', '[{"type":"paragraph","data":{"text":"1.최근 배달의민족, 요기요 등 배달 앱 서비스 기업은 식품/비식품 배달 서비스를 확대하는 중."}},{"type":"paragraph","data":{"text":"2.특히 배달앱 1위 우아한형제들은 작년 12월부터 B마트를 시작함. 현재 식품 배달을 요청하면 1시간 이내 물건이 배달되고, 낱개로 구매할 수 있는게 특징. "}},{"type":"paragraph","data":{"text":"3.딜리버리히어로가 운영하는 요기요는 편의점, 마트를 활용해 소량 배달 서비스를 진행 중."}},{"type":"paragraph","data":{"text":"4.딜리버리히어로에 따르면 편의점 배달 서비스 11월 주문량이 7월에 비해 10배 늘어났다고 함."}},{"type":"paragraph","data":{"text":"5.현재 소량 배달 서비스가 앞으로 더 커질지, 한계점에서 그칠지는 귀추가 주목됨."}},{"type":"paragraph","data":{"text":"날이 갈 수록 배달 니즈는 커지고 있습니다. "}},{"type":"paragraph","data":{"text":"소량 배달 서비스들은 자취생들의 수요를 충분히 흡수할 수 있어보이는데요."}},{"type":"paragraph","data":{"text":"다만 서비스가 성장하려면 파이를 늘려야되고, 정기적으로 장을 보는 사람들까지 끌어와야 될텐데 쉽지는 않아보이네요."}},{"type":"paragraph","data":{"text":"(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요😊)"}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:21');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (46, 2, '46 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}]', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:22');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (47, 2, '47 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}],HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:23');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (48, 2, '48 news title', '[{"type":"paragraph","data":{"text":"1.와이즈앱 조사 결과에 따르면 2019년 한국인이 가장 많이 결제한 커머스 서비스는 옥션,G마켓,G9을 운영하는 이베이코리아로 15조6000억원 규모."}},{"type":"paragraph","data":{"text":"2.쿠팡은 15조3000억원으로 2위까지 치고 올라옴."}},{"type":"paragraph","data":{"text":"3.3위인 11번가가 9조 정도인걸 보면 격차를 많이 벌린 것으로 보임."}},{"type":"paragraph","data":{"text":"4.4위 위메프 5조7000억원,5위는 배달의민족이 5조2000억원,규모로 순위에 랭크."}},{"type":"paragraph","data":{"text":"쇼핑의 강자 네이버는 기록되지않았지만 추정금액은 19조 원 정도라고 하네요."}},{"type":"paragraph","data":{"text":"아무튼 역시 쿠팡의 성장세는 대단합니다.앞으로는 네이버랑 쿠팡 둘이서 다 할 것 같네요."}}],HOT', '2019-08-21 00:00:00', '2019-08-21 00:00:00', '2019-12-21 00:00:24');
insert into news(id, category_id, title, content, create_at, modified_at, publish_at) values (49, 2, '데르프의 테스트', '[{"type":"paragraph","data":{"text":"- 쏘카 관계자는 "차량을 직접 소유하지 않고 빌려 쓰는 공유 모델은 글로벌뿐 아니라 한국에도 30대를 중심으로 급속하게 확산되고 있다" "}},{"type":"paragraph","data":{"text":"- "차량구독 서비스 `쏘카 패스`, 장기 대여 차량 공유 `쏘카 페어링` 등 서비스 이용률이 높아지면서 국내도 자차 공유 트렌드가 생기고 있다"라고 밝힘."}},{"type":"paragraph","data":{"text":"By 스낵뉴스\uDE4B‍️(이 뉴스가 유용할 친구에게 이 뉴스를 공유해보세요)"}}]', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-11-16 00:00:00');

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

insert into pick(id, link, publish_at) values (1, '픽스 링크 - A', '2020-01-02 00:00:01');
insert into pick(id, link, publish_at) values (2, '픽스 링크 - B', '2020-01-01 00:00:00');
insert into pick(id, link, publish_at) values (3, '픽스 링크 - C', '2020-01-03 00:00:00');
insert into pick(id, link, publish_at) values (4, '픽스 링크 - D', '2020-01-04 00:00:00');
insert into pick(id, link, publish_at) values (5, '픽스 링크 - E', '2020-01-06 00:00:00');
insert into pick(id, link, publish_at) values (6, '픽스 링크 - F', '2020-01-05 00:00:00');
insert into pick(id, link, publish_at) values (7, '픽스 링크 - G', '2020-01-07 00:00:00');
insert into pick(id, link, publish_at) values (8, '픽스 링크 - H', '2020-01-09 00:00:00');
insert into pick(id, link, publish_at) values (9, '픽스 링크 - I', '2020-01-08 00:00:00');
insert into pick(id, link, publish_at) values (10, '픽스 링크 - J', '2020-01-11 00:00:00');
insert into pick(id, link, publish_at) values (11, '픽스 링크 - K', '2020-01-10 00:00:00');

insert into pick_topic(pick_id, topic_id) values (1, 1);
insert into pick_topic(pick_id, topic_id) values (1, 2);
insert into pick_topic(pick_id, topic_id) values (2, 2);

INSERT INTO USER (ID, USERNAME, PASSWORD, EMAIL, ACTIVATED) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin@admin.com', 1);
INSERT INTO USER (ID, USERNAME, PASSWORD, EMAIL, ACTIVATED) VALUES (2, 'user', '$2a$10$JKpEgVnESNX7F2p7dhKZB.0F05ZNNlYbMJVcnUge0KpkjHEPWu66a', 'enabled@user.com', 1);

INSERT INTO AUTHORITY (NAME) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (NAME) VALUES ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_USER');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_ADMIN');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) VALUES (2, 'ROLE_USER');