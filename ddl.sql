-- Table: public.organization

-- DROP TABLE IF EXISTS public.organization;

CREATE TABLE IF NOT EXISTS public.organization
(
    code character varying(20) COLLATE pg_catalog."default" NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    parent_code character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT org_pkey PRIMARY KEY (code),
    CONSTRAINT organization_ch_1 CHECK (code IS NOT NULL) NOT VALID,
    CONSTRAINT organization_ch_2 CHECK (name IS NOT NULL) NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.organization
    OWNER to root;
	
-- Table: public.position

-- DROP TABLE IF EXISTS public."position";

CREATE TABLE IF NOT EXISTS public."position"
(
    code character varying(20) COLLATE pg_catalog."default" NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    organization_code character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT position_pkey PRIMARY KEY (code),
    CONSTRAINT position_fk_1 FOREIGN KEY (organization_code)
        REFERENCES public.organization (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT position_ch_1 CHECK (code IS NOT NULL) NOT VALID,
    CONSTRAINT position_ch_2 CHECK (organization_code IS NOT NULL) NOT VALID,
    CONSTRAINT position_ch_3 CHECK (name IS NOT NULL) NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."position"
    OWNER to root;
	

-- Table: public.employee

-- DROP TABLE IF EXISTS public.employee;

CREATE TABLE IF NOT EXISTS public.employee
(
    name character varying(50) COLLATE pg_catalog."default",
    position_code character varying(20) COLLATE pg_catalog."default",
    picture character varying(255) COLLATE pg_catalog."default",
    report_to_id integer,
    id integer NOT NULL DEFAULT nextval('member_id_seq'::regclass),
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT employee_fk_1 FOREIGN KEY (position_code)
        REFERENCES public."position" (code) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT employee_ch_1 CHECK (id IS NOT NULL) NOT VALID,
    CONSTRAINT employee_ch_2 CHECK (position_code IS NOT NULL) NOT VALID,
    CONSTRAINT employee_ch_3 CHECK (name IS NOT NULL) NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.employee
    OWNER to root;
	
-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    google_id character varying(255) COLLATE pg_catalog."default",
    role character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uesrs_un_1 UNIQUE (email),
    CONSTRAINT users_ch_1 CHECK (id IS NOT NULL) NOT VALID,
    CONSTRAINT users_ch_2 CHECK (email IS NOT NULL) NOT VALID,
    CONSTRAINT users_ch_4 CHECK (role IS NOT NULL) NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to root;