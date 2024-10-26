CREATE TABLE public.tab_profile (
    id serial NOT NULL,
    name varchar(25) NULL,
	label varchar(50) NULL,
	description varchar(100) NULL,
	CONSTRAINT pk_profile PRIMARY KEY (id)
);

INSERT INTO public.tab_profile (name, "label", description) VALUES('MANAGER','Perfil Gerente', 'Acesso as funcionalidades administrativas doo sistema');
INSERT INTO public.tab_profile (name, "label", description) VALUES('FORMALIZATION', 'Perfil Formalização', 'Acesso as funcionalidades de cadastros do sistema');

CREATE TABLE public.tab_profile_role (
    id serial NOT NULL,
	role varchar(16) NOT NULL,
	profile_id integer NOT NULL,
	CONSTRAINT pk_profile_role PRIMARY KEY (id)
);
--role é a composição dos enums: Functionality_Operation
INSERT INTO public.tab_profile_role (role,profile_id) VALUES('USERS_MENU',1); --'MANAGER'
INSERT INTO public.tab_profile_role (role,profile_id) VALUES('USERS_FULL',1);
INSERT INTO public.tab_profile_role (role,profile_id) VALUES('CUSTOMERS_MENU',1);
INSERT INTO public.tab_profile_role (role,profile_id) VALUES('CUSTOMERS_SELECT',1);

INSERT INTO public.tab_profile_role (role,profile_id) VALUES('CUSTOMERS_MENU',2); --'FORMALIZATION'
INSERT INTO public.tab_profile_role (role,profile_id) VALUES('CUSTOMERS_FULL',2);

--INSERT INTO public.tab_profile_role (role,profile) VALUES('PRODUCTS_MENU','FORMALIZATION');
--INSERT INTO public.tab_profile_role (role,profile) VALUES('PRODUCTS_FULL','FORMALIZATION');


CREATE TABLE public.tab_user (
	login varchar(10) NOT NULL,
	password varchar(50) NULL,
	profile_id varchar(5) NULL,
	CONSTRAINT pk_user PRIMARY KEY (login)
);

INSERT INTO public.tab_user (login, password, profile_id) VALUES('gleyson','IzaTec@2024',1); --'MANAGER'
INSERT INTO public.tab_user (login, password, profile_id) VALUES('izabelly','IzaTec@2024',2);--'FORMALIZATION'

CREATE TABLE public.tab_menu (
	id serial not null,
	label varchar(40) NOT NULL,
	level varchar(16) NOT NULL,
	role varchar(16) NULL,
	parent int NULL,
	CONSTRAINT pk_menu PRIMARY KEY (id)
);

INSERT INTO public.tab_menu ("label", "level", "role", parent) VALUES('-', '00', NULL, NULL);--id=1
INSERT INTO public.tab_menu ("label", "level", "role", parent) VALUES('Dashboard', '00.01', 'DASH', 1);--id=2
INSERT INTO public.tab_menu ("label", "level", "role", parent) VALUES('Cadastros', '00.02', NULL, 1);--id=3
INSERT INTO public.tab_menu ("label", "level", "role", parent) VALUES('Clientes', '00.02.01', 'CLI', 3);--id=4
INSERT INTO public.tab_menu ("label", "level", "role", parent) VALUES('Produtos', '00.02.02', 'PRD', 3);--id=5

-- atualizados via aplicação
CREATE TABLE public.tab_customer (
      id serial not null,
      name varchar(50) NOT NULL,
      CONSTRAINT pk_customer PRIMARY KEY (id)
);
CREATE TABLE public.tab_product (
     id serial not null,
     name varchar(50) NOT NULL,
     CONSTRAINT pk_product PRIMARY KEY (id)
);



