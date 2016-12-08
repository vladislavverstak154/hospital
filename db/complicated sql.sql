
select patinfo.id, patinfo.first_name, patinfo.second_name, druginfo.recipe, plinfo.place_id from (select patient.id,
 patient.first_name, patient.second_name, cure.id as cure_id from patient inner join
cure on cure.patient_id = patient.id where cure.date_depart is null) patinfo inner join
(select drug.recipe, drug.cure_id as cure_id from drug where date_end is null) druginfo on patinfo.cure_id=druginfo.cure_id inner join (select place.id as place_id,
 place.cure_id as cure_id from place where available is false) plinfo on plinfo.cure_id=patinfo.cure_id;






select patient.id, patient.first_name, patient.second_name, cure.id as cure_id from patient inner join
cure on cure.patient_id = patient.id where cure.date_depart is null;

select drug.recipe, drug.cure_id as cure_id from drug where date_end is null;

select place.id as place_id, place.cure_id as cure_id from place where available is false;

