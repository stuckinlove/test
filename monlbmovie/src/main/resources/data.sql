-- ■テーブル構成
-- customer : customer_favorite = 1 : N
-- movie_header : customer_favorite = 1 : N
-- movie_header : movie_detail = 1 : 1

-- ■DDL(hibernate自動生成)
-- create table customer (customer_id bigint generated by default as identity, name varchar(255), primary key (customer_id))
-- create table customer_favorite (customer_favorite_id bigint generated by default as identity, customer_id bigint, movie_header_id bigint, primary key (customer_favorite_id))
-- create table movie_header (movie_header_id bigint generated by default as identity, popular boolean, title varchar(255), movie_detail_id bigint, primary key (movie_header_id))
-- create table movie_detail (movie_detail_id bigint generated by default as identity, detail_info varchar(255), year varchar(255), primary key (movie_detail_id))
-- alter table customer_favorite add constraint FKd6deehbyvu60dclf9rt8omw3l foreign key (customer_id) references customer
-- alter table customer_favorite add constraint FKaxhjmhgj400g2yr1jgogyhkpg foreign key (movie_header_id) references movie_header
-- alter table movie_header add constraint FK8441ph2c6njb2intkrt5h8ji3 foreign key (movie_detail_id) references movie_detail

-- ■初期データ(テスト用)
-- movie detail
INSERT INTO movie_detail (movie_detail_id, year, detail_info) VALUES (101001, '2021-01-01', 'A-detail-info-a');
INSERT INTO movie_detail (movie_detail_id, year, detail_info) VALUES (102001, '2020-01-01', 'B-detail-info-a');
INSERT INTO movie_detail (movie_detail_id, year, detail_info) VALUES (103001, '2009-01-01', 'C-detail-info-a');
INSERT INTO movie_detail (movie_detail_id, year, detail_info) VALUES (104001, '2008-01-01', 'D-detail-info-a');
INSERT INTO movie_detail (movie_detail_id, year, detail_info) VALUES (105001, '2007-01-01', 'E-detail-info-a');
INSERT INTO movie_detail (movie_detail_id, year, detail_info) VALUES (106001, '2006-01-01', 'F-detail-info-a');

-- movie header
INSERT INTO movie_header (movie_header_id, title, popular, movie_detail_id) VALUES (1001, 'title-A', true, 101001);
INSERT INTO movie_header (movie_header_id, title, popular, movie_detail_id) VALUES (2001, 'title-B', true, 102001);
INSERT INTO movie_header (movie_header_id, title, popular, movie_detail_id) VALUES (3001, 'title-C', true, 103001);
INSERT INTO movie_header (movie_header_id, title, popular, movie_detail_id) VALUES (4001, 'title-D', true, 104001);
INSERT INTO movie_header (movie_header_id, title, popular, movie_detail_id) VALUES (5001, 'title-E', false, 105001);
INSERT INTO movie_header (movie_header_id, title, popular, movie_detail_id) VALUES (6001, 'title-F', false, 106001);

-- customer
INSERT INTO customer (customer_id, name) VALUES (101, 'louis');

-- customer favorite
INSERT INTO customer_favorite (customer_favorite_id, customer_id, movie_header_id) VALUES (1, 101, 1001);
INSERT INTO customer_favorite (customer_favorite_id, customer_id, movie_header_id) VALUES (2, 101, 2001);
