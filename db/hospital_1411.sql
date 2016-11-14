CREATE TABLE "patient" (
	"id" serial NOT NULL,
	"first_name" character varying NOT NULL,
	"second_name" character varying NOT NULL,
	"last_name" character varying NOT NULL,
	"date_of_birth" DATE NOT NULL,
	UNIQUE(first_name, second_name, last_name, date_of_birth),
	CONSTRAINT patient_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "doctor" (
	"id" serial NOT NULL,
	"first_name" serial NOT NULL,
	"second_name" serial NOT NULL,
	"last_name" serial NOT NULL,
	"date_hire" DATE,
	"date_end_holiday" DATE,
	"patient_amount" bigint,
	UNIQUE(first_name, second_name, last_name),
	CONSTRAINT doctor_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "nurse" (
	"id" serial NOT NULL,
	"first_name" character varying NOT NULL,
	"second_name" character varying NOT NULL,
	"last_name" character varying NOT NULL,
	UNIQUE(first_name, second_name, last_name),
	CONSTRAINT nurse_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "cure" (
	"id" serial NOT NULL,
	"date_arrive" DATE NOT NULL,
	"date_depart" DATE NOT NULL,
	"diagnosis" character varying,
	"doctor_id" bigint NOT NULL,
	"patient_id" bigint NOT NULL,
	CONSTRAINT cure_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "operation" (
	"id" serial NOT NULL,
	"operation_title" character varying NOT NULL,
	"date_perform" DATE NOT NULL,
	"doctor_id" bigint NOT NULL,
	"cure_id" bigint NOT NULL,
	CONSTRAINT operation_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "procedure" (
	"id" serial NOT NULL,
	"procedure_name" character varying NOT NULL,
	"doctor_id" bigint NOT NULL,
	"nurse_id" bigint NOT NULL,
	"cure_id" bigint NOT NULL,
	CONSTRAINT procedure_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "drug" (
	"id" serial NOT NULL,
	"recipe" character varying NOT NULL,
	"date_end" DATE NOT NULL,
	"doctor_id" bigint NOT NULL,
	"nurse_id" bigint NOT NULL,
	"cure_id" bigint NOT NULL,
	CONSTRAINT drug_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "place" (
	"id" bigint NOT NULL,
	"cure_id" bigint NOT NULL UNIQUE,
	"available" BOOLEAN NOT NULL UNIQUE
) WITH (
  OIDS=FALSE
);






ALTER TABLE "cure" ADD CONSTRAINT "cure_fk0" FOREIGN KEY ("doctor_id") REFERENCES "doctor"("id");
ALTER TABLE "cure" ADD CONSTRAINT "cure_fk1" FOREIGN KEY ("patient_id") REFERENCES "patient"("id");

ALTER TABLE "operation" ADD CONSTRAINT "operation_fk0" FOREIGN KEY ("doctor_id") REFERENCES "doctor"("id");
ALTER TABLE "operation" ADD CONSTRAINT "operation_fk1" FOREIGN KEY ("cure_id") REFERENCES "cure"("id");

ALTER TABLE "procedure" ADD CONSTRAINT "procedure_fk0" FOREIGN KEY ("doctor_id") REFERENCES "doctor"("id");
ALTER TABLE "procedure" ADD CONSTRAINT "procedure_fk1" FOREIGN KEY ("nurse_id") REFERENCES "nurse"("id");
ALTER TABLE "procedure" ADD CONSTRAINT "procedure_fk2" FOREIGN KEY ("cure_id") REFERENCES "cure"("id");

ALTER TABLE "drug" ADD CONSTRAINT "drug_fk0" FOREIGN KEY ("doctor_id") REFERENCES "doctor"("id");
ALTER TABLE "drug" ADD CONSTRAINT "drug_fk1" FOREIGN KEY ("nurse_id") REFERENCES "nurse"("id");
ALTER TABLE "drug" ADD CONSTRAINT "drug_fk2" FOREIGN KEY ("cure_id") REFERENCES "cure"("id");

ALTER TABLE "place" ADD CONSTRAINT "place_fk0" FOREIGN KEY ("cure_id") REFERENCES "cure"("id");

