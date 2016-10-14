CREATE TABLE "patient" (
	"id" serial(1024) NOT NULL,
	"first_name" character varying(256) NOT NULL,
	"second_name" character varying(256) NOT NULL,
	"last_name" character varying(256) NOT NULL,
	"date_arrive" DATE(10) NOT NULL,
	"date_depart" DATE(10) NOT NULL,
	"doctor_id" bigint(512) NOT NULL,
	CONSTRAINT patient_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "doctor" (
	"id" serial(512) NOT NULL,
	"first_name" serial(256) NOT NULL,
	"second_name" serial(256) NOT NULL,
	"last_name" serial(256) NOT NULL,
	CONSTRAINT doctor_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "nurse" (
	"id" serial(512) NOT NULL,
	"first_name" character varying(256) NOT NULL,
	"second_name" character varying(256) NOT NULL,
	"last_name" character varying(256) NOT NULL,
	CONSTRAINT nurse_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "cure" (
	"id" serial(102400) NOT NULL,
	"date_set" DATE(10) NOT NULL,
	"doctor_id" bigint(102400) NOT NULL,
	"patient_id" bigint(1024) NOT NULL,
	CONSTRAINT cure_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "operation" (
	"id" serial(102400) NOT NULL,
	"operation_title" character varying(2048) NOT NULL,
	"date_perform" DATE(10) NOT NULL,
	"doctor_id" bigint(512) NOT NULL,
	"cure_id" bigint(102400) NOT NULL,
	CONSTRAINT operation_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "procedure" (
	"id" serial(102400) NOT NULL,
	"procedure_name" character varying(2048) NOT NULL,
	"doctor_id" bigint(102400) NOT NULL,
	"nurse_id" bigint(102400) NOT NULL,
	"cure_id" bigint(102400) NOT NULL,
	"date_end" DATE(10) NOT NULL,
	CONSTRAINT procedure_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "drug" (
	"id" serial(102400) NOT NULL,
	"recipe" character varying(2048) NOT NULL,
	"date_end" DATE(10) NOT NULL,
	"doctor_id" bigint(512) NOT NULL,
	"nurse_id" bigint(512) NOT NULL,
	CONSTRAINT drug_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "patient" ADD CONSTRAINT "patient_fk0" FOREIGN KEY ("doctor_id") REFERENCES "doctor"("id");



ALTER TABLE "cure" ADD CONSTRAINT "cure_fk0" FOREIGN KEY ("doctor_id") REFERENCES "doctor"("id");
ALTER TABLE "cure" ADD CONSTRAINT "cure_fk1" FOREIGN KEY ("patient_id") REFERENCES "patient"("id");

ALTER TABLE "operation" ADD CONSTRAINT "operation_fk0" FOREIGN KEY ("doctor_id") REFERENCES "doctor"("id");
ALTER TABLE "operation" ADD CONSTRAINT "operation_fk1" FOREIGN KEY ("cure_id") REFERENCES "cure"("id");

ALTER TABLE "procedure" ADD CONSTRAINT "procedure_fk0" FOREIGN KEY ("doctor_id") REFERENCES "doctor"("id");
ALTER TABLE "procedure" ADD CONSTRAINT "procedure_fk1" FOREIGN KEY ("nurse_id") REFERENCES "nurse"("id");
ALTER TABLE "procedure" ADD CONSTRAINT "procedure_fk2" FOREIGN KEY ("cure_id") REFERENCES "cure"("id");

ALTER TABLE "drug" ADD CONSTRAINT "drug_fk0" FOREIGN KEY ("id") REFERENCES "cure"("id");
ALTER TABLE "drug" ADD CONSTRAINT "drug_fk1" FOREIGN KEY ("doctor_id") REFERENCES "doctor"("id");
ALTER TABLE "drug" ADD CONSTRAINT "drug_fk2" FOREIGN KEY ("nurse_id") REFERENCES "nurse"("id");

