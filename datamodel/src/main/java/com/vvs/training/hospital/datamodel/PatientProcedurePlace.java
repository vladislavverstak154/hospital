package com.vvs.training.hospital.datamodel;

public class PatientProcedurePlace extends PatientPlace {
	private Procedure procedure;

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}
	
}
