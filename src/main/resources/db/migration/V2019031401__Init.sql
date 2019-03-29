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

/* FOREIGN KEYS */
ALTER TABLE Subscription        ADD CONSTRAINT SubscriptionSubscriptionPeriodId            FOREIGN KEY (period_id)            REFERENCES SubscriptionPeriod;
ALTER TABLE Subscription        ADD CONSTRAINT SubscriptionSystemUserId                    FOREIGN KEY (user_id)              REFERENCES SystemUser;

ALTER TABLE PaymentCard         ADD CONSTRAINT PaymentCardSystemUserId                     FOREIGN KEY (user_id)              REFERENCES SystemUser;
/* END OF FOREIGN KEYS */


/* Initial values */
INSERT INTO SubscriptionPeriod
(id, created_at, updated_at, code, cost, description, period, status)
VALUES 
(1, '2019-03-20 16:15:16', '2019-03-20 16:15:16', 'tri', 50.00, 'Comprando esta suscripción tienes derecho a realizar 3 declaraciones de IVA 1 Impuesto a la Renta y 1 Anexo de Gastos Personales', 'Trimestral', 'active'),
(2, '2019-03-20 16:15:16', '2019-03-20 16:15:16', 'sem', 80.00, 'Comprando esta suscripción tienes derecho a realizar 6 declaraciones de IVA 1 Impuesto a la Renta y 1 Anexo de Gastos Personales', 'Semestral', 'active'),
(3, '2019-03-20 16:15:16', '2019-03-20 16:15:16', 'anu', 120.00, 'Comprando esta suscripción tienes derecho a realizar 12 declaraciones de IVA 1 Impuesto a la Renta y 1 Anexo de Gastos Personales', 'Anual', 'active')
;
/* END OF Initial values */