package com.paraciuman.hackathon.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Agenda.class)
public abstract class Agenda_ {

	public static volatile SingularAttribute<Agenda, Date> saveDate;
	public static volatile SingularAttribute<Agenda, Date> endDate;
	public static volatile SingularAttribute<Agenda, Long> id;
	public static volatile SingularAttribute<Agenda, User> user;
	public static volatile SingularAttribute<Agenda, Date> startDate;

}

