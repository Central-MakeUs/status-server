CREATE TABLE public.users (
                              created_at timestamp(6) without time zone,
                              id bigint NOT NULL,
                              updated_at timestamp(6) without time zone,
                              nickname character varying(255) NOT NULL,
                              provider_id character varying(255) NOT NULL,
                              provider_type character varying(255) NOT NULL,
                              status character varying(255) NOT NULL,
                              tag character varying(255) NOT NULL,
                              profile_image character varying(255),
                              CONSTRAINT users_provider_type_check CHECK (((provider_type)::text = ANY ((ARRAY['KAKAO'::character varying, 'GOOGLE'::character varying, 'APPLE'::character varying])::text[]))),
    CONSTRAINT users_status_check CHECK (((status)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying])::text[])))
);

CREATE TABLE public.users_agreements (
                                         id bigint NOT NULL,
                                         created_at timestamp(6) without time zone NOT NULL,
                                         terms_id bigint NOT NULL,
                                         users_id bigint NOT NULL
);


ALTER TABLE public.users_agreements ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_agreements_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


CREATE TABLE public.users_attribute_log (
                                            attribute_id integer NOT NULL,
                                            exp integer NOT NULL,
                                            created_at timestamp(6) without time zone NOT NULL,
                                            id bigint NOT NULL,
                                            main_quest_id bigint,
                                            sub_quest_id bigint,
                                            users_id bigint NOT NULL,
                                            source_type character varying(255) NOT NULL,
                                            CONSTRAINT users_attribute_log_source_type_check CHECK (((source_type)::text = ANY ((ARRAY['MAINQUEST'::character varying, 'SUBQUESTLOG'::character varying])::text[])))
);

ALTER TABLE public.users_attribute_log ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_attribute_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE public.users_attribute_progress (
                                                 attribute_id integer NOT NULL,
                                                 exp integer NOT NULL,
                                                 level integer NOT NULL,
                                                 id bigint NOT NULL,
                                                 updated_at timestamp(6) without time zone NOT NULL,
                                                 users_id bigint NOT NULL
);



ALTER TABLE public.users_attribute_progress ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_attribute_progress_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


ALTER TABLE public.users ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


CREATE TABLE public.users_main_quest (
                                         attribute1 integer NOT NULL,
                                         attribute2 integer,
                                         attributes integer NOT NULL,
                                         exp1 integer NOT NULL,
                                         exp2 integer,
                                         created_at timestamp(6) without time zone NOT NULL,
                                         end_date date NOT NULL,
                                         id bigint NOT NULL,
                                         main_quest_id bigint NOT NULL,
                                         start_date date NOT NULL,
                                         users_id bigint NOT NULL,
                                         status character varying(255) NOT NULL,
                                         title character varying(255) NOT NULL,
                                         CONSTRAINT users_main_quest_status_check CHECK (((status)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying, 'COMPLETED'::character varying, 'DELETED'::character varying])::text[])))
);


ALTER TABLE public.users_main_quest ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_main_quest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


CREATE TABLE public.users_sub_quest (
                                        action_unit_num integer NOT NULL,
                                        attribute1 integer NOT NULL,
                                        attribute2 integer,
                                        exp1 integer NOT NULL,
                                        exp2 integer,
                                        id bigint NOT NULL,
                                        main_quest_id bigint NOT NULL,
                                        sub_quest_id bigint NOT NULL,
                                        users_id bigint NOT NULL,
                                        action_unit_type character varying(255) NOT NULL,
                                        description character varying(255) NOT NULL,
                                        frequency_type character varying(255) NOT NULL,
                                        status character varying(255),
                                        required_log integer NOT NULL,
                                        CONSTRAINT users_sub_quest_action_unit_type_check CHECK (((action_unit_type)::text = ANY ((ARRAY['TIME_SECOND'::character varying, 'TIME_MINUTE'::character varying, 'TIME_HOUR'::character varying, 'NUMBER_1'::character varying, 'NUMBER_2'::character varying, 'NUMBER_3'::character varying, 'DISTANCE'::character varying, 'ONCE'::character varying])::text[]))),
    CONSTRAINT users_sub_quest_frequency_type_check CHECK (((frequency_type)::text = ANY ((ARRAY['DAILY'::character varying, 'WEEKLY_1'::character varying, 'WEEKLY_2'::character varying, 'WEEKLY_3'::character varying, 'WEEKLY_4'::character varying, 'WEEKLY_5'::character varying, 'WEEKLY_6'::character varying, 'MONTHLY_1'::character varying, 'MONTHLY_2'::character varying, 'MONTHLY_3'::character varying, 'MONTHLY_4'::character varying])::text[])))
);


ALTER TABLE public.users_sub_quest ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_sub_quest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);



CREATE TABLE public.users_sub_quest_log (
                                            created_at timestamp(6) without time zone NOT NULL,
                                            id bigint NOT NULL,
                                            updated_at timestamp(6) without time zone NOT NULL,
                                            users_sub_quest_id bigint NOT NULL,
                                            difficulty character varying(255) NOT NULL,
                                            memo character varying(255),
                                            CONSTRAINT users_sub_quest_log_difficulty_check CHECK (((difficulty)::text = ANY ((ARRAY['EASY'::character varying, 'NORMAL'::character varying, 'HARD'::character varying])::text[])))
);

ALTER TABLE public.users_sub_quest_log ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_sub_quest_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


ALTER TABLE ONLY public.users_agreements
    ADD CONSTRAINT users_agreements_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.users_attribute_log
    ADD CONSTRAINT users_attribute_log_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.users_attribute_progress
    ADD CONSTRAINT users_attribute_progress_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.users_main_quest
    ADD CONSTRAINT users_main_quest_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.users_sub_quest_log
    ADD CONSTRAINT users_sub_quest_log_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.users_sub_quest
    ADD CONSTRAINT users_sub_quest_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.users_main_quest
    ADD CONSTRAINT fk19vjiaxypx988grxcmlhwc4nu FOREIGN KEY (users_id) REFERENCES public.users(id);


ALTER TABLE ONLY public.users_sub_quest
    ADD CONSTRAINT fk1jt4yt80f9jo9uomu6cmdjidn FOREIGN KEY (users_id) REFERENCES public.users(id);


ALTER TABLE ONLY public.users_main_quest
    ADD CONSTRAINT fk1xhde61vwk9vcekaib8d4p91e FOREIGN KEY (main_quest_id) REFERENCES public.main_quest(id);


ALTER TABLE ONLY public.users_agreements
    ADD CONSTRAINT fk219sefqopqhot3ud36yerrpj0 FOREIGN KEY (users_id) REFERENCES public.users(id);


ALTER TABLE ONLY public.users_main_quest
    ADD CONSTRAINT fk793qexu11yseq2o70h00cw6p5 FOREIGN KEY (attribute2) REFERENCES public.attribute(id);


ALTER TABLE ONLY public.users_sub_quest
    ADD CONSTRAINT fk7l5t7rvhf9p4qxxp8wd5kb0f6 FOREIGN KEY (sub_quest_id) REFERENCES public.sub_quest(id);


ALTER TABLE ONLY public.users_attribute_log
    ADD CONSTRAINT fk7y00nifsiqy5kq2cjihs256t5 FOREIGN KEY (main_quest_id) REFERENCES public.users_main_quest(id);


ALTER TABLE ONLY public.users_attribute_progress
    ADD CONSTRAINT fk8yxh36pwqe841n7wrjusrxqap FOREIGN KEY (users_id) REFERENCES public.users(id);


ALTER TABLE ONLY public.users_sub_quest
    ADD CONSTRAINT fkfbyw9fsq3sajnwsa888dge52g FOREIGN KEY (main_quest_id) REFERENCES public.users_main_quest(id);


ALTER TABLE ONLY public.users_sub_quest
    ADD CONSTRAINT fkjs8ly2mtxyfqgx8f3pysikp3y FOREIGN KEY (attribute1) REFERENCES public.attribute(id);


ALTER TABLE ONLY public.users_agreements
    ADD CONSTRAINT fkl2v2fls524ie5n048o39mpidb FOREIGN KEY (terms_id) REFERENCES public.terms_and_conditions(id);


ALTER TABLE ONLY public.users_sub_quest
    ADD CONSTRAINT fkmiqkrpub5x446pwqhpeh77co1 FOREIGN KEY (attribute2) REFERENCES public.attribute(id);


ALTER TABLE ONLY public.users_attribute_log
    ADD CONSTRAINT fknafmlydtj2i77y7iidrs9i671 FOREIGN KEY (attribute_id) REFERENCES public.attribute(id);


ALTER TABLE ONLY public.users_attribute_log
    ADD CONSTRAINT fkp0hb2mp65vachgpbp28sq6tmt FOREIGN KEY (users_id) REFERENCES public.users(id);


ALTER TABLE ONLY public.users_main_quest
    ADD CONSTRAINT fkpbc67fn542vq6x7esj3f75imb FOREIGN KEY (attribute1) REFERENCES public.attribute(id);

ALTER TABLE ONLY public.users_sub_quest_log
    ADD CONSTRAINT fkrlwpho2fbntijk3p7jcbctdkb FOREIGN KEY (users_sub_quest_id) REFERENCES public.users_sub_quest(id);

ALTER TABLE ONLY public.users_attribute_progress
    ADD CONSTRAINT fkssi9p9ptkusslwxdcad3l5bpr FOREIGN KEY (attribute_id) REFERENCES public.attribute(id);


ALTER TABLE ONLY public.users_attribute_log
    ADD CONSTRAINT fkt2841qh1k3l6tdflmljg6g741 FOREIGN KEY (sub_quest_id) REFERENCES public.users_sub_quest_log(id);

