--
-- PostgreSQL database dump
--

-- Dumped from database version 10.23
-- Dumped by pg_dump version 10.23

-- Started on 2024-05-21 19:51:52

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3042 (class 1262 OID 132430)
-- Name: loja-virtual-mentoria; Type: DATABASE; Schema: -; Owner: postgres
--

--CREATE DATABASE "loja-virtual-mentoria" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE "loja_virtual_teste" OWNER TO postgres;

--\connect -reuse-previous=on "dbname='loja-virtual-mentoria'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3044 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 236 (class 1255 OID 140774)
-- Name: validachavepessoa(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validachavepessoa() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare existe integer;

/*NEW -> Carrega os dados que estão sendo inseridos e atualizados*/
BEGIN
	existe = (select count(1) from pessoa_fisica where id = NEW.pessoa_id);
	if(existe <= 0) then
		existe= (select count(1) from pessoa_juridica where id = NEW.pessoa_id);

	if(existe <= 0) then
		RAISE EXCEPTION 'Não foi encontrado o ID e PK da pessoa para realizar a associação do cadastro';
end if;
end if;
return new;
END;
$$;


ALTER FUNCTION public.validachavepessoa() OWNER TO postgres;

--
-- TOC entry 237 (class 1255 OID 140961)
-- Name: validachavepessoa2(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validachavepessoa2() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare existe integer;

/*NEW -> Carrega os dados que estão sendo inseridos e atualizados*/
BEGIN
	existe = (select count(1) from pessoa_fisica where id = NEW.pessoa_fornecedor_id);
	if(existe <= 0) then
		existe= (select count(1) from pessoa_juridica where id = NEW.pessoa_fornecedor_id);

	if(existe <= 0) then
		RAISE EXCEPTION 'Não foi encontrado o ID e PK da pessoa para realizar a associação do cadastro';
end if;
end if;
return new;
END;
$$;


ALTER FUNCTION public.validachavepessoa2() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 198 (class 1259 OID 132438)
-- Name: acesso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.acesso (
                               id bigint NOT NULL,
                               descricao character varying(255) NOT NULL
);


ALTER TABLE public.acesso OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 140597)
-- Name: avaliacao_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.avaliacao_produto (
                                          id bigint NOT NULL,
                                          pessoa_id bigint NOT NULL,
                                          produto_id bigint NOT NULL,
                                          nota integer NOT NULL,
                                          descricao character varying(255) NOT NULL
);


ALTER TABLE public.avaliacao_produto OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 132443)
-- Name: categoria_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria_produto (
                                          id bigint NOT NULL,
                                          nome_descricao character varying(255) NOT NULL
);


ALTER TABLE public.categoria_produto OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 132529)
-- Name: conta_pagar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conta_pagar (
                                    id bigint NOT NULL,
                                    data_pagamento date,
                                    valor_desconto numeric(19,2),
                                    pessoa_id bigint NOT NULL,
                                    pessoa_fornecedor_id bigint NOT NULL,
                                    data_vencimento date NOT NULL,
                                    descricao character varying(255) NOT NULL,
                                    status_conta_pagar character varying(255) NOT NULL,
                                    valor_total numeric(19,2) NOT NULL
);


ALTER TABLE public.conta_pagar OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 132510)
-- Name: conta_receber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conta_receber (
                                      id bigint NOT NULL,
                                      data_pagamento date,
                                      valor_desconto numeric(19,2),
                                      pessoa_id bigint NOT NULL,
                                      data_vencimento date NOT NULL,
                                      descricao character varying(255) NOT NULL,
                                      status_conta_receber character varying(255) NOT NULL,
                                      valor_total numeric(19,2) NOT NULL
);


ALTER TABLE public.conta_receber OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 140445)
-- Name: cupom_desconto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cupom_desconto (
                                       id bigint NOT NULL,
                                       valor_porcento_desc numeric(19,2),
                                       valor_real_desc numeric(19,2),
                                       cod_descricao character varying(255) NOT NULL,
                                       data_validade_cupom date NOT NULL
);


ALTER TABLE public.cupom_desconto OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 140609)
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.endereco (
                                 id bigint NOT NULL,
                                 bairro character varying(255) NOT NULL,
                                 cep character varying(255) NOT NULL,
                                 cidade character varying(255) NOT NULL,
                                 complemento character varying(255),
                                 numero character varying(255) NOT NULL,
                                 rua_lograd character varying(255) NOT NULL,
                                 tipo_endereco character varying(255) NOT NULL,
                                 uf character varying(255) NOT NULL,
                                 pessoa_id bigint NOT NULL
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 140627)
-- Name: forma_pagamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.forma_pagamento (
                                        id bigint NOT NULL,
                                        descricao character varying(255) NOT NULL
);


ALTER TABLE public.forma_pagamento OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 140637)
-- Name: imagem_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.imagem_produto (
                                       id bigint NOT NULL,
                                       imagem_miniatura text NOT NULL,
                                       imagem_original text NOT NULL,
                                       produto_id bigint NOT NULL
);


ALTER TABLE public.imagem_produto OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 140579)
-- Name: item_venda_loja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_venda_loja (
                                        id bigint NOT NULL,
                                        produto_id bigint NOT NULL,
                                        vd_compra_loja_virtual_id bigint NOT NULL,
                                        quantidade double precision NOT NULL
);


ALTER TABLE public.item_venda_loja OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 132431)
-- Name: marca_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marca_produto (
                                      id bigint NOT NULL,
                                      nome_descricao character varying(255) NOT NULL
);


ALTER TABLE public.marca_produto OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 140650)
-- Name: nota_fiscal_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_fiscal_compra (
                                           id bigint NOT NULL,
                                           data_compra date NOT NULL,
                                           descricao_observacao character varying(255),
                                           numero_nota character varying(255) NOT NULL,
                                           serie_nota character varying(255) NOT NULL,
                                           valor_desconto numeric(19,2),
                                           valoricms numeric(19,2) NOT NULL,
                                           valor_total numeric(19,2) NOT NULL,
                                           conta_pagar_id bigint NOT NULL,
                                           pessoa_id bigint NOT NULL
);


ALTER TABLE public.nota_fiscal_compra OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 140668)
-- Name: nota_fiscal_venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_fiscal_venda (
                                          id bigint NOT NULL,
                                          numero character varying(255) NOT NULL,
                                          pdf text NOT NULL,
                                          serie character varying(255) NOT NULL,
                                          tipo character varying(255) NOT NULL,
                                          xml text NOT NULL,
                                          vd_compra_loja_virtual_id bigint NOT NULL
);


ALTER TABLE public.nota_fiscal_venda OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 140500)
-- Name: nota_item_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_item_produto (
                                          id bigint NOT NULL,
                                          quantidade double precision NOT NULL,
                                          nota_fiscal_compra_id bigint NOT NULL,
                                          produto_id bigint NOT NULL
);


ALTER TABLE public.nota_item_produto OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 132459)
-- Name: pessoa_fisica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pessoa_fisica (
                                      id bigint NOT NULL,
                                      cpf character varying(255) NOT NULL,
                                      data_nascimento date,
                                      email character varying(255) NOT NULL,
                                      nome character varying(255) NOT NULL,
                                      telefone character varying(255) NOT NULL
);


ALTER TABLE public.pessoa_fisica OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 132467)
-- Name: pessoa_juridica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pessoa_juridica (
                                        id bigint NOT NULL,
                                        email character varying(255) NOT NULL,
                                        nome character varying(255) NOT NULL,
                                        telefone character varying(255) NOT NULL,
                                        categoria character varying(255),
                                        cnpj character varying(255) NOT NULL,
                                        inscricao_estadual character varying(255) NOT NULL,
                                        inscricao_municipal character varying(255),
                                        nome_fantasia character varying(255) NOT NULL,
                                        razao_social character varying(255) NOT NULL
);


ALTER TABLE public.pessoa_juridica OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 140686)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto (
                                id bigint NOT NULL,
                                alerta_qtd_estoque boolean,
                                altura double precision NOT NULL,
                                ativo boolean NOT NULL,
                                descricao text NOT NULL,
                                largura double precision NOT NULL,
                                link_video_youtube character varying(255),
                                nome character varying(255) NOT NULL,
                                peso double precision NOT NULL,
                                profundidade double precision NOT NULL,
                                qtd_alerta_estoque integer,
                                qtd_de_clique integer,
                                qtd_estoque integer NOT NULL,
                                tipo_unidade character varying(255) NOT NULL,
                                valor_de_venda numeric(19,2) NOT NULL
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 132475)
-- Name: seq_acesso; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_acesso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_acesso OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 140602)
-- Name: seq_avaliacao_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_avaliacao_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_avaliacao_produto OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 132477)
-- Name: seq_categoria_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_categoria_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_categoria_produto OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 132537)
-- Name: seq_conta_pagar; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_conta_pagar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_conta_pagar OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 132520)
-- Name: seq_conta_receber; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_conta_receber
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_conta_receber OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 140450)
-- Name: seq_cupom_desconto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_cupom_desconto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_cupom_desconto OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 132479)
-- Name: seq_endereco; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_endereco
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_endereco OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 132527)
-- Name: seq_forma_pagamento; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_forma_pagamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_forma_pagamento OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 140478)
-- Name: seq_imagem_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_imagem_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_imagem_produto OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 140584)
-- Name: seq_item_venda_loja; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_item_venda_loja
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_item_venda_loja OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 132436)
-- Name: seq_marca_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_marca_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_marca_produto OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 140493)
-- Name: seq_nota_fiscal_compra; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_fiscal_compra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_fiscal_compra OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 140535)
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_fiscal_venda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_fiscal_venda OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 140505)
-- Name: seq_nota_item_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_item_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_item_produto OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 132481)
-- Name: seq_pessoa; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_pessoa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_pessoa OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 140460)
-- Name: seq_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_produto OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 140525)
-- Name: seq_status_rastreio; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_status_rastreio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_status_rastreio OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 132498)
-- Name: seq_usuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_usuario OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 140542)
-- Name: seq_vd_compra_loja_virtual; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_vd_compra_loja_virtual
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_vd_compra_loja_virtual OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 140517)
-- Name: status_rastreio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status_rastreio (
                                        id bigint NOT NULL,
                                        centro_distribuicao character varying(255),
                                        cidade character varying(255),
                                        estado character varying(255),
                                        status character varying(255),
                                        vd_compra_loja_virtual_id bigint NOT NULL
);


ALTER TABLE public.status_rastreio OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 140715)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
                                id bigint NOT NULL,
                                data_atual_senha date NOT NULL,
                                login character varying(255) NOT NULL,
                                senha character varying(255) NOT NULL,
                                pessoa_id bigint NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 132491)
-- Name: usuarios_acesso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios_acesso (
                                        usuario_id bigint NOT NULL,
                                        acesso_id bigint NOT NULL
);


ALTER TABLE public.usuarios_acesso OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 140728)
-- Name: vd_compra_loja_virtual; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vd_compra_loja_virtual (
                                               id bigint NOT NULL,
                                               data_entrega date NOT NULL,
                                               data_venda date NOT NULL,
                                               dia_entrega integer NOT NULL,
                                               valor_desconto numeric(19,2),
                                               valor_frete numeric(19,2) NOT NULL,
                                               valor_total numeric(19,2) NOT NULL,
                                               cupom_desconto_id bigint,
                                               endereco_cobranca_id bigint NOT NULL,
                                               endereco_entrega_id bigint NOT NULL,
                                               forma_pagamento_id bigint NOT NULL,
                                               nota_fiscal_venda_id bigint NOT NULL,
                                               pessoa_id bigint NOT NULL
);


ALTER TABLE public.vd_compra_loja_virtual OWNER TO postgres;

--
-- TOC entry 2999 (class 0 OID 132438)
-- Dependencies: 198
-- Data for Name: acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3027 (class 0 OID 140597)
-- Dependencies: 226
-- Data for Name: avaliacao_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.avaliacao_produto (id, pessoa_id, produto_id, nota, descricao) VALUES (1, 1, 1, 10, 'teste avaliacao produto trigger');


--
-- TOC entry 3000 (class 0 OID 132443)
-- Dependencies: 199
-- Data for Name: categoria_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3012 (class 0 OID 132529)
-- Dependencies: 211
-- Data for Name: conta_pagar; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3009 (class 0 OID 132510)
-- Dependencies: 208
-- Data for Name: conta_receber; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3014 (class 0 OID 140445)
-- Dependencies: 213
-- Data for Name: cupom_desconto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3029 (class 0 OID 140609)
-- Dependencies: 228
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3030 (class 0 OID 140627)
-- Dependencies: 229
-- Data for Name: forma_pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3031 (class 0 OID 140637)
-- Dependencies: 230
-- Data for Name: imagem_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3025 (class 0 OID 140579)
-- Dependencies: 224
-- Data for Name: item_venda_loja; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2997 (class 0 OID 132431)
-- Dependencies: 196
-- Data for Name: marca_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3032 (class 0 OID 140650)
-- Dependencies: 231
-- Data for Name: nota_fiscal_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3033 (class 0 OID 140668)
-- Dependencies: 232
-- Data for Name: nota_fiscal_venda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3019 (class 0 OID 140500)
-- Dependencies: 218
-- Data for Name: nota_item_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3001 (class 0 OID 132459)
-- Dependencies: 200
-- Data for Name: pessoa_fisica; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pessoa_fisica (id, cpf, data_nascimento, email, nome, telefone) VALUES (1, '9447576754', '2024-06-21', 'jao@gmail.com', 'JoaoLTDA', '21967574433');


--
-- TOC entry 3002 (class 0 OID 132467)
-- Dependencies: 201
-- Data for Name: pessoa_juridica; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3034 (class 0 OID 140686)
-- Dependencies: 233
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.produto (id, alerta_qtd_estoque, altura, ativo, descricao, largura, link_video_youtube, nome, peso, profundidade, qtd_alerta_estoque, qtd_de_clique, qtd_estoque, tipo_unidade, valor_de_venda) VALUES (1, true, 10, true, 'Produto teste', 50.200000000000003, 'youtube.com', 'teste', 50.200000000000003, 80, 1, 50, 1, 'UN', 50.00);


--
-- TOC entry 3021 (class 0 OID 140517)
-- Dependencies: 220
-- Data for Name: status_rastreio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3035 (class 0 OID 140715)
-- Dependencies: 234
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3007 (class 0 OID 132491)
-- Dependencies: 206
-- Data for Name: usuarios_acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3036 (class 0 OID 140728)
-- Dependencies: 235
-- Data for Name: vd_compra_loja_virtual; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3045 (class 0 OID 0)
-- Dependencies: 202
-- Name: seq_acesso; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_acesso', 1, false);


--
-- TOC entry 3046 (class 0 OID 0)
-- Dependencies: 227
-- Name: seq_avaliacao_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_avaliacao_produto', 1, false);


--
-- TOC entry 3047 (class 0 OID 0)
-- Dependencies: 203
-- Name: seq_categoria_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_categoria_produto', 1, false);


--
-- TOC entry 3048 (class 0 OID 0)
-- Dependencies: 212
-- Name: seq_conta_pagar; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_conta_pagar', 1, false);


--
-- TOC entry 3049 (class 0 OID 0)
-- Dependencies: 209
-- Name: seq_conta_receber; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_conta_receber', 1, false);


--
-- TOC entry 3050 (class 0 OID 0)
-- Dependencies: 214
-- Name: seq_cupom_desconto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_cupom_desconto', 1, false);


--
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 204
-- Name: seq_endereco; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_endereco', 1, false);


--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 210
-- Name: seq_forma_pagamento; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_forma_pagamento', 1, false);


--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 216
-- Name: seq_imagem_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_imagem_produto', 1, false);


--
-- TOC entry 3054 (class 0 OID 0)
-- Dependencies: 225
-- Name: seq_item_venda_loja; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_item_venda_loja', 1, false);


--
-- TOC entry 3055 (class 0 OID 0)
-- Dependencies: 197
-- Name: seq_marca_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_marca_produto', 1, false);


--
-- TOC entry 3056 (class 0 OID 0)
-- Dependencies: 217
-- Name: seq_nota_fiscal_compra; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_fiscal_compra', 1, false);


--
-- TOC entry 3057 (class 0 OID 0)
-- Dependencies: 222
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_fiscal_venda', 1, false);


--
-- TOC entry 3058 (class 0 OID 0)
-- Dependencies: 219
-- Name: seq_nota_item_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_item_produto', 1, false);


--
-- TOC entry 3059 (class 0 OID 0)
-- Dependencies: 205
-- Name: seq_pessoa; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_pessoa', 1, false);


--
-- TOC entry 3060 (class 0 OID 0)
-- Dependencies: 215
-- Name: seq_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_produto', 1, false);


--
-- TOC entry 3061 (class 0 OID 0)
-- Dependencies: 221
-- Name: seq_status_rastreio; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_status_rastreio', 1, false);


--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 207
-- Name: seq_usuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_usuario', 1, false);


--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 223
-- Name: seq_vd_compra_loja_virtual; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_vd_compra_loja_virtual', 1, false);


--
-- TOC entry 2803 (class 2606 OID 132442)
-- Name: acesso acesso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acesso
    ADD CONSTRAINT acesso_pkey PRIMARY KEY (id);


--
-- TOC entry 2827 (class 2606 OID 140601)
-- Name: avaliacao_produto avaliacao_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avaliacao_produto
    ADD CONSTRAINT avaliacao_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 2805 (class 2606 OID 132447)
-- Name: categoria_produto categoria_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_produto
    ADD CONSTRAINT categoria_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 2817 (class 2606 OID 132536)
-- Name: conta_pagar conta_pagar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta_pagar
    ADD CONSTRAINT conta_pagar_pkey PRIMARY KEY (id);


--
-- TOC entry 2815 (class 2606 OID 132517)
-- Name: conta_receber conta_receber_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta_receber
    ADD CONSTRAINT conta_receber_pkey PRIMARY KEY (id);


--
-- TOC entry 2819 (class 2606 OID 140449)
-- Name: cupom_desconto cupom_desconto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupom_desconto
    ADD CONSTRAINT cupom_desconto_pkey PRIMARY KEY (id);


--
-- TOC entry 2829 (class 2606 OID 140616)
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- TOC entry 2831 (class 2606 OID 140631)
-- Name: forma_pagamento forma_pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forma_pagamento
    ADD CONSTRAINT forma_pagamento_pkey PRIMARY KEY (id);


--
-- TOC entry 2833 (class 2606 OID 140644)
-- Name: imagem_produto imagem_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagem_produto
    ADD CONSTRAINT imagem_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 2825 (class 2606 OID 140583)
-- Name: item_venda_loja item_venda_loja_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT item_venda_loja_pkey PRIMARY KEY (id);


--
-- TOC entry 2801 (class 2606 OID 132435)
-- Name: marca_produto marca_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca_produto
    ADD CONSTRAINT marca_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 2835 (class 2606 OID 140657)
-- Name: nota_fiscal_compra nota_fiscal_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT nota_fiscal_compra_pkey PRIMARY KEY (id);


--
-- TOC entry 2837 (class 2606 OID 140675)
-- Name: nota_fiscal_venda nota_fiscal_venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT nota_fiscal_venda_pkey PRIMARY KEY (id);


--
-- TOC entry 2821 (class 2606 OID 140504)
-- Name: nota_item_produto nota_item_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT nota_item_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 2807 (class 2606 OID 132466)
-- Name: pessoa_fisica pessoa_fisica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa_fisica
    ADD CONSTRAINT pessoa_fisica_pkey PRIMARY KEY (id);


--
-- TOC entry 2809 (class 2606 OID 132474)
-- Name: pessoa_juridica pessoa_juridica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_pkey PRIMARY KEY (id);


--
-- TOC entry 2839 (class 2606 OID 140693)
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 2823 (class 2606 OID 140524)
-- Name: status_rastreio status_rastreio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT status_rastreio_pkey PRIMARY KEY (id);


--
-- TOC entry 2811 (class 2606 OID 132519)
-- Name: usuarios_acesso uk_8bak9jswon2id2jbunuqlfl9e; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT uk_8bak9jswon2id2jbunuqlfl9e UNIQUE (acesso_id);


--
-- TOC entry 2813 (class 2606 OID 132497)
-- Name: usuarios_acesso unique_acesso_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT unique_acesso_user UNIQUE (usuario_id, acesso_id);


--
-- TOC entry 2841 (class 2606 OID 140722)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2843 (class 2606 OID 140732)
-- Name: vd_compra_loja_virtual vd_compra_loja_virtual_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_compra_loja_virtual
    ADD CONSTRAINT vd_compra_loja_virtual_pkey PRIMARY KEY (id);


--
-- TOC entry 2868 (class 2620 OID 140966)
-- Name: endereco validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.endereco FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2870 (class 2620 OID 140968)
-- Name: nota_fiscal_compra validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.nota_fiscal_compra FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2872 (class 2620 OID 140970)
-- Name: usuario validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.usuario FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2874 (class 2620 OID 140972)
-- Name: vd_compra_loja_virtual validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.vd_compra_loja_virtual FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2869 (class 2620 OID 140967)
-- Name: endereco validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.endereco FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2871 (class 2620 OID 140969)
-- Name: nota_fiscal_compra validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.nota_fiscal_compra FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2873 (class 2620 OID 140971)
-- Name: usuario validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.usuario FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2875 (class 2620 OID 140973)
-- Name: vd_compra_loja_virtual validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.vd_compra_loja_virtual FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2867 (class 2620 OID 140958)
-- Name: avaliacao_produto validachavepessoaavaliacaoproduto2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaavaliacaoproduto2 BEFORE INSERT ON public.avaliacao_produto FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2862 (class 2620 OID 140959)
-- Name: conta_pagar validachavepessoacontapagar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagar BEFORE UPDATE ON public.conta_pagar FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2863 (class 2620 OID 140960)
-- Name: conta_pagar validachavepessoacontapagar2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagar2 BEFORE INSERT ON public.conta_pagar FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2860 (class 2620 OID 140964)
-- Name: conta_receber validachavepessoacontareceber; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontareceber BEFORE UPDATE ON public.conta_receber FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2861 (class 2620 OID 140965)
-- Name: conta_receber validachavepessoacontareceber2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontareceber2 BEFORE INSERT ON public.conta_receber FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2864 (class 2620 OID 140962)
-- Name: conta_pagar validachavepessoafornecedorcontapagar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoafornecedorcontapagar BEFORE UPDATE ON public.conta_pagar FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa2();


--
-- TOC entry 2865 (class 2620 OID 140963)
-- Name: conta_pagar validachavepessoafornecedorcontapagar2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoafornecedorcontapagar2 BEFORE INSERT ON public.conta_pagar FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa2();


--
-- TOC entry 2866 (class 2620 OID 140955)
-- Name: avaliacao_produto validachavepessoaupdate; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaupdate BEFORE UPDATE ON public.avaliacao_produto FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2844 (class 2606 OID 132500)
-- Name: usuarios_acesso acesso_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT acesso_fk FOREIGN KEY (acesso_id) REFERENCES public.acesso(id);


--
-- TOC entry 2853 (class 2606 OID 140658)
-- Name: nota_fiscal_compra conta_pagar_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT conta_pagar_fk FOREIGN KEY (conta_pagar_id) REFERENCES public.conta_pagar(id);


--
-- TOC entry 2855 (class 2606 OID 140748)
-- Name: vd_compra_loja_virtual cupom_desconto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_compra_loja_virtual
    ADD CONSTRAINT cupom_desconto_fk FOREIGN KEY (cupom_desconto_id) REFERENCES public.cupom_desconto(id);


--
-- TOC entry 2856 (class 2606 OID 140753)
-- Name: vd_compra_loja_virtual endereco_cobranca_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_compra_loja_virtual
    ADD CONSTRAINT endereco_cobranca_fk FOREIGN KEY (endereco_cobranca_id) REFERENCES public.endereco(id);


--
-- TOC entry 2857 (class 2606 OID 140758)
-- Name: vd_compra_loja_virtual endereco_entrega_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_compra_loja_virtual
    ADD CONSTRAINT endereco_entrega_fk FOREIGN KEY (endereco_entrega_id) REFERENCES public.endereco(id);


--
-- TOC entry 2858 (class 2606 OID 140763)
-- Name: vd_compra_loja_virtual forma_pagamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_compra_loja_virtual
    ADD CONSTRAINT forma_pagamento_fk FOREIGN KEY (forma_pagamento_id) REFERENCES public.forma_pagamento(id);


--
-- TOC entry 2846 (class 2606 OID 140663)
-- Name: nota_item_produto nota_fiscal_compra_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT nota_fiscal_compra_fk FOREIGN KEY (nota_fiscal_compra_id) REFERENCES public.nota_fiscal_compra(id);


--
-- TOC entry 2859 (class 2606 OID 140768)
-- Name: vd_compra_loja_virtual nota_fiscal_venda_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_compra_loja_virtual
    ADD CONSTRAINT nota_fiscal_venda_fk FOREIGN KEY (nota_fiscal_venda_id) REFERENCES public.nota_fiscal_venda(id);


--
-- TOC entry 2851 (class 2606 OID 140694)
-- Name: avaliacao_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avaliacao_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 2852 (class 2606 OID 140699)
-- Name: imagem_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagem_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 2849 (class 2606 OID 140704)
-- Name: item_venda_loja produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 2847 (class 2606 OID 140709)
-- Name: nota_item_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 2845 (class 2606 OID 140723)
-- Name: usuarios_acesso usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- TOC entry 2850 (class 2606 OID 140733)
-- Name: item_venda_loja vd_compra_loja_virtual_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT vd_compra_loja_virtual_fk FOREIGN KEY (vd_compra_loja_virtual_id) REFERENCES public.vd_compra_loja_virtual(id);


--
-- TOC entry 2854 (class 2606 OID 140738)
-- Name: nota_fiscal_venda vd_compra_loja_virtual_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT vd_compra_loja_virtual_fk FOREIGN KEY (vd_compra_loja_virtual_id) REFERENCES public.vd_compra_loja_virtual(id);


--
-- TOC entry 2848 (class 2606 OID 140743)
-- Name: status_rastreio vd_compra_loja_virtual_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT vd_compra_loja_virtual_fk FOREIGN KEY (vd_compra_loja_virtual_id) REFERENCES public.vd_compra_loja_virtual(id);


-- Completed on 2024-05-21 19:51:52

--
-- PostgreSQL database dump complete
--

