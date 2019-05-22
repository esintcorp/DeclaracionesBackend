CREATE TABLE SystemUser (
  id             INTEGER NOT NULL PRIMARY KEY,
  created_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  updated_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  status         VARCHAR(24) NOT NULL,
  type           VARCHAR(64) NOT NULL,
  email          VARCHAR(256) NOT NULL,
  firstName      VARCHAR(1024),
  idCard         VARCHAR(10) NOT NULL,
  lastName       VARCHAR(1024) NOT NULL,
  rucNumber      VARCHAR(15),
  password       VARCHAR(1024) NOT NULL
);

CREATE SEQUENCE systemuser_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE Subscription (
  id             INTEGER NOT NULL PRIMARY KEY,
  created_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  updated_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  status         VARCHAR(24) NOT NULL,
  type           VARCHAR(64),
  period_id      INTEGER NOT NULL,
  user_id        INTEGER NOT NULL
);

CREATE SEQUENCE subscription_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE SubscriptionPeriod (
  id             INTEGER NOT NULL PRIMARY KEY,
  created_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  updated_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  status         VARCHAR(24) NOT NULL,
  code           VARCHAR(24) NOT NULL,
  cost           NUMERIC(19,2) NOT NULL,
  description    VARCHAR(1024),
  period         VARCHAR(48)
);

CREATE SEQUENCE subscriptionperiod_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE PaymentCard (
  id             INTEGER NOT NULL PRIMARY KEY,
  created_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  updated_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  status         VARCHAR(24) NOT NULL,
  cardName       VARCHAR(1024) NOT NULL,
  cardNumber     VARCHAR(20) NOT NULL,
  expirationDate DATE NOT NULL,
  cvv            VARCHAR(8) NOT NULL,
  user_id        INTEGER NOT NULL
);

CREATE SEQUENCE paymentcard_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE Declaration (
  id             INTEGER NOT NULL PRIMARY KEY,
  created_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  updated_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  status         VARCHAR(24) NOT NULL,
  type           VARCHAR(16) NOT NULL,
  codeSRI        VARCHAR(16) NOT NULL,
  name           VARCHAR(128) NOT NULL,
  description    VARCHAR(1024) NOT NULL,
  cronology      VARCHAR(16) NOT NULL
);

CREATE SEQUENCE declaration_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE Bill (
  id             INTEGER NOT NULL PRIMARY KEY,
  created_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  updated_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  status         VARCHAR(24) NOT NULL,
  subtotal       NUMERIC(19,2) NOT NULL,
  iva            NUMERIC(19,2) NOT NULL,
  total          NUMERIC(19,2) NOT NULL,
  user_id        INTEGER NOT NULL
);

CREATE SEQUENCE bill_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE BillType (
  id             INTEGER NOT NULL PRIMARY KEY,
  created_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  updated_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  status         VARCHAR(24) NOT NULL,
  name           VARCHAR(16) NOT NULL
);

CREATE SEQUENCE billType_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE Question (
  id             INTEGER NOT NULL PRIMARY KEY,
  created_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  updated_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  status         VARCHAR(24) NOT NULL,
  declaration_id INTEGER NOT NULL,
  billType_id    INTEGER NOT NULL,
  name           VARCHAR(1024) NOT NULL,
  sequence       INTEGER NOT NULL,
  datatype       VARCHAR(24) NOT NULL
);

CREATE SEQUENCE question_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE Answer (
  id             INTEGER NOT NULL PRIMARY KEY,
  created_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  updated_at     TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
  status         VARCHAR(24) NOT NULL,
  question_id    INTEGER NOT NULL,
  user_id        INTEGER NOT NULL,
  bill_id        INTEGER NOT NULL
);

CREATE SEQUENCE answer_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

/* FOREIGN KEYS */
ALTER TABLE Subscription        ADD CONSTRAINT SubscriptionSubscriptionPeriodId            FOREIGN KEY (period_id)            REFERENCES SubscriptionPeriod;
ALTER TABLE Subscription        ADD CONSTRAINT SubscriptionSystemUserId                    FOREIGN KEY (user_id)              REFERENCES SystemUser;

ALTER TABLE PaymentCard         ADD CONSTRAINT PaymentCardSystemUserId                     FOREIGN KEY (user_id)              REFERENCES SystemUser;

ALTER TABLE Question            ADD CONSTRAINT QuestionDeclarationId                       FOREIGN KEY (declaration_id)       REFERENCES Declaration;
ALTER TABLE Question            ADD CONSTRAINT QuestionBillTypeId                          FOREIGN KEY (billType_id)          REFERENCES BillType;

ALTER TABLE Answer              ADD CONSTRAINT AnswerSystemUserId                          FOREIGN KEY (user_id)              REFERENCES SystemUser;
ALTER TABLE Answer              ADD CONSTRAINT AnswerQuestionId                            FOREIGN KEY (question_id)          REFERENCES Question;
ALTER TABLE Answer              ADD CONSTRAINT AnswerBillId                                FOREIGN KEY (bill_id)              REFERENCES Bill;
/* END OF FOREIGN KEYS */


/* Initial values */
INSERT INTO SubscriptionPeriod
(id, created_at, updated_at, code, cost, description, period, status)
VALUES 
(1, '2019-03-20 16:15:16', '2019-03-20 16:15:16', 'tri', 50.00, 'Comprando esta suscripción tienes derecho a realizar 3 declaraciones de IVA 1 Impuesto a la Renta y 1 Anexo de Gastos Personales', 'Trimestral', 'active'),
(2, '2019-03-20 16:15:16', '2019-03-20 16:15:16', 'sem', 80.00, 'Comprando esta suscripción tienes derecho a realizar 6 declaraciones de IVA 1 Impuesto a la Renta y 1 Anexo de Gastos Personales', 'Semestral', 'active'),
(3, '2019-03-20 16:15:16', '2019-03-20 16:15:16', 'anu', 120.00, 'Comprando esta suscripción tienes derecho a realizar 12 declaraciones de IVA 1 Impuesto a la Renta y 1 Anexo de Gastos Personales', 'Anual', 'active')
;

INSERT INTO Declaration
(id, created_at, updated_at, status, type, codeSRI, name, description, cronology)
VALUES
(1, now(), now(), 'active', 'IVA', '101', 'IVA', 'Declaracion de Impuesto al Valor Agregado', 'mensual'),
(2, now(), now(), 'active', 'Renta', '102', 'Impuesto a la Renta', 'Declaracion de Impuesto a la Renta', 'anual'),
(3, now(), now(), 'active', 'Anexo', '103', 'Anexo Transaccional Simplificado', 'Declaracion de Anexos', 'anual');

INSERT INTO BillType
(id, created_at, updated_at, status, name)
VALUES
(1, now(), now(), 'active', 'Compra'),
(2, now(), now(), 'active', 'Venta');



INSERT INTO SystemUser
(ID, created_at, updated_at, EMAIL, FIRSTNAME, IDCARD, LASTNAME, PASSWORD, RUCNUMBER, STATUS, TYPE)
VALUES
(1, now(), now(), 'h.adrian1388@gmail.com', 'Hêctor', '0502875065', 'Mosquera', '$2a$10$zU.9PO.DTSdKHhCRaVU0K.6T9ZNuNY85cJJXMDVfvtT3wdpi/bgLi', '111', 'active', 'nat');

/* END OF Initial values */