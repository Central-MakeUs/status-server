-- 카테고리 테이블
CREATE TABLE "category" (
                            "id" BIGSERIAL NOT NULL,
                            "name" TEXT NOT NULL,
                            "attribute" INTEGER NOT NULL
);

-- 능력치 테이블
CREATE TABLE "attribute" (
                             "bit_mask" INTEGER NOT NULL,
                             "attribute_id" INTEGER NOT NULL,
                             "name" TEXT NOT NULL,
                             "type" TEXT NOT NULL,
                             "description" TEXT NOT NULL,
                             "default_level" INTEGER DEFAULT 5 NOT NULL
);

COMMENT ON COLUMN "attribute"."attribute_id" IS 'UNIQUE';

-- 능력치 별 레벨 테이블
CREATE TABLE "attribute_level" (
                                   "type" TEXT NOT NULL,
                                   "level" INTEGER NOT NULL,
                                   "required_exp" INTEGER NOT NULL
);

-- 메인 퀘스트 테이블
CREATE TABLE "main_quest" (
                              "id" BIGSERIAL NOT NULL,
                              "category_id" BIGSERIAL NOT NULL,
                              "title" TEXT NOT NULL,
                              "attributes" INTEGER NOT NULL,
                              "created_at" TIMESTAMP DEFAULT now() NULL
);

-- 서브 퀘스트 테이블
CREATE TABLE "sub_quest" (
                             "id" BIGSERIAL NOT NULL,
                             "main_quest_id" BIGSERIAL NOT NULL,
                             "quest_value_id" BIGSERIAL NOT NULL,
                             "desc" TEXT NOT NULL,
                             "default_frequency" TEXT NOT NULL,
                             "default_repeat" INTEGER NOT NULL,
                             "created_at" TIMESTAMP DEFAULT now() NOT NULL
);

-- 퀘스트 등급 테이블
CREATE TABLE "quest_value" (
                               "id" BIGSERIAL NOT NULL,
                               "name" TEXT NOT NULL,
                               "exp" INTEGER NOT NULL
);

ALTER TABLE "category" ADD CONSTRAINT "PK_CATEGORY" PRIMARY KEY ("id");

ALTER TABLE "attribute" ADD CONSTRAINT "PK_ATTRIBUTE" PRIMARY KEY ("bit_mask");

ALTER TABLE "attribute_level" ADD CONSTRAINT "PK_ATTRIBUTE_LEVEL" PRIMARY KEY ("type", "level");

ALTER TABLE "main_quest" ADD CONSTRAINT "PK_MAIN_QUEST" PRIMARY KEY ("id");

ALTER TABLE "sub_quest" ADD CONSTRAINT "PK_SUB_QUEST" PRIMARY KEY ("id");

ALTER TABLE "quest_value" ADD CONSTRAINT "PK_QUEST_VALUE" PRIMARY KEY ("id");

ALTER TABLE "main_quest" ADD CONSTRAINT "FK_category_TO_main_quest_1"
    FOREIGN KEY ("category_id") REFERENCES "category" ("id");

ALTER TABLE "sub_quest" ADD CONSTRAINT "FK_main_quest_TO_sub_quest_1"
    FOREIGN KEY ("main_quest_id") REFERENCES "main_quest" ("id");

ALTER TABLE "sub_quest" ADD CONSTRAINT "FK_quest_value_TO_sub_quest_1"
    FOREIGN KEY ("quest_value_id") REFERENCES "quest_value" ("id");
