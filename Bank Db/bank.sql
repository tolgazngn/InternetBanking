CREATE TABLE "credit_card"(
    "tc" VARCHAR(11) NOT NULL,
    "number" VARCHAR(16) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "cvc" VARCHAR(3) NOT NULL,
    "valid_thru" DATE NOT NULL,
    "balance" INTEGER NOT NULL,
    "limit" INTEGER NOT NULL
);
ALTER TABLE
    "credit_card" ADD PRIMARY KEY("tc");
CREATE TABLE "customer"(
    "tc" VARCHAR(11) NOT NULL,
    "customer_no" VARCHAR(8) NOT NULL,
    "name" VARCHAR(50) NOT NULL,
    "surname" VARCHAR(50) NOT NULL,
    "email" VARCHAR(255) NOT NULL,
    "phone_number" VARCHAR(11) NOT NULL,
    "online_password" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "customer" ADD PRIMARY KEY("tc");
ALTER TABLE
    "customer" ADD CONSTRAINT "customer_customer_no_unique" UNIQUE("customer_no");
ALTER TABLE
    "customer" ADD CONSTRAINT "customer_email_unique" UNIQUE("email");
ALTER TABLE
    "customer" ADD CONSTRAINT "customer_phone_number_unique" UNIQUE("phone_number");
CREATE TABLE "customer_address"(
    "tc" VARCHAR(11) NOT NULL,
    "country" VARCHAR(255) NOT NULL,
    "district" VARCHAR(255) NOT NULL,
    "neighbourhood" VARCHAR(255) NOT NULL,
    "street" VARCHAR(255) NOT NULL,
    "apartment_no" VARCHAR(255) NOT NULL,
    "door_no" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "customer_address" ADD PRIMARY KEY("tc");
CREATE TABLE "admin"(
    "username" VARCHAR(255) NOT NULL,
    "password" VARCHAR(20) NOT NULL,
    "name" VARCHAR(50) NOT NULL,
    "surname" VARCHAR(50) NOT NULL,
    "authority" VARCHAR(50) NOT NULL
);
ALTER TABLE
    "admin" ADD PRIMARY KEY("username");
CREATE TABLE "bank_card"(
    "tc" VARCHAR(11) NOT NULL,
    "number" VARCHAR(16) NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    "iban" VARCHAR(26) NOT NULL,
    "cvc" VARCHAR(3) NOT NULL,
    "valid_thru" DATE NOT NULL,
    "balance" INTEGER NOT NULL
);
ALTER TABLE
    "bank_card" ADD PRIMARY KEY("tc");
CREATE TABLE "account_activities"(
    "sender_iban" VARCHAR(26) NOT NULL,
    "receiver_iban" VARCHAR(26) NOT NULL,
    "activity_date" TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "amount" INTEGER NOT NULL,
    "sender_tc" VARCHAR(11) NOT NULL,
    "receiver_tc" VARCHAR(11) NOT NULL
);
ALTER TABLE
    "credit_card" ADD CONSTRAINT "credit_card_tc_foreign" FOREIGN KEY("tc") REFERENCES "customer"("tc");
ALTER TABLE
    "customer_address" ADD CONSTRAINT "customer_address_tc_foreign" FOREIGN KEY("tc") REFERENCES "customer"("tc");
ALTER TABLE
    "bank_card" ADD CONSTRAINT "bank_card_tc_foreign" FOREIGN KEY("tc") REFERENCES "customer"("tc");