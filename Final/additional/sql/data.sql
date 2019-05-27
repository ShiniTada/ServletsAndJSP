USE `good_couriers` ;

INSERT INTO `user`
VALUES (1,'admin','admin','21232f297a57a5a743894a0e4a801fc3'),
(2,'courier1','courier','c79d74a1e537c13ccb4ec1c6266d5a8d'),
(3,'courier2','courier','61b49956a9f21a7268ea9d175aa0fc91'),
(4,'courier3','courier','4333b78a57dfb4cda4232bc6f18cebd2'),
(5,'courier4','courier','72677c8938c75c207b85dc330d59d5a1'),
(6,'customer1','customer','ffbc4675f864e0e9aab8bdf7a0437010'),
(7,'customer2','customer','5ce4d191fd14ac85a1469fb8c29b7a7b'),
(8,'customer3','customer','033f7f6121501ae98285ad77f216d5e7'),
(27,'customer4','customer','55feb130be438e686ad6a80d12dd8f44'),
(51,'courier5','courier','0c67441f58546adbbd3bd1ff0ff40214'),
(57,'courier6','courier','ec47870b24a2927ac774383abfb714c4'),
(58,'courier7','courier','069213a92a99c6975cfe5a25e75dfb69'),
(59,'courier8','courier','1c52f6281dcc0cf605a1071c9719140d');

INSERT INTO `courierrecord`
VALUES (1,2,100,85,80,88.3,1,'2'),
(2,3,90,89,86.33,88.44,1,'6'),
(3,4,84.28,90,95.71,90.01,1,'7'),
(4,5,0,0,0,0,0,'0'),
(17,51,0,0,0,0,0,'0'),
(23,57,0,0,0,0,1,'0'),
(24,58,90,100,100,96.67,1,'1'),
(25,59,0,0,0,0,1,'0');

INSERT INTO `customerorder`
VALUES (1,'Minsk, Bedu, 4','Pisnsk, Parkovaya, 24','2019-04-17','completed','laptop HP',10,6),
(3,'Minsk, Filimonova, 25A','Minsk, Kazintsa, 86k1','2019-04-30','completed','sofa, tv',30,8),
(4,'Minsk, Bedu, 4','Minsk, Platonova, 8A','2019-05-21','denied','fridge',25,6),
(6,'Minsk, Bedu, 4','Minsk, Kosmonavtov, 30','2019-05-31','posted','telephone 8g',10,6),
(7,'Minsk, Kosmo, 17','Minsk, Platonova, 8A','2019-05-19','posted','raw fish',15,6),
(8,'Minsk, Voronyanskogo, 19','Minsk, Bedu, 4','2019-05-20','delivered','mirrors 45x45',20,6),
(15,'Minsk, Voronyanskogo, 19','Minsk, Lenina, 19A','2019-05-27','denied','chairs',15,8),
(16,'Minsk, Bedu, 4','Minsk, Bedu, 19','2019-05-27','delivered','lamp',10,8),
(17,'Minsk, Voronyanskogo, 19','Minsk, Platonova, 8A','2019-05-24','posted','fridges',40,8);


INSERT INTO `transport`
VALUES (3,'cycle',1),
(4,'motorcycle',1),
(5,'car',2),
(6,'truck',3),
(35,'truck',4),
(36,'car',4),
(46,'motorcycle',23),
(47,'car',23),
(48,'cycle',24),
(49,'motorcycle',24),
(50,'car',24),
(51,'car',25),
(52,'truck',25);

INSERT INTO `goods`
VALUES (1,'food',1),
(2,'food',2),
(3,'easy-to-beat',2),
(4,'tech',3),
(5,'furniture',3),
(27,'tech',4),
(28,'easy-to-beat',4),
(39,'tech',23),
(40,'furniture',23),
(41,'tech',24),
(42,'easy-to-beat',24),
(43,'food',25),
(44,'tech',25);

INSERT INTO `courier_has_customerorder`
VALUES (1,2,1),
(5,4,3),
(7,2,4),
(9,2,6),
(10,2,7),
(11,3,8),
(18,4,15),
(19,4,16),
(20,4,17);
