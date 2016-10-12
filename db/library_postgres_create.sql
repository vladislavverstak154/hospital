CREATE TABLE "book" (
	"id" serial NOT NULL,
	"title" character varying NOT NULL,
	CONSTRAINT book_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "author" (
	"id" serial NOT NULL,
	"firts_name" character varying NOT NULL,
	"last_name" character varying NOT NULL,
	CONSTRAINT author_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "book_2_author" (
	"book_id" bigint NOT NULL,
	"author_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "book_details" (
	"id" bigint NOT NULL,
	"pages_count" int NOT NULL,
	"cover_color" character varying NOT NULL,
	CONSTRAINT book_details_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);





ALTER TABLE "book_2_author" ADD CONSTRAINT "book_2_author_fk0" FOREIGN KEY ("book_id") REFERENCES "book"("id");
ALTER TABLE "book_2_author" ADD CONSTRAINT "book_2_author_fk1" FOREIGN KEY ("author_id") REFERENCES "author"("id");

ALTER TABLE "book_details" ADD CONSTRAINT "book_details_fk0" FOREIGN KEY ("id") REFERENCES "book"("id");

