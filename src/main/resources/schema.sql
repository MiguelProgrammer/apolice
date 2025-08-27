--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: apolice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.apolice (
    valor_segurado numeric(12,2),
    valor_total_premio_mensal numeric(12,2),
    data_criacao timestamp(6) with time zone,
    data_fim timestamp(6) with time zone,
    apolice_id uuid,
    cliente uuid,
    cobertura uuid,
    id uuid NOT NULL,
    produto uuid,
    categoria character varying(255),
    tipo_pagamento character varying(255),
    assistencia character varying(255)[],
    CONSTRAINT apolice_categoria_check CHECK (((categoria)::text = ANY ((ARRAY['VIDA'::character varying, 'AUTO'::character varying, 'RESIDENCIAL'::character varying, 'EMPRESARIAL'::character varying])::text[]))),
    CONSTRAINT apolice_tipo_pagamento_check CHECK (((tipo_pagamento)::text = ANY ((ARRAY['CARTAO_CREDITO'::character varying, 'DEBITO_EM_CONTA'::character varying, 'BOLETO'::character varying, 'PIX'::character varying])::text[])))
);


ALTER TABLE public.apolice OWNER TO postgres;

--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    classificacao smallint,
    data_analise timestamp(6) with time zone,
    apolice_id uuid,
    cliente_id uuid,
    id uuid NOT NULL,
    CONSTRAINT cliente_classificacao_check CHECK (((classificacao >= 0) AND (classificacao <= 3)))
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cobertura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cobertura (
    colisao_terceiros numeric(12,2),
    perda_total numeric(12,2),
    roubo numeric(12,2),
    id uuid NOT NULL
);


ALTER TABLE public.cobertura OWNER TO postgres;

--
-- Name: fraude; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fraude (
    valor_segurado numeric(12,2),
    valor_total_premio_mensal numeric(12,2),
    data_criacao timestamp(6) with time zone,
    data_fim timestamp(6) with time zone,
    apolice_id uuid,
    cliente uuid,
    cobertura uuid,
    id uuid NOT NULL,
    produto uuid,
    categoria character varying(255),
    tipo_pagamento character varying(255),
    assistencia character varying(255)[],
    CONSTRAINT fraude_categoria_check CHECK (((categoria)::text = ANY ((ARRAY['VIDA'::character varying, 'AUTO'::character varying, 'RESIDENCIAL'::character varying, 'EMPRESARIAL'::character varying])::text[]))),
    CONSTRAINT fraude_tipo_pagamento_check CHECK (((tipo_pagamento)::text = ANY ((ARRAY['CARTAO_CREDITO'::character varying, 'DEBITO_EM_CONTA'::character varying, 'BOLETO'::character varying, 'PIX'::character varying])::text[])))
);


ALTER TABLE public.fraude OWNER TO postgres;

--
-- Name: historico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historico (
    data_ini timestamp(6) with time zone,
    apolice_id uuid,
    id uuid NOT NULL,
    status character varying(255),
    CONSTRAINT historico_status_check CHECK (((status)::text = ANY ((ARRAY['RECEBIDO'::character varying, 'VALIDADO'::character varying, 'PENDENTE'::character varying, 'REJEITADO'::character varying, 'APROVADO'::character varying, 'CANCELADA'::character varying])::text[])))
);


ALTER TABLE public.historico OWNER TO postgres;

--
-- Name: ocorrencia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ocorrencia (
    tipo smallint,
    data_atualizacao timestamp(6) with time zone,
    data_criacao timestamp(6) with time zone,
    cliente_id uuid,
    id uuid NOT NULL,
    produto_id uuid,
    descricao character varying(255),
    CONSTRAINT ocorrencia_tipo_check CHECK (((tipo >= 0) AND (tipo <= 3)))
);


ALTER TABLE public.ocorrencia OWNER TO postgres;

--
-- Data for Name: apolice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.apolice (valor_segurado, valor_total_premio_mensal, data_criacao, data_fim, apolice_id, cliente, cobertura, id, produto, categoria, tipo_pagamento, assistencia) FROM stdin;
\.


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (classificacao, data_analise, apolice_id, cliente_id, id) FROM stdin;
\.


--
-- Data for Name: cobertura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cobertura (colisao_terceiros, perda_total, roubo, id) FROM stdin;
\.


--
-- Data for Name: fraude; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fraude (valor_segurado, valor_total_premio_mensal, data_criacao, data_fim, apolice_id, cliente, cobertura, id, produto, categoria, tipo_pagamento, assistencia) FROM stdin;
\.


--
-- Data for Name: historico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.historico (data_ini, apolice_id, id, status) FROM stdin;
\.


--
-- Data for Name: ocorrencia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ocorrencia (tipo, data_atualizacao, data_criacao, cliente_id, id, produto_id, descricao) FROM stdin;
\.


--
-- Name: apolice apolice_cobertura_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.apolice
    ADD CONSTRAINT apolice_cobertura_key UNIQUE (cobertura);


--
-- Name: apolice apolice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.apolice
    ADD CONSTRAINT apolice_pkey PRIMARY KEY (id);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: cobertura cobertura_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cobertura
    ADD CONSTRAINT cobertura_pkey PRIMARY KEY (id);


--
-- Name: fraude fraude_cobertura_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fraude
    ADD CONSTRAINT fraude_cobertura_key UNIQUE (cobertura);


--
-- Name: fraude fraude_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fraude
    ADD CONSTRAINT fraude_pkey PRIMARY KEY (id);


--
-- Name: historico historico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico
    ADD CONSTRAINT historico_pkey PRIMARY KEY (id);


--
-- Name: ocorrencia ocorrencia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ocorrencia
    ADD CONSTRAINT ocorrencia_pkey PRIMARY KEY (id);


--
-- Name: apolice fkb70ynpese9v78fmih0kt5wcvu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.apolice
    ADD CONSTRAINT fkb70ynpese9v78fmih0kt5wcvu FOREIGN KEY (cobertura) REFERENCES public.cobertura(id);


--
-- Name: ocorrencia fki2jun5clhup2yqcnebnvpw7od; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ocorrencia
    ADD CONSTRAINT fki2jun5clhup2yqcnebnvpw7od FOREIGN KEY (cliente_id) REFERENCES public.cliente(id);


--
-- Name: historico fkofe7fux9ai7rrcd6t329880ae; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historico
    ADD CONSTRAINT fkofe7fux9ai7rrcd6t329880ae FOREIGN KEY (apolice_id) REFERENCES public.apolice(id);


--
-- PostgreSQL database dump complete
--

