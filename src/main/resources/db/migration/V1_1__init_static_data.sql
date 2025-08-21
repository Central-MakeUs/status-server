-- 능력치 데이터
INSERT INTO attribute (id, type, name, description, bit_mask) VALUES
                                                                                        (101, 'MENTALITY', '인내', '쉽게 포기하지 않고 어려운 일도 끝까지 밀어붙일 수 있는 능력이에요.', 1),
                                                                                        (102, 'MENTALITY', '집중', '한 가지 일에 몰입하고, 방해 요소를 차단할 수 있는 능력이에요.', 2),
                                                                                        (103, 'MENTALITY', '제어', '감정이나 충동을 인식하고 스스로 조절할 수 있는 힘이에요.', 4),
                                                                                        (104, 'MENTALITY', '영감', '새로운 아이디어를 떠올리고 창의적으로 표현할 수 있는 능력이에요.', 8),
                                                                                        (105, 'MENTALITY', '성실', '단정코 반복적인 일도 꾸준히 실천할 수 있는 힘이에요.', 16),
                                                                                        (106, 'MENTALITY', '용기', '두려움과 불확실성을 넘어서 행동할 수 있는 능력이에요.', 32),
                                                                                        (201, 'SKILL', '건강', '몸의 컨디션을 관리하고 신체적 루틴을 실천하는 능력이에요.', 64),
                                                                                        (202, 'SKILL', '전략', '시간, 일정, 우선순위를 스스로 설계하고 조정하는 능력이에요.', 128),
                                                                                        (203, 'SKILL', '기록', '하루의 감정이나 정보를 정리하고 기록하는 능력이에요.', 256),
                                                                                        (204, 'SKILL', '기술', '다양한 디지털 도구를 능숙하게 다루고 활용하는 능력이에요.', 512),
                                                                                        (205, 'SKILL', '화술', '생각이나 감정을 말이나 글로 효과적으로 전달하는 능력이에요.', 1024),
                                                                                        (206, 'SKILL', '탐구', '새로운 지식을 배우고, 스스로 공부하며 정리하는 능력이에요.', 2048);

-- 정신/기술 능력치 경험치 별 레벨 데이터
INSERT INTO attribute_level (level, xp_required, type)
VALUES (1,	8	, 'MENTALITY'),
       (2,	16	, 'MENTALITY'),
       (3,	24	, 'MENTALITY'),
       (4,	32	, 'MENTALITY'),
       (5,	42	, 'MENTALITY'),
       (6,	52	, 'MENTALITY'),
       (7,	67	, 'MENTALITY'),
       (8,	82	, 'MENTALITY'),
       (9,	102	, 'MENTALITY'),
       (10,	122	, 'MENTALITY'),
       (11,	196	, 'MENTALITY'),
       (12,	274	, 'MENTALITY'),
       (13,	356	, 'MENTALITY'),
       (14,	442	, 'MENTALITY'),
       (15,	532	, 'MENTALITY'),
       (16,	626	, 'MENTALITY'),
       (17,	725	, 'MENTALITY'),
       (18,	828	, 'MENTALITY'),
       (19,	935	, 'MENTALITY'),
       (20,	1047	, 'MENTALITY'),
       (21,	1163	, 'MENTALITY'),
       (22,	1283	, 'MENTALITY'),
       (23,	1408	, 'MENTALITY'),
       (24,	1537	, 'MENTALITY'),
       (25,	1670	, 'MENTALITY'),
       (26,	1808	, 'MENTALITY'),
       (27,	1950	, 'MENTALITY'),
       (28,	2097	, 'MENTALITY'),
       (29,	2248	, 'MENTALITY'),
       (30,	2404	, 'MENTALITY'),
       (31,	2564	, 'MENTALITY'),
       (32,	2729	, 'MENTALITY'),
       (33,	2898	, 'MENTALITY'),
       (34,	3072	, 'MENTALITY'),
       (35,	3251	, 'MENTALITY'),
       (36,	3434	, 'MENTALITY'),
       (37,	3622	, 'MENTALITY'),
       (38,	3814	, 'MENTALITY'),
       (39,	4011	, 'MENTALITY'),
       (40,	4213	, 'MENTALITY'),
       (41,	4419	, 'MENTALITY'),
       (42,	4630	, 'MENTALITY'),
       (43,	4845	, 'MENTALITY'),
       (44,	5065	, 'MENTALITY'),
       (45,	5290	, 'MENTALITY'),
       (46,	5519	, 'MENTALITY'),
       (47,	5753	, 'MENTALITY'),
       (48,	5992	, 'MENTALITY'),
       (49,	6235	, 'MENTALITY'),
       (50,	6483	, 'MENTALITY'),
       (51,	6736	, 'MENTALITY'),
       (52,	6994	, 'MENTALITY'),
       (53,	7256	, 'MENTALITY'),
       (54,	7523	, 'MENTALITY'),
       (55,	7795	, 'MENTALITY'),
       (56,	8072	, 'MENTALITY'),
       (57,	8353	, 'MENTALITY'),
       (58,	8639	, 'MENTALITY'),
       (59,	8930	, 'MENTALITY'),
       (60,	9226	, 'MENTALITY'),
       (61,	9526	, 'MENTALITY'),
       (62,	9831	, 'MENTALITY'),
       (63,	10141	, 'MENTALITY'),
       (64,	10456	, 'MENTALITY'),
       (65,	10776	, 'MENTALITY'),
       (66,	11100	, 'MENTALITY'),
       (67,	11429	, 'MENTALITY'),
       (68,	11763	, 'MENTALITY'),
       (69,	12102	, 'MENTALITY'),
       (70,	12446	, 'MENTALITY'),
       (71,	12794	, 'MENTALITY'),
       (72,	13147	, 'MENTALITY'),
       (73,	13505	, 'MENTALITY'),
       (74,	13868	, 'MENTALITY'),
       (75,	14236	, 'MENTALITY'),
       (76,	14609	, 'MENTALITY'),
       (77,	14987	, 'MENTALITY'),
       (78,	15369	, 'MENTALITY'),
       (79,	15756	, 'MENTALITY'),
       (80,	16148	, 'MENTALITY'),
       (81,	16545	, 'MENTALITY'),
       (82,	16947	, 'MENTALITY'),
       (83,	17354	, 'MENTALITY'),
       (84,	17766	, 'MENTALITY'),
       (85,	18183	, 'MENTALITY'),
       (86,	18605	, 'MENTALITY'),
       (87,	19032	, 'MENTALITY'),
       (88,	19463	, 'MENTALITY'),
       (89,	19899	, 'MENTALITY'),
       (90,	20340	, 'MENTALITY'),
       (91,	20786	, 'MENTALITY'),
       (92,	21237	, 'MENTALITY'),
       (93,	21693	, 'MENTALITY'),
       (94,	22154	, 'MENTALITY'),
       (95,	22620	, 'MENTALITY'),
       (96,	23091	, 'MENTALITY'),
       (97,	23567	, 'MENTALITY'),
       (98,	24048	, 'MENTALITY'),
       (99,	24534	, 'MENTALITY'),


       (1, 	8	, 'SKILL'),
       (2,	16	, 'SKILL'),
       (3,	24	, 'SKILL'),
       (4,	32	, 'SKILL'),
       (5,	42	, 'SKILL'),
       (6,	52	, 'SKILL'),
       (7,	67	, 'SKILL'),
       (8,	82	, 'SKILL'),
       (9,	102	, 'SKILL'),
       (10,	122	, 'SKILL'),
       (11,	281	, 'SKILL'),
       (12,	446	, 'SKILL'),
       (13,	617	, 'SKILL'),
       (14,	794	, 'SKILL'),
       (15,	977	, 'SKILL'),
       (16,	1166	, 'SKILL'),
       (17,	1361	, 'SKILL'),
       (18,	1562	, 'SKILL'),
       (19,	1769	, 'SKILL'),
       (20,	1982	, 'SKILL'),
       (21,	2202	, 'SKILL'),
       (22,	2428	, 'SKILL'),
       (23,	2660	, 'SKILL'),
       (24,	2899	, 'SKILL'),
       (25,	3144	, 'SKILL'),
       (26,	3396	, 'SKILL'),
       (27,	3654	, 'SKILL'),
       (28,	3919	, 'SKILL'),
       (29,	4190	, 'SKILL'),
       (30,	4468	, 'SKILL'),
       (31,	4753	, 'SKILL'),
       (32,	5044	, 'SKILL'),
       (33,	5342	, 'SKILL'),
       (34,	5647	, 'SKILL'),
       (35,	5958	, 'SKILL'),
       (36,	6276	, 'SKILL'),
       (37,	6601	, 'SKILL'),
       (38,	6933	, 'SKILL'),
       (39,	7271	, 'SKILL'),
       (40,	7616	, 'SKILL'),
       (41,	7968	, 'SKILL'),
       (42,	8327	, 'SKILL'),
       (43,	8693	, 'SKILL'),
       (44,	9066	, 'SKILL'),
       (45,	9445	, 'SKILL'),
       (46,	9831	, 'SKILL'),
       (47,	10224	, 'SKILL'),
       (48,	10624	, 'SKILL'),
       (49,	11031	, 'SKILL'),
       (50,	11445	, 'SKILL'),
       (51,	11866	, 'SKILL'),
       (52,	12294	, 'SKILL'),
       (53,	12729	, 'SKILL'),
       (54,	13171	, 'SKILL'),
       (55,	13620	, 'SKILL'),
       (56,	14076	, 'SKILL'),
       (57,	14539	, 'SKILL'),
       (58,	15009	, 'SKILL'),
       (59,	15486	, 'SKILL'),
       (60,	15970	, 'SKILL'),
       (61,	16461	, 'SKILL'),
       (62,	16959	, 'SKILL'),
       (63,	17465	, 'SKILL'),
       (64,	17978	, 'SKILL'),
       (65,	18498	, 'SKILL'),
       (66,	19025	, 'SKILL'),
       (67,	19559	, 'SKILL'),
       (68,	20100	, 'SKILL'),
       (69,	20648	, 'SKILL'),
       (70,	21204	, 'SKILL'),
       (71,	21767	, 'SKILL'),
       (72,	22337	, 'SKILL'),
       (73,	22914	, 'SKILL'),
       (74,	23499	, 'SKILL'),
       (75,	24091	, 'SKILL'),
       (76,	24690	, 'SKILL'),
       (77,	25296	, 'SKILL'),
       (78,	25910	, 'SKILL'),
       (79,	26531	, 'SKILL'),
       (80,	27159	, 'SKILL'),
       (81,	27794	, 'SKILL'),
       (82,	28437	, 'SKILL'),
       (83,	29087	, 'SKILL'),
       (84,	29744	, 'SKILL'),
       (85,	30409	, 'SKILL'),
       (86,	31081	, 'SKILL'),
       (87,	31760	, 'SKILL'),
       (88,	32447	, 'SKILL'),
       (89,	33141	, 'SKILL'),
       (90,	33842	, 'SKILL'),
       (91,	34551	, 'SKILL'),
       (92,	35267	, 'SKILL'),
       (93,	35991	, 'SKILL'),
       (94,	36722	, 'SKILL'),
       (95,	37460	, 'SKILL'),
       (96,	38206	, 'SKILL'),
       (97,	38959	, 'SKILL'),
       (98,	39720	, 'SKILL'),
       (99,	40488	, 'SKILL');


-- 퀘스트 테마
INSERT INTO quest_theme (linked_attribute, id, name) VALUES
                                                         (262,1, '디지털 사용 절제'),
                                                         (81,2, '건강 루틴'),
                                                         (1058,3, '의사소통 훈련'),
                                                         (2066,4, '배우는 습관 갖기'),
                                                         (42,5, '새로운 시도 도전'),
                                                         (81,6, '아침 루틴 정착'),
                                                         (84,7, '수면 습관 개선'),
                                                         (69,8, '소비 절제'),
                                                         (1064,9, '감정 표현력 향상'),
                                                         (386,10, '생각 정리'),
                                                         (2178,11, '논리적인 사고'),
                                                         (97,12, '스포츠 취미 발견'),
                                                         (2072,13, '내 취향 탐색'),
                                                         (2192,14, '여행 계획'),
                                                         (592,15, '요리하기');

-- 메인퀘스트
INSERT INTO public.main_quest(
    linked_attribute, id, theme_id, name, attribute1, attribute2, exp1, exp2, npc_name)
VALUES (260,1001, 1, '스마트폰 사용 패턴 분석하기', 203, 103, 100, 50, '패턴의 기록자'),
       (6,1002, 1, '디지털 방해 없는 고정 시간 정하기', 102, 103, 100, 50, '몰입의 수호자'),
       (260,1003, 1, '내 디지털 습관 파악하기', 203, 103, 100, 50, '행동의 조율자'),
       (6,1004, 1, '집중을 방해하는 앱 목록 정리하기', 102, 103, 100, 50, '방해의 추적자'),
       (258,1005, 1, '내 디지털 알림 패턴 분석하기', 203, 102, 100, 50, '패턴의 기록자'),
       (6,1006, 1, '휴대폰 없이 몰입할 공간 만들기', 103, 102, 100, 50, '환경의 설계자'),
       (80,2001, 2, '스트레칭 습관 만들기', 201, 105, 100, 50, '유연의 수련자'),
       (80,2002, 2, '수분 섭취 습관 만들기', 201, 105, 100, 50, '수분의 점검자'),
       (65,2003, 2, '수면 습관 개선 실천하기', 201, 101, 100, 50, '숙면의 수호자'),
       (65,2004, 2, '올바른 식사 습관 만들기', 201, 101, 100, 50, '식사의 관리자'),
       (80,2005, 2, '눈 피로 줄이기', 201, 105, 100, 50, '눈의 회복자'),
       (65,2006, 2, '바른 자세 유지하기', 201, 101, 100, 50, '자세의 교정가'),
       (1026,3001, 3, '다른 사람의 말 경청하기', 102, 205, 100, 50, '경청의 달인'),
       (1056,3002, 3, '새로운 사람과 대화하기', 106, 205, 100, 50, '첫걸음의 사절'),
       (1026,3003, 3, '대화 중 좋은 질문 던지기', 205, 102, 100, 50, '질문의 탐험가'),
       (1056,3004, 3, '의견을 명확히 전달하는 말하기', 205, 106, 100, 50, '명료의 전도사'),
       (1056,3005, 3, '자기소개 준비하기', 205, 106, 100, 50, '자기 PR의 장인'),
       (1026,3006, 3, '떨지 않고 발표하는 실력 기르기', 102, 205, 100, 50, '성찰의 안내자'),
       (2050,4001, 4, '오늘 배운 내용의 핵심을 스스로 설명하기', 206, 102, 100, 50, '지식의 전달자'),
       (2064,4002, 4, '새로 알게 된 개념을 다른 분야와 연결해보기', 206, 105, 100, 50, '통찰의 길잡이'),
       (18,4003, 4, '배운 내용을 시각 자료로 정리하기', 102, 105, 100, 50, '기록의 장인'),
       (2050,4004, 4, '강의나 글에서 중요한 질문 3가지 만들기', 206, 102, 100, 50, '호기심의 수호자'),
       (2064,4005, 4, '배운 내용을 실생활 상황에 적용해보기', 206, 105, 100, 50, '실천의 선도자'),
       (18,4006, 4, '하루 학습한 내용을 한 문단으로 요약하기', 105, 102, 100, 50, '정리의 달인'),
       (40,5001, 5, '가본 적 없는 동네 탐방하기', 106, 104, 100, 50, '미지의 탐험가'),
       (40,5002, 5, '새로운 악기 배우기', 106, 104, 100, 50, '도전의 개척자'),
       (40,5003, 5, '새로운 운동 배우기', 106, 104, 100, 50, '도전의 개척자'),
       (40,5004, 5, '새로운 요리 먹어보기', 104, 106, 100, 50, '경험의 수집가'),
       (34,5005, 5, '길에서 처음 보는 사람에게 먼저 인사 건네기', 106, 102, 100, 50, '대화의 개척자'),
       (10,5006, 5, '평소와 다른 경로로 출근하거나 등교하기', 102, 104, 100, 50, '변화의 연금술사'),
       (80,6001, 6, '기상 후 몸과 마음을 깨우는 루틴 만들기', 105, 201, 100, 50, '아침의 개척자'),
       (65,6002, 6, '아침 운동 습관 만들기', 201, 101, 100, 50, '활력의 전도사'),
       (80,6003, 6, '아침 식사 준비와 섭취 습관 만들기', 105, 201, 100, 50, '영양의 수호자'),
       (17,6004, 6, '하루 계획 세우는 아침 시간 만들기', 105, 101, 100, 50, '계획의 설계자'),
       (65,6005, 6, '아침 명상이나 호흡 훈련 습관 만들기', 101, 201, 100, 50, '평온의 지휘자'),
       (17,6006, 6, '아침을 여유롭게 시작하는 준비 습관 만들기', 105, 101, 100, 50, '여유의 장인'),
       (20,7001, 7, '일정한 시간에 잠자리에 들기 습관 만들기', 105, 103, 100, 50, '수면 리듬의 관리자'),
       (68,7002, 7, '취침 전 디지털 기기 사용 줄이기', 103, 201, 100, 50, '빛 차단의 수호자'),
       (80,7003, 7, '잠들기 전 스트레칭 루틴 만들기', 201, 105, 100, 50, '근육 이완의 길잡이'),
       (80,7004, 7, '아침 기상 시간을 일정하게 유지하기', 105, 201, 100, 50, '새벽의 선도자'),
       (20,7005, 7, '잠들기 전 독서 습관 만들기', 103, 105, 100, 50, '집중의 서고지기'),
       (68,7006, 7, '숙면을 위한 환경 정리 습관 만들기', 201, 103, 100, 50, '평온의 설계자'),
       (5,8001, 8, '한 달 소비 내역 분석하기', 103, 101, 100, 50, '절약의 기록자'),
       (68,8002, 8, '필요 없는 구독 서비스 해지하기', 103, 201, 100, 50, '소비 절제의 감시자'),
       (5,8003, 8, '장보기 전 구매 목록 작성하기', 103, 101, 100, 50, '계획의 수호자'),
       (5,8004, 8, '하루 동안 불필요한 지출 피하기', 101, 103, 100, 50, '유혹의 방패'),
       (68,8005, 8, '예산에 맞춘 주간 식단 계획 세우기', 201, 103, 100, 50, '알뜰한 요리사'),
       (5,8006, 8, '비상금 목표액 설정하고 저축 시작하기', 101, 103, 100, 50, '미래의 설계자'),
       (1032,9001, 9, '감정을 다양한 어휘로 표현하기', 205, 104, 100, 50, '감정 어휘의 연금술사'),
       (1056,9002, 9, '대화 중 감정 묘사 시도하기', 205, 106, 100, 50, '감정 묘사의 개척자'),
       (1056,9003, 9, '감정에 맞는 목소리 톤 변화 연습하기', 205, 106, 100, 50, '톤 조절의 지휘자'),
       (1032,9004, 9, '감정을 글로 표현해보기', 104, 205, 100, 50, '감정 기록의 서기'),
       (1056,9005, 9, '표정과 제스처로 감정 전달하기', 205, 106, 100, 50, '표정의 무대 감독'),
       (1056,9006, 9, '감정 표현 상황에서 다양한 말투 사용하기', 205, 106, 100, 50, '말투 실험의 연금술사'),
       (130,10001, 10, '마인드맵으로 생각 구조화하기', 202, 102, 100, 50, '구조의 항해사'),
       (258,10002, 10, '머릿속 생각 모두 적어서 비우기', 203, 102, 100, 50, '비움의 기록관'),
       (384,10003, 10, '3단 논리로 주장 정리하기', 202, 203, 100, 50, '논리의 감독관'),
       (258,10004, 10, '하루 끝에 생각 정리하기', 203, 102, 100, 50, '일지의 사서'),
       (130,10005, 10, '한 가지 주제로 생각 묶기', 102, 202, 100, 50, '집중의 큐레이터'),
       (258,10006, 10, '산만함 줄이고 핵심만 남기기', 102, 203, 100, 50, '핵심의 편집자'),
       (130,11001, 11, '주장과 근거를 표로 정리하기', 202, 102, 100, 50, '논증의 설계자'),
       (2176,11002, 11, '원인과 결과의 연결 고리 찾기', 206, 202, 100, 50, '인과의 탐험가'),
       (2176,11003, 11, '반대 의견에 대한 반박 구성하기', 202, 206, 100, 50, '논쟁의 조율자'),
       (2050,11004, 11, '문제 상황의 해결책 3가지 제시', 206, 102, 100, 50, '해답의 기획자'),
       (130,11005, 11, '잘못된 논리 찾기 연습하기', 102, 202, 100, 50, '오류의 추적자'),
       (2176,11006, 11, '여러 선택지의 장단점 비교하기', 202, 206, 100, 50, '선택의 감정관'),
       (96,12001, 12, '새로운 스포츠 종목 기초 배우기', 106, 201, 100, 50, '도전의 개척자'),
       (65,12002, 12, '스포츠 종목별 기초 체력 만들기', 201, 101, 100, 50, '체력의 조련사'),
       (33,12003, 12, '지역 스포츠 모임에 참여하기', 106, 101, 100, 50, '교류의 주역'),
       (65,12004, 12, '관전 취미를 가질 스포츠 팀 정하기', 101, 201, 100, 50, '기술의 연마자'),
       (33,12005, 12, '경기 규칙과 전략 익히기', 106, 101, 100, 50, '전술의 설계자'),
       (65,12006, 12, '지속 가능한 스포츠 루틴 만들기', 101, 201, 100, 50, '습관의 장인'),
       (2064,13001, 13, '독서 취향 탐험하기', 206, 105, 100, 50, '지식의 항해자'),
       (2056,13002, 13, '음악 취향 발견하기', 104, 206, 100, 50, '선율의 탐험가'),
       (2056,13003, 13, '영화·드라마 취향 탐험하기', 206, 104, 100, 50, '이야기의 모험가'),
       (24,13004, 13, '운동 취향 시도하기', 104, 105, 100, 50, '땀방울의 개척자'),
       (2056,13005, 13, '게임 취향 분석하기', 206, 104, 100, 50, '플레이의 전략가'),
       (24,13006, 13, '미술·공예 취향 탐색하기', 104, 105, 100, 50, '창작의 연금술사'),
       (2176,14001, 14, '세계 문화 탐방 여행 계획 세우기', 206, 202, 100, 50, '문화의 사절단'),
       (144,14002, 14, '휴양과 힐링을 위한 여행 계획 세우기', 105, 202, 100, 50, '평온의 설계자'),
       (2064,14003, 14, '미식 탐험 여행 계획 세우기', 206, 105, 100, 50, '미각의 탐험가'),
       (144,14004, 14, '자연 경관 감상 여행 계획 세우기', 202, 105, 100, 50, '풍경의 설계자'),
       (2176,14005, 14, '역사 유적 탐방 여행 계획 세우기', 206, 202, 100, 50, '시간의 여행자'),
       (144,14006, 14, '액티비티 중심 여행 계획 세우기', 202, 105, 100, 50, '모험의 설계자'),
       (528,15001, 15, '자취생을 위한 간단 요리 마스터하기', 204, 105, 100, 50, '생활의 셰프'),
       (576,15002, 15, '다이어트 식단 요리 만들기', 201, 204, 100, 50, '균형의 조리사'),
       (528,15003, 15, '세계 각국의 전통 요리 도전하기', 204, 105, 100, 50, '세계의 요리사'),
       (576,15004, 15, '가족을 위한 건강식 요리하기', 201, 204, 100, 50, '가정의 조리인'),
       (528,15005, 15, '손님 접대용 특별 요리 완성하기', 204, 105, 100, 50, '환대의 셰프'),
       (528,15006, 15, '제철 재료로 창의적인 요리 만들기', 204, 105, 100, 50, '계절의 요리사');



-- 서브 퀘스트
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10001,'TIME_MINUTE','MOOD_LOG','기상 후 {actionUnitNum}분 동안 스마트폰 잠금 모드 유지하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10002,'ONCE','MOOD_LOG','스마트폰 사용 전 사용 목적 생각하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10003,'ONCE','MOOD_LOG','스마트폰 사용 시간 체크 후 느낀점 남기기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10004,'ONCE','MOOD_LOG','하루 중 스마트폰 사용 시간 파악하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10005,'ONCE','MOOD_LOG','불필요한 앱 삭제 리스트 만들어 삭제하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10006,'ONCE','MOOD_LOG','앱 별 사용 시간 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10007,'ONCE','MOOD_LOG','디지털 차단 앱 설치 및 사용 스크린샷 찍기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10008,'ONCE','MOOD_LOG','디지털 절제 시간대 정하고 메모 남기기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10009,'TIME_MINUTE','MOOD_LOG','디지털 사용 중단 후 {actionUnitNum}분간 다른 활동 해보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10010,'TIME_MINUTE','MOOD_LOG','디지털 사용 중단 후 {actionUnitNum}분간 산책하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10011,'ONCE','MOOD_LOG','업무 중 집중 방해 요인 파악하고 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10012,'ONCE','MOOD_LOG','휴식 시간에 집중 방해 요인 파악하고 노트에 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10013,'ONCE','MOOD_LOG','SNS 사용 전 \"이용 목적\" 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10014,'ONCE','MOOD_LOG','SNS 앱 삭제하고 대체 행동으로 독서하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10015,'ONCE','MOOD_LOG','SNS 앱 삭제하고 대체 행동으로 산책하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10016,'ONCE','MOOD_LOG','디지털 알림 끄기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10017,'ONCE','MOOD_LOG','디지털 유혹 순간을 대처한 방식 간단히 쓰기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10018,'ONCE','MOOD_LOG','디지털 사용 후 즉시 느낀점 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10019,'ONCE','MOOD_LOG','하루 동안 집중이 잘 된 순간을 써보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10020,'ONCE','MOOD_LOG','집중을 방해한 앱의 사용 시간 캡처하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10021,'ONCE','MOOD_LOG','앱 삭제 전과 후 집중도를 비교하고 느낀점 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10022,'ONCE','MOOD_LOG','집중 시간에 스마트폰 알림 끄기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10023,'ONCE','MOOD_LOG','집중 시간에 스마트폰 비행기 모드 켜기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10024,'ONCE','MOOD_LOG','스마트폰 사용 욕구가 생긴 순간 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10025,'ONCE','MOOD_LOG','SNS 접속 전, 접속 목적과 사용 시간을 메모하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10026,'ONCE','MOOD_LOG','오늘 디지털 사용 중 후회를 느낀 순간 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10027,'ONCE','MOOD_LOG','디지털 절제에 도움이 된 행동 구체적으로 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10028,'ONCE','MOOD_LOG','오늘 집중을 방해한 디지털 요소 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10029,'ONCE','MOOD_LOG','오늘 하루의 집중 유지 시간을 측정하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (10030,'ONCE','MOOD_LOG','디지털 절제 도전을 위한 오늘의 다짐 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20001,'TIME_MINUTE','MOOD_LOG','기상 후 {actionUnitNum}분 내로 스트레칭 시작하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20002,'ONCE','MOOD_LOG','스트레칭 실천 전후 기분 변화 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20003,'ONCE','MOOD_LOG','아침 식사 전 가볍게 손목 돌리는 스트레칭하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20004,'ONCE','MOOD_LOG','아침 기상 후 창문 열고 1분간 깊게 숨쉬기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20005,'TIME_MINUTE','MOOD_LOG','기상 후 {actionUnitNum}분 동안 스마트폰 미사용하고 스트레칭 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20006,'ONCE','MOOD_LOG','기상 후 기지개 켜기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20007,'ONCE','MOOD_LOG','하루 수분 섭취 상황 체크하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20008,'NUMBER_3','MOOD_LOG','하루 {actionUnitNum}회 이상 의식적으로 물 1컵 마시기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20009,'ONCE','MOOD_LOG','물 마시는 시간을 정하고 실천하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20010,'ONCE','MOOD_LOG','음료 대신 물 선택한 순간 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20011,'TIME_MINUTE','MOOD_LOG','잠자기 전 {actionUnitNum}분 동안 수면 유도 명상하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20012,'ONCE','MOOD_LOG','수면 환경 개선을 위해 실천한 오늘의 행동 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20013,'ONCE','MOOD_LOG','전날 수면 시간 확인 후 루틴 되돌아보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20014,'TIME_MINUTE','MOOD_LOG','잠들기 전 {actionUnitNum}분간 디지털 기기 사용 중단 유지하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20015,'ONCE','MOOD_LOG','오늘 섭취한 가공식품 개수 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20016,'NUMBER_3','MOOD_LOG','앱을 활용해 하루 걸음 수 {actionUnitNum}회 이상 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20017,'ONCE','MOOD_LOG','하루 중 건강 루틴 실천이 어려운 시간대 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20018,'ONCE','MOOD_LOG','걷기 전후 컨디션 체크하고 기분 일지 남기기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20019,'TIME_MINUTE','MOOD_LOG','작업 중 {actionUnitNum}분마다 눈 깜빡이기 의식적으로 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20020,'ONCE','MOOD_LOG','집중 작업 전 모니터 밝기와 색온도 조정하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20021,'NUMBER_3','MOOD_LOG','하루 {actionUnitNum}회 이상 먼 곳 바라보며 눈 근육 풀기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20022,'TIME_MINUTE','MOOD_LOG','잠자기 전 {actionUnitNum}분간 눈 감고 휴식하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20023,'TIME_HOUR','MOOD_LOG','오늘 하루 동안 {actionUnitNum}시간 이상 앉아 있었는지 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20024,'ONCE','MOOD_LOG','앉아있는 자세에서 어깨 회전 스트레칭 수행하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20025,'TIME_MINUTE','MOOD_LOG','생각날 때 허리 펴고 바른 자세로 {actionUnitNum}분 유지하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20026,'ONCE','MOOD_LOG','스트레칭 진행 전후로 컨디션 점검하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (20027,'NUMBER_3','MOOD_LOG','하루 중 {actionUnitNum}회 이상 스스로 자세 점검하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30001,'ONCE','MOOD_LOG','대화 중 상대방 말을 끝까지 끊지 않고 듣기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30002,'ONCE','MOOD_LOG','상대방이 한 말에서 핵심 단어 3개 이상 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30003,'ONCE','MOOD_LOG','대화 중 이해한 내용을 내 말로 요약하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30004,'ONCE','MOOD_LOG','처음 보는 사람에게 인사 건네기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30005,'ONCE','MOOD_LOG','대화 중 칭찬 한마디 건네기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30006,'ONCE','MOOD_LOG','처음 만난 사람의 이름 기억하고 불러주기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30007,'NUMBER_3','MOOD_LOG','상대방의 말에 열린 질문 {actionUnitNum}회 던지기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30008,'ONCE','MOOD_LOG','상대방 대답에 후속 질문 이어가기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30009,'ONCE','MOOD_LOG','질문 전에 짧게 공감 표현하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30010,'ONCE','MOOD_LOG','의견 발표 전 3문장으로 구조 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30011,'ONCE','MOOD_LOG','말할 때 불필요한 추임새 줄이기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30012,'ONCE','MOOD_LOG','중요한 포인트는 손동작이나 표정으로 강조하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30013,'ONCE','MOOD_LOG','5문장 이상의 자기소개 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30014,'ONCE','MOOD_LOG','자기소개를 거울 보며 말해보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30015,'ONCE','MOOD_LOG','자기소개 중 강점 2가지 포함하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30016,'ONCE','MOOD_LOG','발표 전 심호흡 3회 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30017,'ONCE','MOOD_LOG','발표 후 스스로의 피드백 1개 이상 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (30018,'ONCE','MOOD_LOG','연습 발표 녹화하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40001,'NUMBER_1','MOOD_LOG','오늘 배운 내용을 {actionUnitNum}문장으로 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40002,'NUMBER_3','MOOD_LOG','중요 개념을 {actionUnitNum}회 반복해서 말해보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40003,'ONCE','MOOD_LOG','배운 내용을 친구에게 한 번 설명해보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40004,'NUMBER_1','MOOD_LOG','새로 배운 개념을 관련 분야 {actionUnitNum}개 찾기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40005,'NUMBER_1','MOOD_LOG','관련 주제의 기사 {actionUnitNum}개 읽기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40006,'ONCE','MOOD_LOG','관련 도서 한 권에서 핵심 내용 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40007,'ONCE','MOOD_LOG','배운 내용을 마인드맵으로 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40008,'NUMBER_1','MOOD_LOG','학습 내용에서 핵심 키워드 {actionUnitNum}개 뽑기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40009,'NUMBER_1','MOOD_LOG','수업 중 들었던 질문 {actionUnitNum}개 메모하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40010,'NUMBER_1','MOOD_LOG','강의나 글에서 인상적인 문장 {actionUnitNum}개 적어보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40011,'NUMBER_3','MOOD_LOG','배운 내용을 하루 생활 속에서 {actionUnitNum}회 적용하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40012,'ONCE','MOOD_LOG','실천 후 느낀 점을 간단히 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40013,'NUMBER_1','MOOD_LOG','하루 학습 내용을 {actionUnitNum}문장으로 요약하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40014,'NUMBER_1','MOOD_LOG','오늘 배운 단어 {actionUnitNum}개로 짧은 문장 만들기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40015,'NUMBER_2','MOOD_LOG','핵심 문장을 {actionUnitNum}초 동안 끊김 없이 말하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40016,'NUMBER_3','MOOD_LOG','복습 카드를 {actionUnitNum}회 만들어 보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40017,'NUMBER_1','MOOD_LOG','관련 사례 {actionUnitNum}개 찾고 메모하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (40018,'NUMBER_1','MOOD_LOG','배운 내용 도식 {actionUnitNum}개 업데이트하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50001,'NUMBER_1','MOOD_LOG','처음 가는 동네의 주요 명소 {actionUnitNum}곳 방문하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50002,'NUMBER_1','MOOD_LOG','방문한 동네에서 인상적인 장면 {actionUnitNum}장 촬영하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50003,'NUMBER_1','MOOD_LOG','방문한 동네의 현지 음식 {actionUnitNum}가지 맛보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50004,'TIME_MINUTE','MOOD_LOG','처음 배우는 악기의 기본 연습 {actionUnitNum}분 진행하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50005,'NUMBER_1','MOOD_LOG','새 악기로 간단한 곡 {actionUnitNum}곡 연주 도전하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50006,'NUMBER_1','MOOD_LOG','악기 연습 과정 중 새로 배운 점 {actionUnitNum}개 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50007,'NUMBER_3','MOOD_LOG','새로운 운동 동작 {actionUnitNum}회 반복 연습하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50008,'TIME_MINUTE','MOOD_LOG','배운 운동 기술을 {actionUnitNum}분간 실습하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50009,'NUMBER_1','MOOD_LOG','운동 후 느낀 변화를 {actionUnitNum}문장으로 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50010,'ONCE','MOOD_LOG','새로운 요리를 한 입 시식하고 첫인상 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50011,'NUMBER_1','MOOD_LOG','먹은 요리의 재료 {actionUnitNum}가지 알아보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50012,'NUMBER_1','MOOD_LOG','요리에서 인상적인 맛 요소 {actionUnitNum}개 메모하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50013,'NUMBER_3','MOOD_LOG','처음 보는 사람에게 인사 {actionUnitNum}회 건네기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50014,'NUMBER_1','MOOD_LOG','인사 후 상대 반응 {actionUnitNum}가지 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50015,'NUMBER_1','MOOD_LOG','대화 시작을 위한 첫 마디 {actionUnitNum}가지 준비하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50016,'NUMBER_1','MOOD_LOG','출근/등교 경로에서 새로운 풍경 {actionUnitNum}장 촬영하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50017,'TIME_MINUTE','MOOD_LOG','평소보다 다른 길을 걸은 시간 {actionUnitNum}분 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (50018,'NUMBER_1','MOOD_LOG','새 경로에서 발견한 특징 {actionUnitNum}가지 메모하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60001,'ONCE','MOOD_LOG','기상 후 바로 침대 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60002,'NUMBER_1','MOOD_LOG','아침 물 {actionUnitNum}컵 마시기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60003,'TIME_MINUTE','MOOD_LOG','아침 햇빛 받으며 {actionUnitNum}분 산책하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60004,'TIME_MINUTE','MOOD_LOG','아침 스트레칭 {actionUnitNum}분 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60005,'NUMBER_1','MOOD_LOG','아침 식사에 단백질 음식 {actionUnitNum}개 포함하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60006,'ONCE','MOOD_LOG','아침 식사 시간 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60007,'TIME_MINUTE','MOOD_LOG','아침 명상 {actionUnitNum}분 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60008,'NUMBER_1','MOOD_LOG','아침 계획 노트에 오늘 할 일 {actionUnitNum}개 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60009,'NUMBER_1','MOOD_LOG','아침에 뉴스 기사 {actionUnitNum}개 읽고 요약하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60010,'NUMBER_1','MOOD_LOG','아침 운동 전 물 {actionUnitNum}컵 마시기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60011,'DISTANCE','MOOD_LOG','아침 러닝 {actionUnitNum}km 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60012,'NUMBER_1','MOOD_LOG','아침 근력 운동 {actionUnitNum}세트 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60013,'NUMBER_1','MOOD_LOG','아침에 어제 감사했던 일 {actionUnitNum}개 쓰기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60014,'ONCE','MOOD_LOG','아침에 가족에게 인사하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60015,'ONCE','MOOD_LOG','아침에 짧은 격려 메시지 보내기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60016,'ONCE','MOOD_LOG','아침 알람을 전날 밤에 설정하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60017,'TIME_MINUTE','MOOD_LOG','아침 준비 시간 {actionUnitNum}분 측정하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (60018,'ONCE','MOOD_LOG','아침 출근/등교 준비 체크리스트 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70001,'ONCE','MOOD_LOG','매일 같은 시간에 알람 맞추기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70002,'TIME_MINUTE','MOOD_LOG','취침 시간 {actionUnitNum}분 전에 조명 어둡게 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70003,'ONCE','MOOD_LOG','기상 후 바로 창문 열어 햇빛 받기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70004,'TIME_MINUTE','MOOD_LOG','취침 전 {actionUnitNum}분 동안 휴대폰 사용하지 않기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70005,'ONCE','MOOD_LOG','침실에 휴대폰 두지 않기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70006,'NUMBER_1','MOOD_LOG','취침 전 디지털 기기 사용을 {actionUnitNum}회 줄이기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70007,'TIME_MINUTE','MOOD_LOG','취침 전 {actionUnitNum}분간 전신 스트레칭');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70008,'NUMBER_1','MOOD_LOG','목, 어깨 스트레칭 {actionUnitNum}회 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70009,'NUMBER_1','MOOD_LOG','취침 전 요가 자세 {actionUnitNum}개 수행하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70010,'NUMBER_1','MOOD_LOG','기상 시간 맞추기 알람 {actionUnitNum}개 설정');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70011,'TIME_MINUTE','MOOD_LOG','아침 기상 후 {actionUnitNum}분 내에 일어나기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70012,'ONCE','MOOD_LOG','아침 기상 시 침대 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70013,'NUMBER_1','MOOD_LOG','취침 전 독서 {actionUnitNum}쪽 읽기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70014,'ONCE','MOOD_LOG','독서 전 책갈피와 조명 준비하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70015,'ONCE','MOOD_LOG','독서 중 휴대폰 전원 끄기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70016,'TIME_MINUTE','MOOD_LOG','침실 바닥 청소 {actionUnitNum}분 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70017,'ONCE','MOOD_LOG','침대 시트 교체하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (70018,'NUMBER_1','MOOD_LOG','방 안 환기 {actionUnitNum}회 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80001,'ONCE','MOOD_LOG','최근 일주일간 소비 내역 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80002,'NUMBER_1','MOOD_LOG','소비 내역 중 불필요 지출 {actionUnitNum}개 뽑아보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80003,'ONCE','MOOD_LOG','소비 내역을 기록 파일로 저장하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80004,'ONCE','MOOD_LOG','소비 내역 중 필수 소비 금액 비율 계산하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80005,'ONCE','MOOD_LOG','하루 예산 대비 지출 차액 계산하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80006,'ONCE','MOOD_LOG','사용하지 않는 구독 서비스 확인하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80007,'ONCE','MOOD_LOG','구독 서비스 해지 절차 진행하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80008,'ONCE','MOOD_LOG','구독 해지 사유 메모하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80009,'ONCE','MOOD_LOG','구독 서비스 목록을 캡처해 보관하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80010,'ONCE','MOOD_LOG','구독 서비스 결제일 알림 설정하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80011,'ONCE','MOOD_LOG','장보기 전 구매 목록 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80012,'NUMBER_1','MOOD_LOG','구매 목록 중 불필요 항목 {actionUnitNum}개 삭제하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80013,'ONCE','MOOD_LOG','구매 목록과 실제 구매 비교 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80014,'ONCE','MOOD_LOG','비슷한 상품 중 할인된 것으로 구매하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80015,'ONCE','MOOD_LOG','구매 후 영수증 보관하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80016,'NUMBER_3','MOOD_LOG','하루종일 즉흥 구매를 하지 않기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80017,'NUMBER_1','MOOD_LOG','구매 욕구 참은 이유 {actionUnitNum}개 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80018,'ONCE','MOOD_LOG','하루 소비 내역 점검하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80019,'NUMBER_1','MOOD_LOG','즉흥 구매 유혹을 받은 상황 {actionUnitNum}개 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80020,'ONCE','MOOD_LOG','즉흥 구매 대신 한 대체 행동 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80021,'ONCE','MOOD_LOG','주간 식단에 필요한 식재료 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80022,'ONCE','MOOD_LOG','예산 내에서 구매 가능한 대체 식사 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80023,'ONCE','MOOD_LOG','식단 계획표 작성 완료하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80024,'ONCE','MOOD_LOG','식재료 유통기한 확인하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80025,'ONCE','MOOD_LOG','남은 재료 활용 요리 아이디어 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80026,'ONCE','MOOD_LOG','비상금 목표액과 현재액 비교하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80027,'ONCE','MOOD_LOG','저축 계좌 거래 내역 확인하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80028,'NUMBER_1','MOOD_LOG','저축 동기 {actionUnitNum}개 적어보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80029,'ONCE','MOOD_LOG','한 달 저축 달성률 계산하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (80030,'ONCE','MOOD_LOG','비상금 사용 항목 선정하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90001,'NUMBER_1','MOOD_LOG','감정 어휘 {actionUnitNum}개 수집하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90002,'NUMBER_1','MOOD_LOG','오늘 대화에서 새 감정 어휘 {actionUnitNum}개 사용하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90003,'NUMBER_3','MOOD_LOG','기분이 변한 순간 {actionUnitNum}회 메모하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90004,'ONCE','MOOD_LOG','느낀 감정을 1문장으로 즉시 말해보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90005,'NUMBER_1','MOOD_LOG','특정 감정을 담은 문장 {actionUnitNum}개 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90006,'NUMBER_1','MOOD_LOG','장면 감정 묘사 {actionUnitNum}문장 필사하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90007,'NUMBER_1','MOOD_LOG','목소리 톤 {actionUnitNum}단계 변화 연습하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90008,'NUMBER_3','MOOD_LOG','같은 문장을 억양 다르게 {actionUnitNum}회 읽기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90009,'NUMBER_3','MOOD_LOG','거울 보며 표정 {actionUnitNum}회 연습하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90010,'NUMBER_1','MOOD_LOG','제스처 {actionUnitNum}개 선택해 말에 적용하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90011,'ONCE','MOOD_LOG','친구에게 감사 인사 전하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90012,'NUMBER_1','MOOD_LOG','감정 일기 {actionUnitNum}문장 쓰기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90013,'NUMBER_1','MOOD_LOG','오늘 감정을 색 {actionUnitNum}개로 표시하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90014,'NUMBER_1','MOOD_LOG','대화 녹음 후 감정 표현 피드백 {actionUnitNum}개 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90015,'NUMBER_1','MOOD_LOG','다른 사람 말투 {actionUnitNum}개 따라 해보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90016,'NUMBER_1','MOOD_LOG','감정 표현 시 금지어 {actionUnitNum}개 정하고 피하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90017,'NUMBER_3','MOOD_LOG','표현 전 심호흡 {actionUnitNum}회 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (90018,'NUMBER_3','MOOD_LOG','대화에서 공감 표현 {actionUnitNum}회 시도하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100001,'NUMBER_1','MOOD_LOG','중심 주제 후보 {actionUnitNum}개 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100002,'NUMBER_1','MOOD_LOG','1차 가지 {actionUnitNum}개 뻗기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100003,'ONCE','MOOD_LOG','유사 항목 통합하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100004,'TIME_MINUTE','MOOD_LOG','타이머 {actionUnitNum}분 설정해 브레인 덤프 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100005,'ONCE','MOOD_LOG','브레인 덤프한 항목에 태그 붙이기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100006,'ONCE','MOOD_LOG','1분 안에 생각 멈추고 즉시 처리할 일 하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100007,'ONCE','MOOD_LOG','CRE(주장-근거-예시) 틀로 골격 만들기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100008,'ONCE','MOOD_LOG','주장 문장 작성하고 핵심어 밑줄 긋기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100009,'NUMBER_1','MOOD_LOG','반론 {actionUnitNum}개 상상하고 반박 포인트 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100010,'NUMBER_1','MOOD_LOG','하루 핵심 장면 {actionUnitNum}개 선정하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100011,'NUMBER_1','MOOD_LOG','오늘 배운 것 {actionUnitNum}개 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100012,'TIME_MINUTE','MOOD_LOG','{actionUnitNum}분동안 오늘 일기 쓰기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100013,'ONCE','MOOD_LOG','주제 밖 항목을 제거하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100014,'ONCE','MOOD_LOG','항목을 묶음으로 만들기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100015,'ONCE','MOOD_LOG','항목 별로 한줄 제목 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100016,'ONCE','MOOD_LOG','문단에서 군더더기 문장 삭제하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100017,'NUMBER_2','MOOD_LOG','핵심 메시지 {actionUnitNum}초 요약 말하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (100018,'NUMBER_1','MOOD_LOG','핵심 포인트 {actionUnitNum}개 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110001,'NUMBER_1','MOOD_LOG','주요 주장 {actionUnitNum}개와 각 근거 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110002,'NUMBER_3','MOOD_LOG','기사의 주장·근거 구조 분석하기 {actionUnitNum}회');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110003,'ONCE','MOOD_LOG','논문에서 핵심 주장과 지원 근거 표시하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110004,'NUMBER_1','MOOD_LOG','사건의 원인 {actionUnitNum}개와 그에 따른 결과 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110005,'NUMBER_1','MOOD_LOG','역사 사건의 원인·결과 연결 {actionUnitNum}개 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110006,'NUMBER_1','MOOD_LOG','뉴스에서 원인과 결과 추론 {actionUnitNum}개 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110007,'NUMBER_1','MOOD_LOG','상대 주장 읽고 반박 근거 {actionUnitNum}개 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110008,'NUMBER_1','MOOD_LOG','토론에서 반대 입장 자료 {actionUnitNum}개 수집하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110009,'NUMBER_3','MOOD_LOG','반박문 초안 검토 {actionUnitNum}회 수행하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110010,'NUMBER_1','MOOD_LOG','가능한 해결책 {actionUnitNum}개 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110011,'NUMBER_1','MOOD_LOG','해결책별 장점 {actionUnitNum}개씩 나열하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110012,'ONCE','MOOD_LOG','문제 해결 과정 흐름도 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110013,'ONCE','MOOD_LOG','기사에서 오류 있는 주장 찾기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110014,'ONCE','MOOD_LOG','블로그 글에서 논리적 비약 사례 찾기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110015,'ONCE','MOOD_LOG','광고 문구에서 허위·과장 표현 찾기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110016,'ONCE','MOOD_LOG','선택지의 장단점을 비교하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110017,'ONCE','MOOD_LOG','각 선택지별 예상 결과와 위험 요소 적기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (110018,'ONCE','MOOD_LOG','선택지별 실행 평가 진행하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120001,'NUMBER_1','MOOD_LOG','스포츠 기본 동작 {actionUnitNum}개 익히기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120002,'NUMBER_3','MOOD_LOG','유튜브로 가이드 영상 시청 {actionUnitNum}회');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120003,'NUMBER_3','MOOD_LOG','기초 기술 실습 {actionUnitNum}회');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120004,'ONCE','MOOD_LOG','새로운 장비 사용해보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120005,'ONCE','MOOD_LOG','기초 자세 촬영 후 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120006,'NUMBER_3','MOOD_LOG','스트레칭 {actionUnitNum}회 수행');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120007,'NUMBER_1','MOOD_LOG','근력 운동 동작 {actionUnitNum}개 수행');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120008,'NUMBER_3','MOOD_LOG','유산소 운동 {actionUnitNum}회 진행');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120009,'ONCE','MOOD_LOG','체력 측정 결과 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120010,'ONCE','MOOD_LOG','기초 체력 루틴 시범 수행');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120011,'ONCE','MOOD_LOG','모임 첫 참석하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120012,'NUMBER_3','MOOD_LOG','팀 연습 경기 {actionUnitNum}회 참여');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120013,'NUMBER_3','MOOD_LOG','모임원과 기술 교류 {actionUnitNum}회');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120014,'NUMBER_3','MOOD_LOG','경기 전후 인사 나누기 {actionUnitNum}회');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120015,'ONCE','MOOD_LOG','모임 소감 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120016,'NUMBER_3','MOOD_LOG','관심 있는 종목 경기 {actionUnitNum}회 시청');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120017,'NUMBER_1','MOOD_LOG','응원팀 후보 {actionUnitNum}개 목록 만들기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120018,'NUMBER_3','MOOD_LOG','팀 정보 조사 {actionUnitNum}회 진행');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120019,'ONCE','MOOD_LOG','경기 일정 캘린더에 추가하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120020,'ONCE','MOOD_LOG','첫 응원 용품 준비하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120021,'NUMBER_3','MOOD_LOG','경기 규칙서 {actionUnitNum}회 읽기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120022,'NUMBER_3','MOOD_LOG','전략 분석 영상 {actionUnitNum}회 시청');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120023,'NUMBER_1','MOOD_LOG','규칙 요약 {actionUnitNum}개 작성');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120024,'ONCE','MOOD_LOG','친구에게 규칙 설명해보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120025,'ONCE','MOOD_LOG','경기 중 전략 기록해보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120026,'NUMBER_1','MOOD_LOG','운동 시간표 {actionUnitNum}개 작성');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120027,'NUMBER_3','MOOD_LOG','루틴 점검 {actionUnitNum}회 수행');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120028,'NUMBER_1','MOOD_LOG','루틴 보완 아이디어 {actionUnitNum}개 작성');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120029,'ONCE','MOOD_LOG','1주일 루틴 유지하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (120030,'ONCE','MOOD_LOG','운동 후 피드백 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130001,'NUMBER_2','MOOD_LOG','읽고 싶은 책 {actionUnitNum}권 선정');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130002,'NUMBER_3','MOOD_LOG','독서 세션 {actionUnitNum}회 진행');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130003,'NUMBER_1','MOOD_LOG','작품·콘텐츠 리뷰 {actionUnitNum}개 읽기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130004,'NUMBER_1','MOOD_LOG','음악 새 장르 {actionUnitNum}개 탐색');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130005,'NUMBER_3','MOOD_LOG','추천 플레이리스트 청취 {actionUnitNum}회');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130006,'NUMBER_1','MOOD_LOG','좋아요/저장 트랙 {actionUnitNum}개 선정');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130007,'NUMBER_1','MOOD_LOG','관심 작품 {actionUnitNum}개 큐에 추가');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130008,'NUMBER_3','MOOD_LOG','트레일러/클립 시청 {actionUnitNum}회');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130009,'NUMBER_3','MOOD_LOG','시청 세션 {actionUnitNum}회 진행');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130010,'NUMBER_1','MOOD_LOG','감상 노트 {actionUnitNum}개 작성');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130011,'NUMBER_1','MOOD_LOG','운동 체험 후보 {actionUnitNum}개 정리');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130012,'TIME_MINUTE','MOOD_LOG','체험 장소/클래스 탐색 {actionUnitNum}분');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130013,'NUMBER_3','MOOD_LOG','운동 체험 세션 {actionUnitNum}회');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130014,'NUMBER_1','MOOD_LOG','준비물 {actionUnitNum}개 구비');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130015,'NUMBER_1','MOOD_LOG','게임 체험할 장르 {actionUnitNum}개 고르기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130016,'NUMBER_3','MOOD_LOG','데모/체험판 설치 {actionUnitNum}회');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130017,'ONCE','MOOD_LOG','튜토리얼 완료하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130018,'ONCE','MOOD_LOG','플레이타임 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130019,'NUMBER_1','MOOD_LOG','작품 아이디어 {actionUnitNum}개 메모');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130020,'NUMBER_1','MOOD_LOG','재료 {actionUnitNum}개 준비');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130021,'NUMBER_3','MOOD_LOG','기초 기법 연습 {actionUnitNum}회');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130022,'TIME_MINUTE','MOOD_LOG','작업 시간 {actionUnitNum}분 확보');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130023,'ONCE','MOOD_LOG','완성 사진 공유하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (130024,'NUMBER_1','MOOD_LOG','플랫폼/커뮤니티 팔로우 {actionUnitNum}개');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140001,'NUMBER_1','MOOD_LOG','목적지 후보 {actionUnitNum}곳의 장단점 비교하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140002,'ONCE','MOOD_LOG','여행 기간 중 주요 일정 초안 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140003,'ONCE','MOOD_LOG','목적지 관련 사진·영상 자료 조사하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140004,'ONCE','MOOD_LOG','예상 경비 예산표 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140005,'NUMBER_1','MOOD_LOG','목적지 대표 음식 {actionUnitNum}가지 조사하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140006,'ONCE','MOOD_LOG','여행 시기별 날씨·기온 정보 조사하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140007,'ONCE','MOOD_LOG','교통편 가격·소요시간 비교하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140008,'ONCE','MOOD_LOG','여행지 안전 정보 및 유의사항 조사하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140009,'ONCE','MOOD_LOG','여행 준비물 체크리스트 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140010,'ONCE','MOOD_LOG','필수 방문지·체험 활동 목록 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140011,'ONCE','PHOTO_LOG','현지 언어 기본 인사말·표현 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140012,'ONCE','PHOTO_LOG','스파·마사지 등 힐링 프로그램 예약하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140013,'ONCE','PHOTO_LOG','현지 요리 체험 클래스 예약하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140014,'ONCE','PHOTO_LOG','일출·일몰 명소와 시간대 조사하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140015,'ONCE','PHOTO_LOG','방문 예정 유적의 배경과 이야기 조사하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (140016,'ONCE','PHOTO_LOG','액티비티 예약 및 장비 대여 업체 조사하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150001,'ONCE','MOOD_LOG','요리에 필요한 재료 목록 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150002,'ONCE','PHOTO_LOG','시장이나 마트에서 재료 구입하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150003,'ONCE','PHOTO_LOG','선택한 요리 레시피 완성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150004,'ONCE','PHOTO_LOG','완성한 요리 사진 기록하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150005,'ONCE','MOOD_LOG','요리 후 주방 정리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150006,'ONCE','PHOTO_LOG','채소·과일 손질법 연습하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150007,'ONCE','MOOD_LOG','조리 시 기름·소금 최소화하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150008,'ONCE','PHOTO_LOG','전통 한식 재료 손질하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150009,'NUMBER_1','PHOTO_LOG','대표 한식 반찬 {actionUnitNum}종 만들기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150010,'ONCE','PHOTO_LOG','아침 식사에 적합한 재료 준비하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150011,'ONCE','PHOTO_LOG','간단 아침 메뉴 조리 연습');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150012,'ONCE','MOOD_LOG','1인분 요리 레시피 찾기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150013,'ONCE','PHOTO_LOG','냉장고 속 재료로 즉흥 요리하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150014,'ONCE','PHOTO_LOG','이탈리안 요리에 어울리는 허브 재료 손질하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150015,'ONCE','PHOTO_LOG','파스타 소스 레시피 비교 시식하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150016,'ONCE','MOOD_LOG','건강식 재료 영양 성분표 분석하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150017,'ONCE','PHOTO_LOG','저염·저당 조리법 2가지 시도하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150018,'ONCE','PHOTO_LOG','한식 주요 양념장 직접 만들어보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150019,'NUMBER_1','PHOTO_LOG','대표 한식 국물 요리 {actionUnitNum}종 끓여보기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150020,'NUMBER_3','MOOD_LOG','아침 식사 {actionUnitNum}일분 식단 구성안 작성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150021,'TIME_MINUTE','PHOTO_LOG','출근 전 {actionUnitNum}분 완성하는 아침 메뉴 연습하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150022,'ONCE','MOOD_LOG','자취방 환경에 맞는 조리도구 구성하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150023,'NUMBER_3','PHOTO_LOG','{actionUnitNum}일 연속 자취 요리 사진 공유하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150024,'NUMBER_1','MOOD_LOG','디저트 {actionUnitNum}종 선정 후 재료 준비하기');
INSERT INTO sub_quest (id,action_unit_type, confirm_type, name) VALUES (150025,'NUMBER_1','PHOTO_LOG','디저트 {actionUnitNum}종 데코레이션 연습하기');


-- main_sub_quest 테이블
/*
-- Query: SELECT * FROM mydb.main_sub_quest
LIMIT 0, 1000

-- Date: 2025-08-11 14:40
*/
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1001,10001,103,7,102,5,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1001,10002,103,5,102,3,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1001,10003,203,5,103,3,260);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1001,10004,203,5,103,3,260);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1004,10005,103,6,102,4,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1001,10006,203,5,103,3,260);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1006,10007,103,6,102,4,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1002,10008,103,6,203,4,260);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1003,10009,103,7,102,5,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1002,10010,103,7,102,5,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1004,10011,102,6,103,4,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1006,10012,102,5,103,3,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1001,10013,103,5,102,3,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1004,10014,103,6,102,4,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1002,10015,103,6,102,4,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1005,10016,103,5,102,3,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1002,10017,103,5,102,3,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1003,10018,203,6,103,4,260);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1002,10019,102,5,103,3,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1004,10020,203,5,103,3,260);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1004,10021,103,6,102,4,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1005,10022,103,6,102,4,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1006,10023,102,6,103,4,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1003,10024,203,6,103,4,260);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1001,10025,103,6,102,4,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1003,10026,203,6,103,4,260);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1002,10027,103,6,203,4,260);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1004,10028,102,6,103,4,6);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1005,10029,102,6,203,4,258);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (1002,10030,103,5,203,3,260);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2001,20001,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2001,20002,105,4,201,4,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2001,20003,201,5,105,2,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2001,20004,201,5,101,2,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2001,20005,201,5,105,2,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2001,20006,201,5,105,2,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2002,20007,105,4,201,4,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2002,20008,201,5,105,2,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2002,20009,105,4,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2002,20010,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2003,20011,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2003,20012,105,5,101,3,17);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2003,20013,101,5,201,2,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2003,20014,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2004,20015,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2004,20016,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2004,20017,101,5,201,2,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2004,20018,105,4,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2005,20019,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2005,20020,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2005,20021,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2005,20022,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2006,20023,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2006,20024,201,5,105,2,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2006,20025,105,5,201,2,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2006,20026,105,5,101,2,17);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (2006,20027,105,5,201,2,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3001,30001,102,5,205,3,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3001,30002,102,4,205,2,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3001,30003,102,4,205,3,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3002,30004,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3002,30005,106,4,205,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3002,30006,106,4,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3003,30007,205,5,102,3,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3003,30008,205,4,102,2,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3003,30009,205,4,102,2,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3004,30010,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3004,30011,205,4,106,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3004,30012,205,4,106,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3005,30013,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3005,30014,205,4,106,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3005,30015,205,4,106,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3006,30016,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3006,30017,106,4,205,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3006,30018,106,4,205,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3003,30001,102,5,205,3,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3004,30001,102,5,205,3,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3003,30002,102,4,205,2,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3004,30003,102,4,205,3,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3005,30004,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3004,30005,106,4,205,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3005,30005,106,4,205,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3005,30006,106,4,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3001,30007,205,5,102,3,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3001,30008,205,4,102,2,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3004,30008,205,4,102,2,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3001,30009,205,4,102,2,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3006,30010,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3006,30011,205,4,106,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3006,30012,205,4,106,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3002,30013,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3006,30013,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3006,30014,205,4,106,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3006,30015,205,4,106,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3004,30016,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3004,30017,106,4,205,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (3004,30018,106,4,205,2,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4001,40001,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4003,40001,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4001,40002,102,5,205,3,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4004,40002,102,5,205,3,1026);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4001,40003,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4002,40004,206,6,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4004,40004,206,6,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4002,40005,105,5,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4002,40006,105,5,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4003,40007,102,6,105,3,18);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4003,40008,105,5,102,3,18);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4006,40008,105,5,102,3,18);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4004,40009,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4004,40010,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4005,40011,206,5,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4001,40011,206,5,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4005,40012,105,5,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4006,40013,105,5,102,3,18);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4006,40014,105,5,102,3,18);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4004,40015,102,6,206,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4006,40015,102,6,206,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4006,40016,105,6,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4002,40016,105,6,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4002,40017,206,5,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4005,40017,206,5,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4003,40018,102,5,105,3,18);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (4001,40018,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5001,50001,106,5,104,3,40);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5006,50001,102,5,104,3,10);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5001,50002,104,5,106,3,40);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5001,50003,104,5,106,3,40);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5002,50004,106,5,104,3,40);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5002,50005,106,5,104,3,40);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5002,50006,104,5,106,3,40);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5003,50007,106,5,104,3,40);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5003,50008,106,5,104,3,40);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5003,50009,104,5,106,3,40);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5004,50010,104,5,106,3,40);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5004,50011,104,5,106,3,40);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5004,50012,104,5,102,3,10);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5005,50013,106,5,102,3,34);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5005,50014,102,5,106,3,34);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5005,50015,102,5,106,3,34);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5006,50016,104,5,102,3,10);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5006,50017,102,5,104,3,10);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (5006,50018,102,5,104,3,10);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6001,60001,105,5,101,3,17);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6006,60001,105,5,101,3,17);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6001,60002,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6004,60002,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6001,60003,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6002,60004,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6004,60004,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6002,60005,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6002,60006,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6003,60006,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6003,60007,101,5,105,3,17);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6003,60008,105,5,101,3,17);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6003,60009,105,5,102,3,18);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6004,60010,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6004,60011,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6004,60012,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6005,60013,105,5,101,3,17);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6005,60014,106,5,105,3,48);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6005,60015,106,5,105,3,48);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6006,60016,105,5,101,3,17);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6006,60017,105,5,102,3,18);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (6006,60018,105,5,101,3,17);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7001,70001,105,5,103,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7001,70002,105,5,103,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7005,70002,103,5,105,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7001,70003,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7004,70003,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7002,70004,103,5,201,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7001,70004,105,5,103,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7002,70005,103,5,201,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7001,70005,105,5,103,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7002,70006,103,5,201,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7001,70006,105,5,103,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7003,70007,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7004,70007,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7003,70008,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7004,70008,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7003,70009,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7004,70009,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7004,70010,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7001,70010,105,5,103,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7004,70011,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7001,70011,105,5,103,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7004,70012,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7001,70012,105,5,103,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7005,70013,103,5,105,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7002,70013,103,5,201,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7005,70014,103,5,105,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7002,70014,103,5,201,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7005,70015,103,5,105,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7002,70015,103,5,201,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7006,70016,201,5,103,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7005,70016,103,5,105,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7006,70017,201,5,103,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7005,70017,103,5,105,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7006,70018,201,5,103,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (7005,70018,103,5,105,3,20);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8001,80001,103,5,101,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8001,80002,103,5,101,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8001,80003,103,5,101,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8001,80004,103,5,101,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8001,80005,103,5,101,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8002,80006,103,5,201,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8002,80007,103,5,201,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8002,80008,103,5,201,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8002,80009,103,5,201,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8002,80010,103,5,201,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8003,80011,103,5,101,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8003,80012,103,5,101,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8003,80013,103,5,101,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8003,80014,103,5,101,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8003,80015,103,5,101,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8004,80016,101,5,103,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8004,80017,101,5,103,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8004,80018,101,5,103,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8004,80019,101,5,103,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8004,80020,101,5,103,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8005,80021,201,5,103,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8005,80022,201,5,103,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8005,80023,201,5,103,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8005,80024,201,5,103,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8005,80025,201,5,103,3,68);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8006,80026,101,5,103,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8006,80027,101,5,103,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8006,80028,101,5,103,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8006,80029,101,5,103,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (8006,80030,101,5,103,3,5);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9001,90001,205,5,104,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9002,90001,205,5,104,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9002,90002,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9001,90002,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9001,90003,104,5,205,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9006,90003,104,5,205,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9001,90004,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9002,90004,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9004,90005,104,5,205,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9001,90005,104,5,205,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9004,90006,104,5,205,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9003,90007,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9006,90007,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9003,90008,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9006,90008,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9005,90009,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9005,90010,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9002,90010,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9002,90011,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9004,90012,104,5,205,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9001,90012,104,5,205,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9004,90013,104,5,205,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9003,90014,205,5,104,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9006,90014,205,5,104,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9006,90015,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9003,90015,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9002,90016,205,5,104,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9001,90016,205,5,104,3,1032);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9003,90017,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9006,90017,106,5,205,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9002,90018,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (9005,90018,205,5,106,3,1056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10001,100001,202,5,102,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10005,100001,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10001,100002,202,5,102,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10004,100002,203,5,102,3,258);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10001,100003,202,5,102,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10006,100003,102,5,203,3,258);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10002,100004,203,5,202,3,384);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10004,100004,203,5,102,3,258);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10002,100005,203,5,202,3,384);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10002,100006,203,5,202,3,384);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10003,100007,202,5,203,3,384);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10003,100008,202,5,203,3,384);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10003,100009,202,5,203,3,384);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10004,100010,203,5,102,3,258);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10001,100010,202,5,102,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10004,100011,203,5,102,3,258);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10004,100012,203,5,102,3,258);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10005,100013,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10005,100014,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10005,100015,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10006,100016,102,5,203,3,258);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10006,100017,102,5,203,3,258);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10005,100017,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10006,100018,102,5,203,3,258);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (10003,100018,202,5,203,3,384);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11001,110001,202,5,102,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11003,110001,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11001,110002,202,5,102,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11005,110002,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11001,110003,202,5,102,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11002,110003,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11002,110004,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11004,110004,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11002,110005,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11006,110005,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11002,110006,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11005,110006,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11003,110007,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11005,110007,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11003,110008,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11001,110008,202,5,102,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11003,110009,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11004,110009,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11004,110010,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11006,110010,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11004,110011,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11001,110011,202,5,102,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11004,110012,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11002,110012,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11005,110013,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11003,110013,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11005,110014,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11006,110014,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11005,110015,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11001,110015,202,5,102,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11006,110016,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11004,110016,206,5,102,3,2050);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11006,110017,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11002,110017,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11006,110018,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (11005,110018,102,5,202,3,130);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12001,120001,106,5,201,3,96);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12002,120001,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12001,120002,106,5,201,3,96);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12005,120002,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12001,120003,106,5,201,3,96);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12006,120003,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12001,120004,106,5,201,3,96);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12003,120004,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12001,120005,106,5,201,3,96);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12006,120005,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12002,120006,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12006,120006,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12002,120007,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12006,120007,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12002,120008,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12003,120008,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12002,120009,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12006,120009,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12002,120010,201,5,101,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12001,120010,106,5,201,3,96);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12003,120011,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12003,120012,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12001,120012,106,5,201,3,96);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12003,120013,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12005,120013,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12003,120014,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12003,120015,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12004,120016,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12005,120016,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12004,120017,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12004,120018,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12005,120018,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12004,120019,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12004,120020,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12005,120021,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12005,120022,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12005,120023,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12005,120024,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12005,120025,106,5,101,3,33);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12006,120026,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12006,120027,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12006,120028,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12006,120029,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (12006,120030,101,5,201,3,65);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13001,130001,206,5,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13001,130002,105,5,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13001,130003,206,5,104,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13003,130003,206,5,104,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13005,130003,206,5,104,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13002,130004,104,5,206,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13002,130005,104,5,206,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13002,130006,104,5,105,3,24);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13003,130007,206,5,104,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13003,130008,104,5,206,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13003,130009,105,5,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13001,130010,105,5,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13002,130010,105,5,104,3,24);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13003,130010,105,5,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13005,130010,105,5,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13004,130011,206,5,104,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13004,130012,206,5,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13004,130013,105,5,104,3,24);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13004,130014,105,5,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13005,130015,206,5,104,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13005,130016,206,5,104,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13005,130017,206,5,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13005,130018,105,5,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13006,130019,104,5,206,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13006,130020,105,5,104,3,24);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13006,130021,105,5,104,3,24);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13006,130022,105,5,104,3,24);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13006,130023,105,5,104,3,24);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13001,130024,206,5,104,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13002,130024,206,5,104,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13003,130024,206,5,104,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (13005,130024,206,5,104,3,2056);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14001,140001,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14002,140001,105,5,202,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14003,140001,206,5,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14004,140001,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14005,140001,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14006,140001,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14001,140002,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14002,140002,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14003,140002,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14004,140002,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14005,140002,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14006,140002,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14001,140003,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14003,140003,206,5,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14004,140003,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14005,140003,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14001,140004,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14002,140004,105,5,202,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14003,140004,105,5,202,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14004,140004,105,5,202,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14005,140004,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14006,140004,105,5,202,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14003,140005,206,5,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14001,140005,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14005,140005,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14001,140006,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14002,140006,105,5,202,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14004,140006,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14006,140006,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14001,140007,202,5,206,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14002,140007,105,5,202,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14006,140007,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14001,140008,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14005,140008,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14006,140008,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14002,140009,105,5,202,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14003,140009,105,5,206,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14006,140009,105,5,202,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14001,140010,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14003,140010,206,5,105,3,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14004,140010,202,5,105,3,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14005,140010,206,5,202,3,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14001,140011,206,50,202,25,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14002,140012,105,50,202,25,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14003,140013,206,50,105,25,2064);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14004,140014,202,50,105,25,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14005,140015,206,50,202,25,2176);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (14006,140016,202,50,105,25,144);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15001,150001,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15001,150002,201,5,204,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15001,150003,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15001,150004,204,3,105,2,528);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15001,150005,105,5,204,3,528);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15001,150014,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15001,150015,201,5,204,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15002,150001,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15002,150002,201,5,204,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15002,150003,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15002,150006,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15002,150007,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15002,150016,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15002,150017,201,5,105,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15003,150001,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15003,150002,201,5,204,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15003,150003,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15003,150008,204,5,105,3,528);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15003,150009,204,5,105,3,528);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15003,150018,204,5,105,3,528);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15003,150019,204,5,105,3,528);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15004,150001,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15004,150002,201,5,204,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15004,150003,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15004,150010,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15004,150011,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15004,150020,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15004,150021,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15005,150001,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15005,150002,201,5,204,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15005,150003,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15005,150012,105,5,204,3,528);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15005,150013,204,5,105,3,528);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15005,150022,204,5,105,3,528);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15005,150023,105,5,204,3,528);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15006,150001,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15006,150002,201,5,204,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15006,150003,204,5,201,3,576);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15006,150010,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15006,150011,105,5,201,3,80);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15006,150024,204,5,105,3,528);
INSERT INTO main_sub_quest (main_quest_id,sub_quest_id,attribute1,exp1,attribute2,exp2,linked_attribute) VALUES (15006,150025,204,5,105,3,528);


-- 닉네임 생성 형용사
INSERT INTO public.nickname_generator(
    type, name)
VALUES ('ADJECTIVE','부지런한'),
       ('ADJECTIVE','귀여운'),
       ('ADJECTIVE','빠른'),
       ('ADJECTIVE','행복한'),
       ('ADJECTIVE','용감한'),
       ('ADJECTIVE','멋진'),
       ('ADJECTIVE','똑똑한'),
       ('ADJECTIVE','느긋한'),
       ('ADJECTIVE','시원한'),
       ('ADJECTIVE','밝은'),
       ('ADJECTIVE','즐거운'),
       ('ADJECTIVE','활기찬'),
       ('ADJECTIVE','사랑스러운'),
       ('ADJECTIVE','부드러운'),
       ('ADJECTIVE','친절한'),
       ('ADJECTIVE','상쾌한'),
       ('ADJECTIVE','건강한'),
       ('ADJECTIVE','유쾌한'),
       ('ADJECTIVE','씩씩한'),
       ('ADJECTIVE','깔끔한'),
       ('ADJECTIVE','섬세한'),
       ('ADJECTIVE','성실한'),
       ('ADJECTIVE','다정한'),
       ('ADJECTIVE','재밌는'),
       ('ADJECTIVE','화사한'),
       ('ADJECTIVE','감성적인'),
       ('ADJECTIVE','편안한'),
       ('ADJECTIVE','자유로운'),
       ('ADJECTIVE','활발한'),
       ('ADJECTIVE','지혜로운'),
       ('ADJECTIVE','슬기로운'),
       ('ADJECTIVE','근사한'),
       ('ADJECTIVE','당당한'),
       ('ADJECTIVE','정직한'),
       ('ADJECTIVE','따스한'),
       ('ADJECTIVE','포근한'),
       ('ADJECTIVE','용기있는'),
       ('ADJECTIVE','순수한'),
       ('ADJECTIVE','정다운'),
       ('ADJECTIVE','행운의'),
       ('ADJECTIVE','멋쟁이'),
       ('ADJECTIVE','고운'),
       ('ADJECTIVE','매력적인'),
       ('ADJECTIVE','화끈한'),
       ('ADJECTIVE','사랑스러움'),
       ('ADJECTIVE','신나는'),
       ('ADJECTIVE','신비로운'),
       ('ADJECTIVE','깜찍한'),
       ('ADJECTIVE','명랑한'),
       ('ADJECTIVE','상냥한'),
       ('ADJECTIVE','풍성한'),
       ('ADJECTIVE','듬직한'),
       ('ADJECTIVE','소중한'),
       ('ADJECTIVE','달콤한'),
       ('ADJECTIVE','예쁜'),
       ('ADJECTIVE','귀여움'),
       ('ADJECTIVE','반짝이는'),
       ('ADJECTIVE','평온한'),
       ('ADJECTIVE','강한'),
       ('ADJECTIVE','용맹한'),
       ('ADJECTIVE','기분좋은'),
       ('ADJECTIVE','만족스러운'),
       ('ADJECTIVE','창의적인'),
       ('ADJECTIVE','독특한'),
       ('ADJECTIVE','활달한'),
       ('ADJECTIVE','밝음'),
       ('ADJECTIVE','부유한'),
       ('ADJECTIVE','성공한'),
       ('ADJECTIVE','영리한'),
       ('ADJECTIVE','다재다능한'),
       ('ADJECTIVE','재치있는'),
       ('ADJECTIVE','똑부러진'),
       ('ADJECTIVE','다복한'),
       ('ADJECTIVE','참신한'),
       ('ADJECTIVE','신선한'),
       ('ADJECTIVE','발랄한'),
       ('ADJECTIVE','기특한'),
       ('ADJECTIVE','고요한'),
       ('ADJECTIVE','차분한'),
       ('ADJECTIVE','매혹적인'),
       ('ADJECTIVE','따뜻한'),
       ('ADJECTIVE','은은한'),
       ('ADJECTIVE','정감있는'),
       ('ADJECTIVE','순진한'),
       ('ADJECTIVE','유연한'),
       ('ADJECTIVE','너그러운'),
       ('ADJECTIVE','센스있는'),
       ('ADJECTIVE','쾌활한'),
       ('ADJECTIVE','사랑받는'),
       ('ADJECTIVE','웃음많은'),
       ('ADJECTIVE','호감가는'),
       ('ADJECTIVE','감미로운'),
       ('ADJECTIVE','온화한'),
       ('ADJECTIVE','기운찬'),
       ('ADJECTIVE','사랑하는'),
       ('ADJECTIVE','든든한'),
       ('ADJECTIVE','정겨운'),
       ( 'NOUN','하늘'),
       ( 'NOUN','바다'),
       ( 'NOUN','산'),
       ( 'NOUN','강'),
       ( 'NOUN','꽃'),
       ( 'NOUN','나무'),
       ( 'NOUN','구름'),
       ( 'NOUN','달'),
       ( 'NOUN','별'),
       ( 'NOUN','바람'),
       ( 'NOUN','새'),
       ( 'NOUN','돌'),
       ( 'NOUN','풀'),
       ( 'NOUN','땅'),
       ( 'NOUN','모래'),
       ( 'NOUN','햇살'),
       ( 'NOUN','눈'),
       ( 'NOUN','비'),
       ( 'NOUN','번개'),
       ( 'NOUN','우주'),
       ( 'NOUN','섬'),
       ( 'NOUN','정원'),
       ( 'NOUN','숲'),
       ( 'NOUN','호수'),
       ( 'NOUN','거리'),
       ( 'NOUN','도시'),
       ( 'NOUN','집'),
       ( 'NOUN','방'),
       ( 'NOUN','책'),
       ( 'NOUN','의자'),
       ( 'NOUN','창문'),
       ( 'NOUN','문'),
       ( 'NOUN','길'),
       ( 'NOUN','자동차'),
       ( 'NOUN','자전거'),
       ( 'NOUN','배'),
       ( 'NOUN','열차'),
       ( 'NOUN','비행기'),
       ( 'NOUN','시계'),
       ( 'NOUN','전화'),
       ( 'NOUN','사람'),
       ( 'NOUN','친구'),
       ( 'NOUN','가족'),
       ( 'NOUN','아이'),
       ( 'NOUN','어른'),
       ( 'NOUN','선생님'),
       ( 'NOUN','학생'),
       ( 'NOUN','직장인'),
       ( 'NOUN','의사'),
       ( 'NOUN','간호사'),
       ( 'NOUN','요리사'),
       ( 'NOUN','경찰'),
       ( 'NOUN','소방관'),
       ( 'NOUN','예술가'),
       ( 'NOUN','음악가'),
       ( 'NOUN','배우'),
       ( 'NOUN','작가'),
       ( 'NOUN','기자'),
       ( 'NOUN','농부'),
       ( 'NOUN','상인'),
       ( 'NOUN','고양이'),
       ( 'NOUN','강아지'),
       ( 'NOUN','새끼'),
       ( 'NOUN','말'),
       ( 'NOUN','소'),
       ( 'NOUN','돼지'),
       ( 'NOUN','닭'),
       ( 'NOUN','토끼'),
       ( 'NOUN','곰'),
       ( 'NOUN','사슴'),
       ( 'NOUN','사자'),
       ( 'NOUN','호랑이'),
       ( 'NOUN','늑대'),
       ( 'NOUN','여우'),
       ( 'NOUN','원숭이'),
       ( 'NOUN','코끼리'),
       ( 'NOUN','기린'),
       ( 'NOUN','악어'),
       ( 'NOUN','거북이'),
       ( 'NOUN','상어'),
       ( 'NOUN','과일'),
       ( 'NOUN','사과'),
       ( 'NOUN','망고'),
       ( 'NOUN','포도'),
       ( 'NOUN','딸기'),
       ( 'NOUN','수박'),
       ( 'NOUN','참외'),
       ( 'NOUN','바나나'),
       ( 'NOUN','복숭아'),
       ( 'NOUN','감'),
       ( 'NOUN','음악'),
       ( 'NOUN','노래'),
       ( 'NOUN','춤'),
       ( 'NOUN','연주'),
       ( 'NOUN','그림'),
       ( 'NOUN','사진'),
       ( 'NOUN','영화'),
       ( 'NOUN','연극'),
       ( 'NOUN','소설'),
       ( 'NOUN','시');

INSERT INTO public.tier_level(
    level, xp_required, grade, level_output)
VALUES (1,	2	, 'BRONZE'	, 1),
       (2,	4	, 'BRONZE'	, 2),
       (3,	6	, 'BRONZE'	, 3),
       (4,	8	, 'BRONZE'	, 4),
       (5,	10	, 'BRONZE'	, 5),
       (6,	12	, 'BRONZE'	, 6),
       (7,	14	, 'BRONZE'	, 7),
       (8,	16	, 'BRONZE'	, 8),
       (9,	18	, 'BRONZE'	, 9),
       (10,	21	, 'BRONZE'	, 10),
       (11,	24	, 'SILVER'	, 1),
       (12,	27	, 'SILVER'	, 2),
       (13,	30	, 'SILVER'	, 3),
       (14,	33	, 'SILVER'	, 4),
       (15,	36	, 'SILVER'	, 5),
       (16,	39	, 'SILVER'	, 6),
       (17,	42	, 'SILVER'	, 7),
       (18,	45	, 'SILVER'	, 8),
       (19,	48	, 'SILVER'	, 9),
       (20,	53	, 'SILVER'	, 10),
       (21,	58	, 'GOLD'	, 1),
       (22,	63	, 'GOLD'	, 2),
       (23,	68	, 'GOLD'	, 3),
       (24,	73	, 'GOLD'	, 4),
       (25,	78	, 'GOLD'	, 5),
       (26,	83	, 'GOLD'	, 6),
       (27,	88	, 'GOLD'	, 7),
       (28,	93	, 'GOLD'	, 8),
       (29,	98	, 'GOLD'	, 9),
       (30,	105	, 'GOLD'	, 10),
       (31,	115	, 'PLATINUM'	, 1),
       (32,	125	, 'PLATINUM'	, 2),
       (33,	135	, 'PLATINUM'	, 3),
       (34,	145	, 'PLATINUM'	, 4),
       (35,	155	, 'PLATINUM'	, 5),
       (36,	165	, 'PLATINUM'	, 6),
       (37,	175	, 'PLATINUM'	, 7),
       (38,	185	, 'PLATINUM'	, 8),
       (39,	195	, 'PLATINUM'	, 9),
       (40,	207	, 'PLATINUM'	, 10),
       (41,	219	, 'DIA'	, 1),
       (42,	231	, 'DIA'	, 2),
       (43,	243	, 'DIA'	, 3),
       (44,	255	, 'DIA'	, 4),
       (45,	267	, 'DIA'	, 5),
       (46,	279	, 'DIA'	, 6),
       (47,	291	, 'DIA'	, 7),
       (48,	303	, 'DIA'	, 8),
       (49,	315	, 'DIA'	, 9),
       (50,	100314	, 'DIA'	, 10);

