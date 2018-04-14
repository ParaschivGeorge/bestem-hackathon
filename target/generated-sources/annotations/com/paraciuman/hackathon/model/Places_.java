package com.paraciuman.hackathon.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Place.class)
public abstract class Places_ {

	public static volatile SingularAttribute<Place, Integer> estimation;
	public static volatile SingularAttribute<Place, Date> endDate;
	public static volatile SingularAttribute<Place, Long> id;
	public static volatile SingularAttribute<Place, Agenda> agenda;
	public static volatile SingularAttribute<Place, Date> startDate;

}

