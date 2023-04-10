CREATE TABLE "event" (
    id bigserial not null,
    clientId varchar(255),
    accountId varchar(255),
    apInstanceId varchar(255),
    callingNumber varchar(255),
    calledNumber varchar(255),
    callingPrefix varchar(255),
    calledPrefix varchar(255),
    eventBeginDate varchar(255),
    eventEndDate varchar(255),
    productId varchar(255),
    rootProductId varchar(255),
    intProperty1 bigint,
    intProperty2 bigint,
    intProperty3 bigint,
    intProperty4 bigint,
    intProperty5 bigint,
    stringProperty1 varchar(255),
    stringProperty2 varchar(255),
    stringProperty3 varchar(255),
    stringProperty4 varchar(255),
    stringProperty5 varchar(255),
    booleanProperty1 boolean,
    booleanProperty2 boolean,
    booleanProperty3 boolean,
    booleanProperty4 boolean,
    booleanProperty5 boolean,
    quantity bigint,
    billingCycleDefId varchar(255),
    billingCycleInstanceId varchar(255),
    unit varchar(255),
    PRIMARY KEY (id)
);