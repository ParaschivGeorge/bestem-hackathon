package com.paraciuman.hackathon.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Places.class)
public abstract class Places_ {

	public static volatile SingularAttribute<Places, Integer> estimation;
	public static volatile SingularAttribute<Places, Date> endDate;
	public static volatile SingularAttribute<Places, Long> id;
	public static volatile SingularAttribute<Places, Agenda> agenda;
	public static volatile SingularAttribute<Places, Date> startDate;

}

